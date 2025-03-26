package cohort22.ByteBuilder.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
public class BlockedNumbersResponseDTO {
    private Set<String> blockedNumbers;
}
