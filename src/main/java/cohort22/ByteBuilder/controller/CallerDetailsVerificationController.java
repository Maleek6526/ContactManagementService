package cohort22.ByteBuilder.controller;

import cohort22.ByteBuilder.services.CallerDetailsVerificationService.CallerDetailsVerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/contact-verification")
@CrossOrigin(origins = "http://localhost:5173")
public class CallerDetailsVerificationController {
    @Autowired
    private CallerDetailsVerificationService callerDetailsVerificationService;


}
