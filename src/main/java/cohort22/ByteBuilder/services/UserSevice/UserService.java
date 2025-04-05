package cohort22.ByteBuilder.services.UserSevice;

import cohort22.ByteBuilder.data.model.Contact;
import cohort22.ByteBuilder.dto.request.*;
import cohort22.ByteBuilder.dto.response.*;

import java.util.List;

public interface UserService {
    UserResponse registerUser(RegisterRequest request);
    UserResponse verifyEmail(String token);
    UserResponse getUserDetails(String email);
    LoginResponse login(LoginRequest request);
    AddContactResponse addContact(AddContactRequest request);
    void blockNumber(BlockNumberRequestDTO request);
    void unblockNumber(UnblockNumberRequestDTO request);
    BlockedNumbersResponseDTO getUserBlockedNumbers(String userEmail);
    void reportSpam(ReportSpamRequest request);
    boolean isNumberSpam(String phoneNumber);
    List<Contact> viewAllSavedContacts(String userEmail);
    DeleteContactResponse deleteContact(DeleteContactRequest request);
    UpdateContactResponse updateContact(UpdateContactRequest request);
    void deleteAllContacts(String addedBy);
    List<SpamContactResponse> getAllSpamContactList();
    int countContacts(String userEmail);
    int countBlockedContacts(String userEmail);
    int countSpamContact(String userEmail);

}
