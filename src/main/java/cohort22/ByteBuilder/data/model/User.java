package cohort22.ByteBuilder.data.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

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
    private boolean isVerified = false;
    private String verificationToken;
    private LocalDateTime tokenExpiryDate;
    private Set<Contact> contacts = new HashSet<>();
    private Set<Contact> blockedNumbers = new HashSet<>();
}
