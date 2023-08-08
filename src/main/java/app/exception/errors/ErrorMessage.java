package app.exception.errors;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonRawValue;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ErrorMessage {
    @JsonRawValue
    private LocalDateTime timestamp;
    @JsonRawValue
    private String message;

    @JsonIgnore
    private String details;

    public ErrorMessage(LocalDateTime timestamp, String message, String details) {
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }

    public ErrorMessage(String message) {
        this(LocalDateTime.now(), message, null);
    }
}
