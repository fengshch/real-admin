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
public class SysUserTableDef extends TableDef {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    public static final SysUserTableDef SYS_USER_PO = new SysUserTableDef();

    
    public final QueryColumn ID = new QueryColumn(this, "id");

    
    public final QueryColumn MFA = new QueryColumn(this, "mfa");

    
    public final QueryColumn NAME = new QueryColumn(this, "name");

    
    public final QueryColumn EMAIL = new QueryColumn(this, "email");

    
    public final QueryColumn GENDER = new QueryColumn(this, "gender");

    
    public final QueryColumn SYSTEM = new QueryColumn(this, "system");

    
    public final QueryColumn ENABLED = new QueryColumn(this, "enabled");

    
    public final QueryColumn NICKNAME = new QueryColumn(this, "nickname");

    
    public final QueryColumn PASSWORD = new QueryColumn(this, "password");

    
    public final QueryColumn USERNAME = new QueryColumn(this, "username");

    
    public final QueryColumn BIRTHDATE = new QueryColumn(this, "birthdate");

    
    public final QueryColumn CREATED_AT = new QueryColumn(this, "created_at");

    
    public final QueryColumn CREATED_BY = new QueryColumn(this, "created_by");

    
    public final QueryColumn UPDATED_AT = new QueryColumn(this, "updated_at");

    
    public final QueryColumn UPDATED_BY = new QueryColumn(this, "updated_by");

    
    public final QueryColumn AVATAR_NAME = new QueryColumn(this, "avatar_name");

    
    public final QueryColumn EMAIL_TOKEN = new QueryColumn(this, "email_token");

    
    public final QueryColumn PHONE_NUMBER = new QueryColumn(this, "phone_number");

    
    public final QueryColumn EMAIL_VERIFIED = new QueryColumn(this, "email_verified");

    
    public final QueryColumn PASSWORD_TOKEN = new QueryColumn(this, "password_token");

    
    public final QueryColumn ACCOUNT_NON_LOCKED = new QueryColumn(this, "account_non_locked");

    
    public final QueryColumn ACCOUNT_NON_EXPIRED = new QueryColumn(this, "account_non_expired");

    
    public final QueryColumn PHONE_NUMBER_VERIFIED = new QueryColumn(this, "phone_number_verified");

    
    public final QueryColumn CREDENTIALS_NON_EXPIRED = new QueryColumn(this, "credentials_non_expired");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{ID, USERNAME, PASSWORD, NAME, NICKNAME, GENDER, BIRTHDATE, AVATAR_NAME, EMAIL, EMAIL_VERIFIED, EMAIL_TOKEN, PASSWORD_TOKEN, PHONE_NUMBER, PHONE_NUMBER_VERIFIED, ACCOUNT_NON_EXPIRED, ACCOUNT_NON_LOCKED, CREDENTIALS_NON_EXPIRED, ENABLED, MFA, SYSTEM, CREATED_BY, UPDATED_BY, CREATED_AT, UPDATED_AT};

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
