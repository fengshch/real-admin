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
public class EmailVerificationTableDef extends TableDef {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    public static final EmailVerificationTableDef EMAIL_VERIFICATION_PO = new EmailVerificationTableDef();

    
    public final QueryColumn ID = new QueryColumn(this, "id");

    
    public final QueryColumn SENT = new QueryColumn(this, "sent");

    
    public final QueryColumn TOKEN = new QueryColumn(this, "token");

    
    public final QueryColumn USER_ID = new QueryColumn(this, "user_id");

    
    public final QueryColumn CREATED_AT = new QueryColumn(this, "created_at");

    
    public final QueryColumn CREATED_BY = new QueryColumn(this, "created_by");

    
    public final QueryColumn EXPIRED_AT = new QueryColumn(this, "expired_at");

    
    public final QueryColumn UPDATED_AT = new QueryColumn(this, "updated_at");

    
    public final QueryColumn UPDATED_BY = new QueryColumn(this, "updated_by");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{ID, USER_ID, TOKEN, SENT, EXPIRED_AT, CREATED_BY, UPDATED_BY, CREATED_AT, UPDATED_AT};

    public EmailVerificationTableDef() {
        super("", "email_verification");
    }

    private EmailVerificationTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public EmailVerificationTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new EmailVerificationTableDef("", "email_verification", alias));
    }

}
