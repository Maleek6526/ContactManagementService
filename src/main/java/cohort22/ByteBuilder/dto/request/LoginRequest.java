package cohort22.ByteBuilder.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class LoginRequest{
    @NotBlank
    private String email;
    @NotBlank
    private String password;

}
