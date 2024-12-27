package cc.realtec.real.auth.server.utils.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;

@Slf4j
public class PasswordMatcherValidator implements ConstraintValidator<PasswordMatch, Object> {

    private String passwordFieldName;
    private String passwordConfirmationName;

    @Override
    public void initialize(PasswordMatch constraintAnnotation) {
        this.passwordFieldName = constraintAnnotation.passwordField();
        this.passwordConfirmationName = constraintAnnotation.passwordConfirmationField();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        try {
            Class<?> clazz = value.getClass();
            Field passwordField = clazz.getDeclaredField(passwordFieldName);
            Field passwordVerificationField = clazz.getDeclaredField(passwordConfirmationName);
            passwordField.setAccessible(true);
            passwordVerificationField.setAccessible(true);

            String password = (String) passwordField.get(value);
            String passwordVerification = (String) passwordVerificationField.get(value);

            return password != null && password.equals(passwordVerification);
        } catch (Exception e) {
            log.error("Error while validating password match", e);
        }
        return false;
    }
}
