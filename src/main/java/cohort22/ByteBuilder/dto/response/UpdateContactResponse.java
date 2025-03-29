package cohort22.ByteBuilder.dto.response;

import cohort22.ByteBuilder.data.model.Contact;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Setter
@Getter
@AllArgsConstructor
public class UpdateContactResponse {
    private String message;
    private List<Contact> updatedContacts;
}
