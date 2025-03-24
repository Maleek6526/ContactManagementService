package cohort22.ByteBuilder.data.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@Document(collection = "users")
public class User {
    @Id
    private String email;
    private String name;
    private String phoneNumber;
    private String password;
    private boolean isVerified;
    private String verificationToken;
    private LocalDateTime tokenExpiryDate;

}
