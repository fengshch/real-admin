package cc.realtec.real.common.web.exception;

public class ReCaptchaUnavailableException extends Exception {
    public ReCaptchaUnavailableException() {
        super();
    }

    public ReCaptchaUnavailableException(String message, Throwable cause){
        super(message, cause);
    }

    public ReCaptchaUnavailableException(String message){
        super(message);
    }

    public ReCaptchaUnavailableException(Throwable cause){
        super(cause);
    }
}
