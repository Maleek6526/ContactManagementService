package cohort22.ByteBuilder.controller;

import cohort22.ByteBuilder.dto.request.*;
import cohort22.ByteBuilder.dto.response.*;
import cohort22.ByteBuilder.exception.DuplicateContactException;
import cohort22.ByteBuilder.exception.UserAlreadyExistsException;
import cohort22.ByteBuilder.exception.UserException;
import cohort22.ByteBuilder.exception.UserNotFoundException;
import cohort22.ByteBuilder.services.UserSevice.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

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

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest request) {
        try {
            LoginResponse response = userService.login(request);
            return ResponseEntity.ok(response);
        } catch (UserNotFoundException | IllegalStateException | IllegalArgumentException e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    @PostMapping("/add-contact")
    public ResponseEntity<AddContactResponse> addContact(@RequestBody AddContactRequest request) {
        try {
            AddContactResponse addContactResponse = userService.addContact(request);
            return ResponseEntity.ok(addContactResponse);
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (DuplicateContactException e) {
            return ResponseEntity.badRequest().body(null);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(null);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateContact(@RequestBody UpdateContactRequest request) {
        try {
            UpdateContactResponse response = userService.updateContact(request);
            return ResponseEntity.ok(response);
        } catch (UserNotFoundException | UserException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("An unexpected error occurred.");
        }
    }




    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteContact(@RequestBody DeleteContactRequest request) {
        try {
            DeleteContactResponse response = userService.deleteContact(request);
            return ResponseEntity.ok(response);
        } catch (UserNotFoundException | UserException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("An unexpected error occurred.");
        }
    }

    @DeleteMapping("/delete-all/{addedBy}")
    public ResponseEntity<String> deleteAllContacts(@PathVariable("addedBy") String addedBy) {
        userService.deleteAllContacts(addedBy);
        return ResponseEntity.ok("All contacts for user " + addedBy + " deleted successfully.");
    }

    @GetMapping("/view/{email}")
    public ResponseEntity<?> viewAllContacts(@PathVariable ("email") String email) {
        try {
            return ResponseEntity.ok(userService.viewAllSavedContacts(email));
        } catch (UserNotFoundException | UserException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("An unexpected error occurred.");
        }
    }


}
