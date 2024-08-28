package cc.realtec.real.auth.server.po.table;

import com.mybatisflex.core.query.QueryColumn;
import com.mybatisflex.core.table.TableDef;

import java.io.Serial;

/**
 *  表定义层。
 *
 * @author bill
 * @since 2024-08-20
 */
public class Oauth2RegisteredClientTableDef extends TableDef {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    public static final Oauth2RegisteredClientTableDef OAUTH2_REGISTERED_CLIENT_PO = new Oauth2RegisteredClientTableDef();

    
    public final QueryColumn ID = new QueryColumn(this, "id");

    
    public final QueryColumn SCOPES = new QueryColumn(this, "scopes");

    
    public final QueryColumn CLIENT_ID = new QueryColumn(this, "client_id");

    
    public final QueryColumn CLIENT_NAME = new QueryColumn(this, "client_name");

    
    public final QueryColumn CLIENT_SECRET = new QueryColumn(this, "client_secret");

    
    public final QueryColumn REDIRECT_URIS = new QueryColumn(this, "redirect_uris");

    
    public final QueryColumn TOKEN_SETTINGS = new QueryColumn(this, "token_settings");

    
    public final QueryColumn CLIENT_SETTINGS = new QueryColumn(this, "client_settings");

    
    public final QueryColumn CLIENT_ID_ISSUED_AT = new QueryColumn(this, "client_id_issued_at");

    
    public final QueryColumn CLIENT_SECRET_EXPIRES_AT = new QueryColumn(this, "client_secret_expires_at");

    
    public final QueryColumn POST_LOGOUT_REDIRECT_URIS = new QueryColumn(this, "post_logout_redirect_uris");

    
    public final QueryColumn AUTHORIZATION_GRANT_TYPES = new QueryColumn(this, "authorization_grant_types");

    
    public final QueryColumn CLIENT_AUTHENTICATION_METHODS = new QueryColumn(this, "client_authentication_methods");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{ID, CLIENT_ID, CLIENT_ID_ISSUED_AT, CLIENT_SECRET, CLIENT_SECRET_EXPIRES_AT, CLIENT_NAME, CLIENT_AUTHENTICATION_METHODS, AUTHORIZATION_GRANT_TYPES, REDIRECT_URIS, POST_LOGOUT_REDIRECT_URIS, SCOPES, CLIENT_SETTINGS, TOKEN_SETTINGS};

    public Oauth2RegisteredClientTableDef() {
        super("", "oauth2_registered_client");
    }

    private Oauth2RegisteredClientTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public Oauth2RegisteredClientTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new Oauth2RegisteredClientTableDef("", "oauth2_registered_client", alias));
    }

}
