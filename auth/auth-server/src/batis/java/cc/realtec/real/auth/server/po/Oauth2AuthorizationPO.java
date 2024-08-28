package cc.realtec.real.auth.server.po;

import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.Table;
import java.io.Serializable;
import java.sql.Timestamp;

import java.io.Serial;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *  实体类。
 *
 * @author bill
 * @since 2024-08-20
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("oauth2_authorization")
public class Oauth2AuthorizationPO implements Serializable {

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

    private Timestamp authorizationCodeIssuedAt;

    private Timestamp authorizationCodeExpiresAt;

    private String authorizationCodeMetadata;

    private String accessTokenValue;

    private Timestamp accessTokenIssuedAt;

    private Timestamp accessTokenExpiresAt;

    private String accessTokenMetadata;

    private String accessTokenType;

    private String accessTokenScopes;

    private String oidcIdTokenValue;

    private Timestamp oidcIdTokenIssuedAt;

    private Timestamp oidcIdTokenExpiresAt;

    private String oidcIdTokenMetadata;

    private String refreshTokenValue;

    private Timestamp refreshTokenIssuedAt;

    private Timestamp refreshTokenExpiresAt;

    private String refreshTokenMetadata;

    private String userCodeValue;

    private Timestamp userCodeIssuedAt;

    private Timestamp userCodeExpiresAt;

    private String userCodeMetadata;

    private String deviceCodeValue;

    private Timestamp deviceCodeIssuedAt;

    private Timestamp deviceCodeExpiresAt;

    private String deviceCodeMetadata;

}
