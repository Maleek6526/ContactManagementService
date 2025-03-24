package cohort22.ByteBuilder.dto.response;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class UserResponse {
    private String email;
    private String name;
    private String phoneNumber;
    private boolean isVerified;
}
