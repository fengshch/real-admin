package cc.realtec.real.auth.server.po;

import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.Table;
import java.io.Serializable;

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
@Table("oauth2_authorization_consent")
public class Oauth2AuthorizationConsentPO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    private String registeredClientId;

    @Id
    private String principalName;

    private String authorities;

}
