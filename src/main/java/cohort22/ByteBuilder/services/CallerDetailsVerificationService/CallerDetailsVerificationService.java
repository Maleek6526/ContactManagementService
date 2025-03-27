package cohort22.ByteBuilder.services.CallerDetailsVerificationService;

import cohort22.ByteBuilder.dto.response.CallerDetailsResponse;
import reactor.core.publisher.Mono;

public interface CallerDetailsVerificationService {
    Mono<CallerDetailsResponse> validatePhoneNumber(String phoneNumber);
}
