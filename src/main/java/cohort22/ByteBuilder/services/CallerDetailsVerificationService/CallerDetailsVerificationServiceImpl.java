package cohort22.ByteBuilder.services.CallerDetailsVerificationService;

import cohort22.ByteBuilder.AppConfig.NumVerifyConfiguration;
import cohort22.ByteBuilder.dto.response.CallerDetailsResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class CallerDetailsVerificationServiceImpl implements CallerDetailsVerificationService{

    @Autowired
    private WebClient.Builder webClientBuilder;
    @Autowired
    private NumVerifyConfiguration numVerifyConfiguration;

    @Override
    public Mono<CallerDetailsResponse> validatePhoneNumber(String phoneNumber) {
        String apiKey = numVerifyConfiguration.getApiKey();

        return webClientBuilder.baseUrl("http://apilayer.net/api")
                .build()
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path("/validate")
                        .queryParam("access_key", apiKey)
                        .queryParam("number", phoneNumber)
                        .build())
                .retrieve()
                .onStatus(status -> status.is4xxClientError() || status.is5xxServerError(),
                        response -> Mono.error(new RuntimeException("API error: " + response.statusCode())))
                .bodyToMono(String.class)
                .doOnNext(response -> {
                    System.out.println("Raw API Response: " + response);
                })
                .map(response -> {
                    try {
                        ObjectMapper objectMapper = new ObjectMapper();
                        JsonNode rootNode = objectMapper.readTree(response);

                        if (rootNode.has("success") && !rootNode.get("success").asBoolean()) {
                            JsonNode errorNode = rootNode.get("error");
                            String errorMessage = errorNode != null ? errorNode.get("info").asText(): "Unknown error";
                            throw new RuntimeException(errorMessage);
                        }
                        return new ObjectMapper().readValue(response, CallerDetailsResponse.class);
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException("Error parsing API response", e);
                    }
           });
    }

}
