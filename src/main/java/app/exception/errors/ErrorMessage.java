package app.exception.errors;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter`
@ToString
@Builder
@AllArgsConstructor
public class ErrorMessage {

    private String message;

    @JsonIgnore
    private LocalDateTime timestamp;

    @JsonIgnore
    private String details;
}
