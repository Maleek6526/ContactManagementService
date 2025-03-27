package cohort22.ByteBuilder.controller;

import cohort22.ByteBuilder.dto.response.CallerDetailsResponse;
import cohort22.ByteBuilder.services.CallerDetailsVerificationService.CallerDetailsVerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/contact-verification")
@CrossOrigin(origins = "http://localhost:5173")
public class CallerDetailsVerificationController {
    @Autowired
    private CallerDetailsVerificationService callerDetailsVerificationService;

    @GetMapping("{number}")
    public Mono<CallerDetailsResponse> validatePhoneNumber(@PathVariable("number") String phoneNumber){
        return callerDetailsVerificationService.validatePhoneNumber(phoneNumber);
    }


}
