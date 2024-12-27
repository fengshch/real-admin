package cc.realtec.real.common.web.exception;

public class ResourceNotFoundException extends Exception {
    public ResourceNotFoundException() {
        super();
    }

    public ResourceNotFoundException(String message, Throwable cause){
        super(message, cause);
    }

    public ResourceNotFoundException(String message){
        super(message);
    }

    public ResourceNotFoundException(Throwable cause){
        super(cause);
    }
}
