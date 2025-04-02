package cohort22.ByteBuilder.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SpamContactResponse {
    private String name;
    private String phoneNumber;
    private String email;
}
