package cc.realtec.real.auth.server.po.table;

import com.mybatisflex.core.query.QueryColumn;
import com.mybatisflex.core.table.TableDef;

import java.io.Serial;

/**
 *  表定义层。
 *
 * @author bill
 * @since 2024-10-24
 */
public class Oauth2AuthorizationTableDef extends TableDef {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    public static final Oauth2AuthorizationTableDef OAUTH2_AUTHORIZATION_PO = new Oauth2AuthorizationTableDef();

    
    public final QueryColumn ID = new QueryColumn(this, "id");

    
    public final QueryColumn STATE = new QueryColumn(this, "state");

    
    public final QueryColumn ATTRIBUTES = new QueryColumn(this, "attributes");

    
    public final QueryColumn PRINCIPAL_NAME = new QueryColumn(this, "principal_name");

    
    public final QueryColumn USER_CODE_VALUE = new QueryColumn(this, "user_code_value");

    
    public final QueryColumn ACCESS_TOKEN_TYPE = new QueryColumn(this, "access_token_type");

    
    public final QueryColumn DEVICE_CODE_VALUE = new QueryColumn(this, "device_code_value");

    
    public final QueryColumn ACCESS_TOKEN_VALUE = new QueryColumn(this, "access_token_value");

    
    public final QueryColumn AUTHORIZED_SCOPES = new QueryColumn(this, "authorized_scopes");

    
    public final QueryColumn OIDC_ID_TOKEN_VALUE = new QueryColumn(this, "oidc_id_token_value");

    
    public final QueryColumn USER_CODE_ISSUED_AT = new QueryColumn(this, "user_code_issued_at");

    
    public final QueryColumn USER_CODE_METADATA = new QueryColumn(this, "user_code_metadata");

    
    public final QueryColumn ACCESS_TOKEN_SCOPES = new QueryColumn(this, "access_token_scopes");

    
    public final QueryColumn REFRESH_TOKEN_VALUE = new QueryColumn(this, "refresh_token_value");

    
    public final QueryColumn USER_CODE_EXPIRES_AT = new QueryColumn(this, "user_code_expires_at");

    
    public final QueryColumn DEVICE_CODE_ISSUED_AT = new QueryColumn(this, "device_code_issued_at");

    
    public final QueryColumn DEVICE_CODE_METADATA = new QueryColumn(this, "device_code_metadata");

    
    public final QueryColumn REGISTERED_CLIENT_ID = new QueryColumn(this, "registered_client_id");

    
    public final QueryColumn ACCESS_TOKEN_ISSUED_AT = new QueryColumn(this, "access_token_issued_at");

    
    public final QueryColumn ACCESS_TOKEN_METADATA = new QueryColumn(this, "access_token_metadata");

    
    public final QueryColumn DEVICE_CODE_EXPIRES_AT = new QueryColumn(this, "device_code_expires_at");

    
    public final QueryColumn OIDC_ID_TOKEN_ISSUED_AT = new QueryColumn(this, "oidc_id_token_issued_at");

    
    public final QueryColumn OIDC_ID_TOKEN_METADATA = new QueryColumn(this, "oidc_id_token_metadata");

    
    public final QueryColumn ACCESS_TOKEN_EXPIRES_AT = new QueryColumn(this, "access_token_expires_at");

    
    public final QueryColumn OIDC_ID_TOKEN_EXPIRES_AT = new QueryColumn(this, "oidc_id_token_expires_at");

    
    public final QueryColumn REFRESH_TOKEN_ISSUED_AT = new QueryColumn(this, "refresh_token_issued_at");

    
    public final QueryColumn REFRESH_TOKEN_METADATA = new QueryColumn(this, "refresh_token_metadata");

    
    public final QueryColumn REFRESH_TOKEN_EXPIRES_AT = new QueryColumn(this, "refresh_token_expires_at");

    
    public final QueryColumn AUTHORIZATION_CODE_VALUE = new QueryColumn(this, "authorization_code_value");

    
    public final QueryColumn AUTHORIZATION_GRANT_TYPE = new QueryColumn(this, "authorization_grant_type");

    
    public final QueryColumn AUTHORIZATION_CODE_ISSUED_AT = new QueryColumn(this, "authorization_code_issued_at");

    
    public final QueryColumn AUTHORIZATION_CODE_METADATA = new QueryColumn(this, "authorization_code_metadata");

    
    public final QueryColumn AUTHORIZATION_CODE_EXPIRES_AT = new QueryColumn(this, "authorization_code_expires_at");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{ID, REGISTERED_CLIENT_ID, PRINCIPAL_NAME, AUTHORIZATION_GRANT_TYPE, AUTHORIZED_SCOPES, ATTRIBUTES, STATE, AUTHORIZATION_CODE_VALUE, AUTHORIZATION_CODE_ISSUED_AT, AUTHORIZATION_CODE_EXPIRES_AT, AUTHORIZATION_CODE_METADATA, ACCESS_TOKEN_VALUE, ACCESS_TOKEN_ISSUED_AT, ACCESS_TOKEN_EXPIRES_AT, ACCESS_TOKEN_METADATA, ACCESS_TOKEN_TYPE, ACCESS_TOKEN_SCOPES, OIDC_ID_TOKEN_VALUE, OIDC_ID_TOKEN_ISSUED_AT, OIDC_ID_TOKEN_EXPIRES_AT, OIDC_ID_TOKEN_METADATA, REFRESH_TOKEN_VALUE, REFRESH_TOKEN_ISSUED_AT, REFRESH_TOKEN_EXPIRES_AT, REFRESH_TOKEN_METADATA, USER_CODE_VALUE, USER_CODE_ISSUED_AT, USER_CODE_EXPIRES_AT, USER_CODE_METADATA, DEVICE_CODE_VALUE, DEVICE_CODE_ISSUED_AT, DEVICE_CODE_EXPIRES_AT, DEVICE_CODE_METADATA};

    public Oauth2AuthorizationTableDef() {
        super("", "oauth2_authorization");
    }

    private Oauth2AuthorizationTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public Oauth2AuthorizationTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new Oauth2AuthorizationTableDef("", "oauth2_authorization", alias));
    }

}
