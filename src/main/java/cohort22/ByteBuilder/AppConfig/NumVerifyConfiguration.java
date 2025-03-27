package cohort22.ByteBuilder.AppConfig;

import io.github.cdimascio.dotenv.Dotenv;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


@Configuration
@Data
@PropertySource("classpath:.env")
public class NumVerifyConfiguration {
    @Value("${API_ACCESS_KEY}")
    private String apiKey;


}
