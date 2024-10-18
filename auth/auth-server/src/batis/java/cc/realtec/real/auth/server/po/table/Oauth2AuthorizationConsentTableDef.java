package cc.realtec.real.auth.server.po.table;

import com.mybatisflex.core.query.QueryColumn;
import com.mybatisflex.core.table.TableDef;

import java.io.Serial;

/**
 *  表定义层。
 *
 * @author bill
 * @since 2024-10-18
 */
public class Oauth2AuthorizationConsentTableDef extends TableDef {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    public static final Oauth2AuthorizationConsentTableDef OAUTH2_AUTHORIZATION_CONSENT_PO = new Oauth2AuthorizationConsentTableDef();

    
    public final QueryColumn AUTHORITIES = new QueryColumn(this, "authorities");

    
    public final QueryColumn PRINCIPAL_NAME = new QueryColumn(this, "principal_name");

    
    public final QueryColumn REGISTERED_CLIENT_ID = new QueryColumn(this, "registered_client_id");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{REGISTERED_CLIENT_ID, PRINCIPAL_NAME, AUTHORITIES};

    public Oauth2AuthorizationConsentTableDef() {
        super("", "oauth2_authorization_consent");
    }

    private Oauth2AuthorizationConsentTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public Oauth2AuthorizationConsentTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new Oauth2AuthorizationConsentTableDef("", "oauth2_authorization_consent", alias));
    }

}
