package cc.realtec.real.common.web.exception;

public class InvalidPasswordException extends Exception {
    public InvalidPasswordException() {
        super("Invalid password");
    }

    public InvalidPasswordException(String message, Throwable cause){
        super(message, cause);
    }

    public InvalidPasswordException(String message){
        super(message);
    }

    public InvalidPasswordException(Throwable cause){
        super(cause);
    }
}
