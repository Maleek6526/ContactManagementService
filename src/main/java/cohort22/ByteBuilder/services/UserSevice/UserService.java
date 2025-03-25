package cohort22.ByteBuilder.services.UserSevice;

import cohort22.ByteBuilder.dto.request.LoginRequest;
import cohort22.ByteBuilder.dto.request.RegisterRequest;
import cohort22.ByteBuilder.dto.response.LoginResponse;
import cohort22.ByteBuilder.dto.response.UserResponse;

public interface UserService {
    UserResponse registerUser(RegisterRequest request);
    UserResponse verifyEmail(String token);
    UserResponse getUserDetails(String email);
    LoginResponse login(LoginRequest request);
}
