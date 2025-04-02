package cohort22.ByteBuilder.data.repository;

import cohort22.ByteBuilder.data.model.SpamReport;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface SpamReportRepository extends MongoRepository<SpamReport, String> {
    Optional<SpamReport> findById(String phoneNumber);
}
