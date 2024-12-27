package cc.realtec.real.auth.server.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class SysUserRequest {

    private String username;

    private String password;

    private String name;

    private String nickname;

    private String gender;

    private LocalDate birthdate;

    private String avatarName;

    private String email;

    private Boolean emailVerified;

    private String phoneNumber;

    private Boolean phoneNumberVerified;

    private Boolean accountNonExpired;

    private Boolean accountNonLocked;

    private Boolean credentialsNonExpired;

    private Boolean enabled;

    private Boolean mfa;

    private Boolean system;
}