package cohort22.ByteBuilder.data.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@Document(collection = "spam_reports")
public class SpamReport {
    @Id
    private String phoneNumber;
    private int reportCount = 0;
    private List<String> reportedBy = new ArrayList<>();
}
