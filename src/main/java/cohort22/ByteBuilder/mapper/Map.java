package cohort22.ByteBuilder.mapper;

import cohort22.ByteBuilder.data.model.User;
import cohort22.ByteBuilder.dto.request.RegisterRequest;
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
}
