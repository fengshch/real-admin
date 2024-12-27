package cc.realtec.real.common.web.exception;

public class InvalidTokenException extends Exception {
    public InvalidTokenException() {
        super("Invalid token");
    }

    public InvalidTokenException(String message, Throwable cause){
        super(message, cause);
    }

    public InvalidTokenException(String message){
        super(message);
    }

    public InvalidTokenException(Throwable cause){
        super(cause);
    }
}
