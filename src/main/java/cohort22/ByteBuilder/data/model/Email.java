package cohort22.ByteBuilder.data.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "emails")
public class Email {
    private String userEmail;
    private String OTP;
    private LocalDateTime OTPExpiringTime;
}
