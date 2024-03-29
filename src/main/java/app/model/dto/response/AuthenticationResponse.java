package app.model.dto.response;

import app.model.dto.TokenDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {

    @JsonProperty("access_token")
    private TokenDto accessToken;
    @JsonProperty("refresh_token")
    private TokenDto refreshToken;
    @JsonProperty("name")
    private String name;
    @JsonProperty("lastname")
    private String lastname;
}