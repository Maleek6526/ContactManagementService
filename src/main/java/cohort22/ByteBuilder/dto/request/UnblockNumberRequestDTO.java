package cohort22.ByteBuilder.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class UnblockNumberRequestDTO {
    private String userEmail;
    private String phoneNumber;
}
