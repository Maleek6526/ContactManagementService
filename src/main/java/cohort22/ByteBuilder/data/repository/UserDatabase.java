package cohort22.ByteBuilder.data.repository;

import cohort22.ByteBuilder.data.model.User;

import java.util.Optional;

public interface UserDatabase {
    Optional<User> findByEmail(String email);
    Optional<User> findByVerificationToken(String token);
    void save(User user);

}
