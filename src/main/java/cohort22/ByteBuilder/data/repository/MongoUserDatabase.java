package cohort22.ByteBuilder.data.repository;

import cohort22.ByteBuilder.data.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class MongoUserDatabase implements UserDatabase {
    @Autowired
    private UserRepository userRepository;

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findById(email);
    }

    @Override
    public Optional<User> findByVerificationToken(String token) {
        return userRepository.findByVerificationToken(token);
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }
}
