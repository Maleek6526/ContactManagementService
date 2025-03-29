package cohort22.ByteBuilder.dto.response;

import cohort22.ByteBuilder.data.model.Contact;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class DeleteContactResponse {
    private String message;
    private List<Contact> remainingContacts;
}