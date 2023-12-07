package app.exception;


import app.exception.errors.ErrorMessage;
import lombok.Getter;

import java.util.List;

@Getter
public class LessonException extends RuntimeException {

    private List<ErrorMessage> errorMessages;

    public LessonException(String message) {
        super(message);
    }

    public LessonException(List<ErrorMessage> errorMessages) {
        this.errorMessages = errorMessages;
    }

}
