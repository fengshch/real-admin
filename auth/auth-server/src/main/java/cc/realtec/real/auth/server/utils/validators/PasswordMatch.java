package cc.realtec.real.auth.server.utils.validators;

import jakarta.validation.Constraint;

import java.lang.annotation.*;

@Constraint(validatedBy = PasswordMatcherValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PasswordMatch {
    String message() default "Passwords don't match";
    Class<?>[] groups() default {};
    Class<?>[] payload() default {};

    String passwordField();

    String passwordConfirmationField();
}
