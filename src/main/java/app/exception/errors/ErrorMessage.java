package app.exception.errors;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonRawValue;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
public class ErrorMessage {
    @JsonRawValue
    private LocalDateTime timestamp;
    @JsonRawValue
    private String message;

    @JsonIgnore
    private String details;
}
