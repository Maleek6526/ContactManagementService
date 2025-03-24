package cohort22.ByteBuilder.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class RegisterRequest {
    private String name;
    private String email;
    private String phoneNumber;
    private String password;
}
