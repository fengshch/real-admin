package cc.realtec.real.common.web.exception;

public class EmailNotFoundException extends Exception {
    public EmailNotFoundException() {
        super("Email not found");
    }

    public EmailNotFoundException(String message, Throwable cause){
        super(message, cause);
    }

    public EmailNotFoundException(String message){
        super(message);
    }

    public EmailNotFoundException(Throwable cause){
        super(cause);
    }
}
