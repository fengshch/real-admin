package cc.realtec.real.auth.server.domain;

import cc.realtec.real.auth.server.utils.validators.PasswordMatch;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
@PasswordMatch(passwordField = "password", passwordConfirmationField = "confirmPassword")
public class ChangePasswordRequest {

    @NotNull
    @Length(min = 8)
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).*$", message = "Password must contain at least one uppercase letter, one lowercase letter and one number.")
    private String password;

    private String confirmPassword;

    private String oldPassword;
}
