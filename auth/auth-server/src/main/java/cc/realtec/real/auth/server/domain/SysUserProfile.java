package cc.realtec.real.auth.server.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class SysUserProfile {

    private String username;

    private String name;

    private String nickname;

    private String gender;

    private LocalDate birthdate;

    private String avatarName;

    private String avatarUrl;

    private String email;

    private String phoneNumber;

    private Boolean mfa;

    private Boolean system;
}