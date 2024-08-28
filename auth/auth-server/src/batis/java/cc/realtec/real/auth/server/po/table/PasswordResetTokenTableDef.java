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
public class PasswordResetTokenTableDef extends TableDef {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    public static final PasswordResetTokenTableDef PASSWORD_RESET_TOKEN_PO = new PasswordResetTokenTableDef();

    
    public final QueryColumn ID = new QueryColumn(this, "id");

    
    public final QueryColumn TOKEN = new QueryColumn(this, "token");

    
    public final QueryColumn USER_ID = new QueryColumn(this, "user_id");

    
    public final QueryColumn CREATED_AT = new QueryColumn(this, "created_at");

    
    public final QueryColumn EMAIL_SENT = new QueryColumn(this, "email_sent");

    
    public final QueryColumn EXPIRES_AT = new QueryColumn(this, "expires_at");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{ID, USER_ID, TOKEN, EMAIL_SENT, CREATED_AT, EXPIRES_AT};

    public PasswordResetTokenTableDef() {
        super("", "password_reset_token");
    }

    private PasswordResetTokenTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public PasswordResetTokenTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new PasswordResetTokenTableDef("", "password_reset_token", alias));
    }

}
