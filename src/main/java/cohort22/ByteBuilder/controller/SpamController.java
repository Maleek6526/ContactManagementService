package cohort22.ByteBuilder.controller;


import cohort22.ByteBuilder.dto.request.ReportSpamRequest;
import cohort22.ByteBuilder.services.UserSevice.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/spam")
@CrossOrigin(origins = "http://localhost:5173")
public class SpamController {
    @Autowired
    private UserService userService;

    @PostMapping("/report")
    public ResponseEntity<String> reportSpam(@RequestBody ReportSpamRequest request) {
        userService.reportSpam(request);
        return ResponseEntity.ok("Spam report submitted successfully.");
    }

    @GetMapping("/check/{phoneNumber}")
    public ResponseEntity<Boolean> checkSpam(@PathVariable ("phoneNumber")String phoneNumber) {
        boolean isSpam = userService.isNumberSpam(phoneNumber);
        return ResponseEntity.ok(isSpam);
    }
}
