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
public class SysUserTableDef extends TableDef {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    public static final SysUserTableDef SYS_USER_PO = new SysUserTableDef();

    
    public final QueryColumn ID = new QueryColumn(this, "id");

    
    public final QueryColumn EMAIL = new QueryColumn(this, "email");

    
    public final QueryColumn ENABLED = new QueryColumn(this, "enabled");

    
    public final QueryColumn LAST_NAME = new QueryColumn(this, "last_name");

    
    public final QueryColumn NICKNAME = new QueryColumn(this, "nickname");

    
    public final QueryColumn PASSWORD = new QueryColumn(this, "password");

    
    public final QueryColumn USERNAME = new QueryColumn(this, "username");

    
    public final QueryColumn AVATAR_URL = new QueryColumn(this, "avatar_url");

    
    public final QueryColumn CREATED_AT = new QueryColumn(this, "created_at");

    
    public final QueryColumn FIRST_NAME = new QueryColumn(this, "first_name");

    
    public final QueryColumn UPDATED_AT = new QueryColumn(this, "updated_at");

    
    public final QueryColumn PHONE_NUMBER = new QueryColumn(this, "phone_number");

    
    public final QueryColumn EMAIL_VERIFIED = new QueryColumn(this, "email_verified");

    
    public final QueryColumn ACCOUNT_NON_LOCKED = new QueryColumn(this, "account_non_locked");

    
    public final QueryColumn ACCOUNT_NON_EXPIRED = new QueryColumn(this, "account_non_expired");

    
    public final QueryColumn CREDENTIALS_NON_EXPIRED = new QueryColumn(this, "credentials_non_expired");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{ID, USERNAME, PASSWORD, EMAIL, EMAIL_VERIFIED, NICKNAME, FIRST_NAME, LAST_NAME, PHONE_NUMBER, ACCOUNT_NON_EXPIRED, ACCOUNT_NON_LOCKED, CREDENTIALS_NON_EXPIRED, ENABLED, AVATAR_URL, CREATED_AT, UPDATED_AT};

    public SysUserTableDef() {
        super("", "sys_user");
    }

    private SysUserTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public SysUserTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new SysUserTableDef("", "sys_user", alias));
    }

}
