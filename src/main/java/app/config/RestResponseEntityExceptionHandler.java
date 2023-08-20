package app.config;

import app.exception.errors.ErrorMessage;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;


@ControllerAdvice
public class RestResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ErrorMessage> handleAllExceptions(Exception ex, WebRequest request) {
        ErrorMessage message = getErrorMessage(ex.getMessage(), request);
        return new ResponseEntity<>(message, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public final ResponseEntity<ErrorMessage> handleAllExceptions(BadCredentialsException ex, WebRequest request) {
        ErrorMessage message = getErrorMessage(ex.getMessage(), request);
        return new ResponseEntity<>(message, new HttpHeaders(), HttpStatus.FORBIDDEN);
    }

    private ErrorMessage getErrorMessage(String ex, WebRequest request) {
        return ErrorMessage.builder()
                .message(ex)
                .timestamp(LocalDateTime.now())
                .details(request.getDescription(false))
                .build();
    }

}
