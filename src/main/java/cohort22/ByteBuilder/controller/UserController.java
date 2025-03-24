package cohort22.ByteBuilder.controller;

import cohort22.ByteBuilder.dto.request.RegisterRequest;
import cohort22.ByteBuilder.dto.response.UserResponse;
import cohort22.ByteBuilder.services.UserSevice.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public UserResponse registerUser(@Valid @RequestBody RegisterRequest request) {
        return userService.registerUser(request);
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
