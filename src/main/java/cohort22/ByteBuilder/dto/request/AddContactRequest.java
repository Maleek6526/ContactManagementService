package cohort22.ByteBuilder.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class AddContactRequest {
    private String userEmail;
    private String name;
    private String email;
    private String phoneNumber;
}
