package duongd.example.domain.usecase.base.exception;

public class AppException extends Exception {

    public AppException() {
    }

    private int errorCode;

    public AppException(String message, int errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public AppException(String message) {
        super(message);
    }

    public AppException(String message, Throwable cause) {
        super(message, cause);
    }

    public int getErrorCode() {
        return errorCode;
    }
}
