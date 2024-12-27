package cc.realtec.real.common.web.exception;

public class ForbiddenException extends Exception {
    public ForbiddenException() {
        super();
    }

    public ForbiddenException(String message, Throwable cause){
        super(message, cause);
    }

    public ForbiddenException(String message){
        super(message);
    }

    public ForbiddenException(Throwable cause){
        super(cause);
    }
}
