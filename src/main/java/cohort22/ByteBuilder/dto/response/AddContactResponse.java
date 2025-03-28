package cohort22.ByteBuilder.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class AddContactResponse {
    private String name;
    private String email;
    private String phoneNumber;
    private String addedBy;
    private boolean isSpam;
}
