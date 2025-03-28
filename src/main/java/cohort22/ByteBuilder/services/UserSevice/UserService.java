package cohort22.ByteBuilder.services.UserSevice;

import cohort22.ByteBuilder.dto.request.*;
import cohort22.ByteBuilder.dto.response.AddContactResponse;
import cohort22.ByteBuilder.dto.response.BlockedNumbersResponseDTO;
import cohort22.ByteBuilder.dto.response.LoginResponse;
import cohort22.ByteBuilder.dto.response.UserResponse;

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

}
