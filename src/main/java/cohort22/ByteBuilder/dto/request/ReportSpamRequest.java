package cohort22.ByteBuilder.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ReportSpamRequest {
    private String reporterEmail;
    private String phoneNumber;
}
