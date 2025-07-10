package cohort22.ByteBuilder.data.repository;


import cohort22.ByteBuilder.data.model.Email;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmailRepository {
    Optional<Email> findByOTP(String otp);
}
