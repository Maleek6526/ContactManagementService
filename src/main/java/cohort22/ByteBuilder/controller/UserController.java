package cohort22.ByteBuilder.controller;

import cohort22.ByteBuilder.dto.request.RegisterRequest;
import cohort22.ByteBuilder.dto.response.UserResponse;
import cohort22.ByteBuilder.exception.UserAlreadyExistsException;
import cohort22.ByteBuilder.services.UserSevice.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterRequest request) {
        try {
            UserResponse response = userService.registerUser(request);
            return ResponseEntity.ok(response);
        } catch (UserAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(Collections.singletonMap("message", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonMap("message", "Something went wrong!"));
        }
    }

    @GetMapping("/verify")
    public UserResponse verifyEmail(@RequestParam("token") String token) {
        return userService.verifyEmail(token);
    }

    @GetMapping("/{email}")
    public UserResponse getUserDetails(@PathVariable("email") String email) {
        return userService.getUserDetails(email);
    }
}
