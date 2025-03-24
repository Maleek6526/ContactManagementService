package cohort22.ByteBuilder.services.UserSevice;

import cohort22.ByteBuilder.dto.request.RegisterRequest;
import cohort22.ByteBuilder.dto.response.UserResponse;

public interface UserService {
    UserResponse registerUser(RegisterRequest request);
    UserResponse verifyEmail(String token);
    UserResponse getUserDetails(String email);
}
