package cohort22.ByteBuilder.services.UserSevice;

import cohort22.ByteBuilder.data.model.Contact;
import cohort22.ByteBuilder.data.model.SpamReport;
import cohort22.ByteBuilder.data.model.User;
import cohort22.ByteBuilder.data.repository.ContactRepository;
import cohort22.ByteBuilder.data.repository.SpamReportRepository;
import cohort22.ByteBuilder.data.repository.UserRepository;
import cohort22.ByteBuilder.dto.request.*;
import cohort22.ByteBuilder.dto.response.*;
import cohort22.ByteBuilder.exception.DuplicateContactException;
import cohort22.ByteBuilder.exception.UserAlreadyExistsException;
import cohort22.ByteBuilder.exception.UserException;
import cohort22.ByteBuilder.exception.UserNotFoundException;
import cohort22.ByteBuilder.mapper.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import java.time.LocalDateTime;


@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ContactRepository contactRepository;
    @Autowired
    private SpamReportRepository spamReportRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public UserResponse registerUser(RegisterRequest request) {
        if (userRepository.findById(request.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException("Email already registered!");
        }
        User user = Map.mapToRegisterRequest(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(user);

        return Map.mapTopRegisterResponse(user);
    }

    @Override
    public UserResponse verifyEmail(String token) {
        Optional<User> userOpt = userRepository.findByVerificationToken(token);
        if (userOpt.isEmpty() || userOpt.get().getTokenExpiryDate().isBefore(LocalDateTime.now())) {
            throw new UserException("Invalid or expired token!");
        }

        User user = userOpt.get();
        user.setVerified(true);
        user.setVerificationToken(null);
        userRepository.save(user);

        return Map.mapTopRegisterResponse(user);
    }

    @Override
    public UserResponse getUserDetails(String email) {
        User user = userRepository.findById(email)
                .orElseThrow(() -> new UserNotFoundException("User not found!"));
        return Map.mapTopRegisterResponse(user);
    }

    @Override
    public LoginResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UserNotFoundException("User not found!"));

        if (!user.isVerified()) {
            throw new IllegalStateException("User is not verified. Please check your email.");
        }

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Incorrect password! Try again.");
        }

        LoginResponse response = new LoginResponse();
        response.setMessage("Login successful!");
        response.setUserId(user.getEmail());
        return response;
    }

    @Override
    public AddContactResponse addContact(AddContactRequest request) {
        User user = userRepository.findById(request.getUserEmail())
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        boolean contactExists = user.getContacts().stream()
                .anyMatch(contact -> contact.getPhoneNumber().equals(request.getPhoneNumber()));

        if (contactExists) {
            throw new DuplicateContactException("A contact with this phone number already exists!");
        }

        Contact contact = Map.mapToAddContactrequest(request);
        contact.setAddedBy(user.getEmail());

        contactRepository.save(contact);

        user.getContacts().add(contact);

        userRepository.save(user);

        AddContactResponse response = Map.mapToAddContactresponse(contact);

        response.setAddedBy(user.getEmail());

        return response;
    }

    @Override
    public void blockNumber(BlockNumberRequestDTO request) {
        User user = userRepository.findById(request.getUserEmail())
                .orElseThrow(() -> new IllegalArgumentException("User not found."));

        if (user.getBlockedNumbers().contains(request.getPhoneNumber())) {
            throw new IllegalStateException("You have already blocked this number.");
        }

        user.getBlockedNumbers().add(request.getPhoneNumber());
        userRepository.save(user);
    }
    @Override
    public void unblockNumber(UnblockNumberRequestDTO request) {
        User user = userRepository.findById(request.getUserEmail())
                .orElseThrow(() -> new IllegalArgumentException("User not found."));

        if (!user.getBlockedNumbers().contains(request.getPhoneNumber())) {
            throw new IllegalStateException("You have not blocked this number.");
        }

        user.getBlockedNumbers().remove(request.getPhoneNumber());
        userRepository.save(user);
    }

    @Override
    public BlockedNumbersResponseDTO getUserBlockedNumbers(String userEmail) {
        User user = userRepository.findById(userEmail)
                .orElseThrow(() -> new IllegalArgumentException("User not found."));
        BlockedNumbersResponseDTO response = new BlockedNumbersResponseDTO();
        response.setBlockedNumbers(user.getBlockedNumbers());

        return response;
    }

    @Override
    public void reportSpam(ReportSpamRequest request) {
        Optional<SpamReport> existingReport = spamReportRepository.findByPhoneNumber(request.getPhoneNumber());

        if (existingReport.isPresent()) {
            SpamReport spamReport = existingReport.get();

            if (spamReport.getReportedBy().contains(request.getReporterEmail())) {
                throw new UserException("You have already reported this number as spam.");
            }

            spamReport.getReportedBy().add(request.getReporterEmail());

            spamReport.setReportCount(spamReport.getReportCount() + 1);

            spamReportRepository.save(spamReport);
        } else {
            SpamReport newSpamReport = new SpamReport();
            newSpamReport.setPhoneNumber(request.getPhoneNumber());
            newSpamReport.getReportedBy().add(request.getReporterEmail());
            newSpamReport.setReportCount(1);

            spamReportRepository.save(newSpamReport);
        }

        contactRepository.findByPhoneNumber(request.getPhoneNumber()).ifPresent(contact -> {
            contact.setSpam(true);
            contactRepository.save(contact);
        });
    }

    @Override
    public boolean isNumberSpam(String phoneNumber) {

        if (phoneNumber == null || phoneNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("Phone number cannot be null or empty.");
        }

        Optional<SpamReport> spamReport = spamReportRepository.findByPhoneNumber(phoneNumber);

        return spamReport.map(report -> report.getReportCount() >= 5).orElse(false);
    }


    @Override
    public List<Contact> viewAllSavedContacts(String userEmail) {
        User user = userRepository.findById(userEmail)
                .orElseThrow(() -> new UserNotFoundException("User not found!"));

        List<Contact> contacts = new ArrayList<>(user.getContacts());

        if (contacts.isEmpty()) {
            throw new UserException("No contacts found for this user.");
        }

        return contacts;
    }

    @Override
    public DeleteContactResponse deleteContact(DeleteContactRequest request) {
        User user = userRepository.findById(request.getUserEmail())
                .orElseThrow(() -> new UserNotFoundException("User not found!"));

        List<String> contactsToDelete = request.getPhoneNumbers();

        if (contactsToDelete == null || contactsToDelete.isEmpty()) {
            throw new UserException("No contacts selected for deletion!");
        }

        List<Contact> contactsBeforeDeletion = new ArrayList<>(user.getContacts());

        user.getContacts().removeIf(contact -> contactsToDelete.contains(contact.getPhoneNumber()));

        if (contactsBeforeDeletion.size() == user.getContacts().size()) {
            throw new UserException("No matching contacts found for deletion!");
        }

        userRepository.save(user);

        return new DeleteContactResponse("Selected contacts deleted successfully!", new ArrayList<>(user.getContacts()));
    }

    @Override
    public UpdateContactResponse updateContact(UpdateContactRequest request) {
        User user = userRepository.findById(request.getUserEmail())
                .orElseThrow(() -> new UserNotFoundException("User not found!"));

        Contact contactToUpdate = user.getContacts().stream()
                .filter(contact -> contact.getPhoneNumber().equals(request.getOldPhoneNumber()))
                .findFirst()
                .orElseThrow(() -> new UserException("Contact not found!"));

        if (request.getNewPhoneNumber() != null &&
                user.getContacts().stream().anyMatch(contact -> contact.getPhoneNumber().equals(request.getNewPhoneNumber())
                        && !contact.equals(contactToUpdate))) {
            throw new UserException("A contact with this phone number already exists!");
        }

        if (request.getNewName() != null && !request.getNewName().isEmpty()) {
            contactToUpdate.setName(request.getNewName());
        }
        if (request.getNewPhoneNumber() != null && !request.getNewPhoneNumber().isEmpty()) {
            contactToUpdate.setPhoneNumber(request.getNewPhoneNumber());
        }
        if (request.getNewEmail() != null && !request.getNewEmail().isEmpty()) {
            contactToUpdate.setEmail(request.getNewEmail());
        }

        contactRepository.save(contactToUpdate);

        userRepository.save(user);

        return new UpdateContactResponse("Contact updated successfully!", new ArrayList<>(user.getContacts()));
    }

    @Override
    public void deleteAllContacts(String addedBy) {
        User user = userRepository.findById(addedBy)
                .orElseThrow(() -> new UserNotFoundException("User not found!"));

        List<Contact> userContacts = new ArrayList<>(user.getContacts());

        if (userContacts.isEmpty()) {
            throw new UserException("No contacts found for this user.");
        }

//        contactRepository.deleteAll(userContacts);
        user.getContacts().clear();
        userRepository.save(user);
    }


}
