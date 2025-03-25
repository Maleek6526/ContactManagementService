package cohort22.ByteBuilder.services.UserSevice;

import cohort22.ByteBuilder.data.model.User;
import cohort22.ByteBuilder.data.repository.UserRepository;
import cohort22.ByteBuilder.dto.request.LoginRequest;
import cohort22.ByteBuilder.dto.request.RegisterRequest;
import cohort22.ByteBuilder.dto.response.LoginResponse;
import cohort22.ByteBuilder.dto.response.UserResponse;
import cohort22.ByteBuilder.exception.UserAlreadyExistsException;
import cohort22.ByteBuilder.exception.UserException;
import cohort22.ByteBuilder.exception.UserNotFoundException;
import cohort22.ByteBuilder.mapper.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

import java.time.LocalDateTime;


@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

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
        return response;
    }

}
