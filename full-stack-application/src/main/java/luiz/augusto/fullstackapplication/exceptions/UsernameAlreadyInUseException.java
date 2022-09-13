package luiz.augusto.fullstackapplication.exceptions;

public class UsernameAlreadyInUseException extends RuntimeException{
    public UsernameAlreadyInUseException() {
    }

    public UsernameAlreadyInUseException(String message) {
        super(message);
    }
}
