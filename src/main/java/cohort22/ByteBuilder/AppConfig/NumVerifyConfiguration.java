package cohort22.ByteBuilder.AppConfig;

import io.github.cdimascio.dotenv.Dotenv;
import lombok.Data;
import lombok.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


@Configuration
@Data
public class NumVerifyConfiguration {
    private static final Dotenv dotenv = Dotenv.load();
    private final String apiKey = dotenv.get("API_ACCESS_KEY");

}
