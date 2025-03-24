package cohort22.ByteBuilder.services.UserSevice;

import cohort22.ByteBuilder.data.model.User;
import cohort22.ByteBuilder.data.repository.UserRepository;
import cohort22.ByteBuilder.dto.request.RegisterRequest;
import cohort22.ByteBuilder.dto.response.UserResponse;
import cohort22.ByteBuilder.exception.UserException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class NewUserServiceImplTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    private RegisterRequest testUser;

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
        testUser = new RegisterRequest();
        testUser.setName("Adewale Maleek");
        testUser.setEmail("adewalemaleekayobami2528@gmail.com");
        testUser.setPhoneNumber("09012345678");
        testUser.setPassword("password123");
    }

    @Test
    void testRegisterUser() {
        UserResponse response = userService.registerUser(testUser);
        assertNotNull(response);
        assertEquals(testUser.getEmail(), response.getEmail());
        assertFalse(response.isVerified());
    }

    @Test
    void testRegisterDuplicateUser() {
        userService.registerUser(testUser);
        Exception exception = assertThrows(UserException.class, () -> userService.registerUser(testUser));
        assertEquals("Email already registered!", exception.getMessage());
    }

    @Test
    void testVerifyEmail() {
        UserResponse response = userService.registerUser(testUser);
        Optional<User> userOpt = userRepository.findById(testUser.getEmail());
        assertTrue(userOpt.isPresent());
        String token = userOpt.get().getVerificationToken();
        UserResponse verifiedResponse = userService.verifyEmail(token);
        assertTrue(verifiedResponse.isVerified());
    }

    @Test
    void testVerifyInvalidToken() {
        Exception exception = assertThrows(UserException.class, () -> userService.verifyEmail("invalid_token"));
        assertEquals("Invalid or expired token!", exception.getMessage());
    }
}