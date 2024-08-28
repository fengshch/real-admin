package cc.realtec.real.auth.common.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
public class SysUser {

    private String id;

    private String username;

    private String passwordPlain;

    private String password;

    private String email;

    private Boolean emailVerified;

    private String nickname;

    private String firstName;

    private String lastName;

    private String phoneNumber;

    private Boolean accountNonExpired;

    private Boolean accountNonLocked;

    private Boolean credentialsNonExpired;

    private Boolean enabled;

    private String avatarUrl;

    private Timestamp createdAt;

    private Timestamp updatedAt;
}