package cc.realtec.real.auth.server.po;

import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.Table;
import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDateTime;

import java.io.Serial;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *  实体类。
 *
 * @author bill
 * @since 2024-10-24
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("sys_user")
public class SysUserPo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    private String username;

    private String password;

    private String name;

    private String nickname;

    private String gender;

    private Date birthdate;

    private String avatarName;

    private String email;

    private Boolean emailVerified;

    private String emailToken;

    private String passwordToken;

    private String phoneNumber;

    private Boolean phoneNumberVerified;

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
