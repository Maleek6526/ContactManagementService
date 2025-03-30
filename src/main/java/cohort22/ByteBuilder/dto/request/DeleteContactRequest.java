package cohort22.ByteBuilder.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class DeleteContactRequest {
    private String userEmail;
    private List<String> phoneNumbers;
}