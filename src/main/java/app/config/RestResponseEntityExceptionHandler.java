package app.config;

import app.exception.LessonException;
import app.exception.errors.ErrorMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.Arrays;


@ControllerAdvice
@Slf4j
public class RestResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ErrorMessage> handleAllExceptions(Exception ex, WebRequest request) {
        ErrorMessage message = getErrorMessage(ex.getMessage(), request);
        String stackTrace = ExceptionUtils.getStackTrace(ex);
        String[] stackTraceLines = stackTrace.split("\n");
        int numLinesToInclude = Math.min(stackTraceLines.length, 25);
        String shortenedStackTrace = String.join("\n", Arrays.copyOfRange(stackTraceLines, 0, numLinesToInclude));

        log.error(message.getMessage() + "\n" + shortenedStackTrace);
        return new ResponseEntity<>(message, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(BadCredentialsException.class)
    public final ResponseEntity<ErrorMessage> handleBadCredentialsException(BadCredentialsException ex, WebRequest request) {
        ErrorMessage message = getErrorMessage(ex.getMessage(), request);

        // Extract the first 15 lines from the stack trace
        String stackTrace = ExceptionUtils.getStackTrace(ex);
        String[] stackTraceLines = stackTrace.split("\n");
        int numLinesToInclude = Math.min(stackTraceLines.length, 15);
        String shortenedStackTrace = String.join("\n", Arrays.copyOfRange(stackTraceLines, 0, numLinesToInclude));

        log.error(message.getMessage() + "\n" + shortenedStackTrace);
        return new ResponseEntity<>(message, new HttpHeaders(), HttpStatus.FORBIDDEN);
    }

    private ErrorMessage getErrorMessage(String ex, WebRequest request) {
        return ErrorMessage.builder()
                .message(ex)
                .timestamp(LocalDateTime.now())
                .details(request.getDescription(false))
                .build();
    }

    @ExceptionHandler(LessonException.class)
    public final ResponseEntity<ErrorMessage> handleLessonException(LessonException ex, WebRequest request) {
        String combinedMessage = String.join(",", ex.getErrorMessages().stream().map(ErrorMessage::getMessage).toList());
        log.error(combinedMessage);
        return new ResponseEntity<>(ErrorMessage.builder()
                .message(combinedMessage)
                .build(), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

}
