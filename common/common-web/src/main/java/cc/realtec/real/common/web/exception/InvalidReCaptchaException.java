package cc.realtec.real.common.web.exception;

public class InvalidReCaptchaException extends Exception {
    public InvalidReCaptchaException() {
        super();
    }

    public InvalidReCaptchaException(String message, Throwable cause){
        super(message, cause);
    }

    public InvalidReCaptchaException(String message){
        super(message);
    }

    public InvalidReCaptchaException(Throwable cause){
        super(cause);
    }
}
