package cohort22.ByteBuilder.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class UpdateContactRequest {
    private String userEmail;
    private String oldPhoneNumber;
    private String newName;
    private String newPhoneNumber;
    private String newEmail;

}