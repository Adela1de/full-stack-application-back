package luiz.augusto.fullstackapplication.exceptions;

public class IncorrectCredentialException extends RuntimeException{
    public IncorrectCredentialException() {
    }

    public IncorrectCredentialException(String message) {
        super(message);
    }
}
