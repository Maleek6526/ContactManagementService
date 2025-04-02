package cohort22.ByteBuilder.controller;


import cohort22.ByteBuilder.dto.request.ReportSpamRequest;
import cohort22.ByteBuilder.dto.response.SpamContactResponse;
import cohort22.ByteBuilder.exception.UserException;
import cohort22.ByteBuilder.services.UserSevice.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/spam")
@CrossOrigin(origins = "http://localhost:5173")
public class SpamController {
    @Autowired
    private UserService userService;

    @PostMapping("/report")
    public ResponseEntity<String> reportSpam(@RequestBody ReportSpamRequest request) {
        try {
            userService.reportSpam(request);
            return ResponseEntity.ok("Spam report submitted successfully.");
        } catch (UserException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/check/{phoneNumber}")
    public ResponseEntity<?> checkSpam(@PathVariable("phoneNumber") String phoneNumber) {
        try {
            boolean isSpam = userService.isNumberSpam(phoneNumber);
            return ResponseEntity.ok(isSpam);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Request: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/spam-contacts")
    public List<SpamContactResponse> getSpamContacts() {
        return userService.getAllSpamContactList();
    }

}
