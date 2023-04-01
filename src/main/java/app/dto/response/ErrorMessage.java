package app.dto.response;

import app.entity.Interfaces.ResponseInterface;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor

public class ErrorMessage implements ResponseInterface {
    private LocalDateTime timestamp;
    private String message;
    private String details;

    public ErrorMessage(LocalDateTime timestamp, String message, String details) {
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }
}
