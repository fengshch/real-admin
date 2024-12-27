package cc.realtec.real.auth.server.po;

import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.Table;
import java.io.Serializable;
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
@Table("oauth2_authorization")
public class Oauth2AuthorizationPo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    private String registeredClientId;

    private String principalName;

    private String authorizationGrantType;

    private String authorizedScopes;

    private String attributes;

    private String state;

    private String authorizationCodeValue;

    private LocalDateTime authorizationCodeIssuedAt;

    private LocalDateTime authorizationCodeExpiresAt;

    private String authorizationCodeMetadata;

    private String accessTokenValue;

    private LocalDateTime accessTokenIssuedAt;

    private LocalDateTime accessTokenExpiresAt;

    private String accessTokenMetadata;

    private String accessTokenType;

    private String accessTokenScopes;

    private String oidcIdTokenValue;

    private LocalDateTime oidcIdTokenIssuedAt;

    private LocalDateTime oidcIdTokenExpiresAt;

    private String oidcIdTokenMetadata;

    private String refreshTokenValue;

    private LocalDateTime refreshTokenIssuedAt;

    private LocalDateTime refreshTokenExpiresAt;

    private String refreshTokenMetadata;

    private String userCodeValue;

    private LocalDateTime userCodeIssuedAt;

    private LocalDateTime userCodeExpiresAt;

    private String userCodeMetadata;

    private String deviceCodeValue;

    private LocalDateTime deviceCodeIssuedAt;

    private LocalDateTime deviceCodeExpiresAt;

    private String deviceCodeMetadata;

}
