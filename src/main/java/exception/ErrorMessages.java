package exception;

public enum ErrorMessages {
    NO_FACULTY_FOUND("Данный факультет не найден."),
    MISSING_REQUIRED_FIELD("Не достаточно данных. Проверьте правильность ввода."),
    RECORD_ALREADY_EXISTS("Запись уже содержится в базе данных.");



    private String errorMessage;

    ErrorMessages(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
