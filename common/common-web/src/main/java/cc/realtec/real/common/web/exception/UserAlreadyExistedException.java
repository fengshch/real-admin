package cc.realtec.real.common.web.exception;

public class UserAlreadyExistedException extends Exception {
    public UserAlreadyExistedException() {
        super();
    }

    public UserAlreadyExistedException(String message, Throwable cause){
        super(message, cause);
    }

    public UserAlreadyExistedException(String message){
        super(message);
    }

    public UserAlreadyExistedException(Throwable cause){
        super(cause);
    }
}
