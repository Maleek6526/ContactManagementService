package cohort22.ByteBuilder.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class CallerDetailsResponse {
    @JsonProperty("valid")
    private boolean valid;

    @JsonProperty("number")
    private String number;

    @JsonProperty("local_format")
    private String localFormat;

    @JsonProperty("international_format")
    private String internationalFormat;

    @JsonProperty("country_prefix")
    private String countryPrefix;

    @JsonProperty("country_code")
    private String countryCode;

    @JsonProperty("country_name")
    private String countryName;

    @JsonProperty("location")
    private String location;

    @JsonProperty("carrier")
    private String carrier;

    @JsonProperty("line_type")
    private String lineType;

}
