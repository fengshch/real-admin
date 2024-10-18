package cc.realtec.real.auth.common.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class SysUserDto {

    private String id;

    private String username;

    private String password;

    private String name;

    private String nickname;

    private String gender;

    private LocalDate birthdate;

    private String picture;

    private String email;

    private Boolean emailVerified;

    private String phoneNumber;

    private String phoneNumberVerified;

    private Boolean accountNonExpired;

    private Boolean accountNonLocked;

    private Boolean credentialsNonExpired;

    private Boolean enabled;

    private Boolean mfa;

    private Boolean system;

    private String createdBy;

    private String updatedBy;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}