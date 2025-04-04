package cohort22.ByteBuilder.data.repository;

import cohort22.ByteBuilder.data.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByVerificationToken(String token);
    Optional<User> findByEmail(String email);
    Optional<User> findByPhoneNumber(String phoneNumber);
    @Query("{ 'contacts.phoneNumber' : ?0 }")
    List<User> findByContactsPhoneNumber(String phoneNumber);
    List<User>findAll();
}
