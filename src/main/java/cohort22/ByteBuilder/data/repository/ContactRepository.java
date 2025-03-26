package cohort22.ByteBuilder.data.repository;

import cohort22.ByteBuilder.data.model.Contact;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ContactRepository extends MongoRepository<Contact, String> {
    Optional<Contact> findByPhoneNumber(String phoneNumber);
}
