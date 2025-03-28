package cohort22.ByteBuilder.mapper;

import cohort22.ByteBuilder.data.model.Contact;
import cohort22.ByteBuilder.data.model.User;
import cohort22.ByteBuilder.dto.request.AddContactRequest;
import cohort22.ByteBuilder.dto.request.RegisterRequest;
import cohort22.ByteBuilder.dto.response.AddContactResponse;
import cohort22.ByteBuilder.dto.response.UserResponse;
import java.time.LocalDateTime;
import java.util.UUID;

public class Map {
    public static User mapToRegisterRequest(RegisterRequest request){
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setPassword(request.getPassword());
        user.setVerificationToken(UUID.randomUUID().toString());
        user.setTokenExpiryDate(LocalDateTime.now().plusMinutes(30));
        user.setVerified(false);
        return user;
    }

    public static UserResponse mapTopRegisterResponse(User user){
        UserResponse userResponse = new UserResponse();
        userResponse.setEmail(user.getEmail());
        userResponse.setName(user.getName());
        userResponse.setPhoneNumber(user.getPhoneNumber());
        userResponse.setVerified(user.isVerified());
        return userResponse;
    }

    public static Contact mapToAddContactrequest(AddContactRequest request){
        Contact contact = new Contact();
        contact.setName(request.getName());
        contact.setEmail(request.getEmail());
        contact.setPhoneNumber(request.getPhoneNumber());
        contact.setAddedBy(request.getEmail());
        return contact;
    }

    public static AddContactResponse mapToAddContactresponse(Contact contact) {
        AddContactResponse response = new AddContactResponse();
        response.setName(contact.getName());
        response.setEmail(contact.getEmail());
        response.setPhoneNumber(contact.getPhoneNumber());
        response.setAddedBy(contact.getAddedBy());
        response.setSpam(contact.isSpam());
        return response;
    }
}
