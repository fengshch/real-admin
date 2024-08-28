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
public class SysUserRoleTableDef extends TableDef {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    public static final SysUserRoleTableDef SYS_USER_ROLE_PO = new SysUserRoleTableDef();

    
    public final QueryColumn ROLE_ID = new QueryColumn(this, "role_id");

    
    public final QueryColumn USER_ID = new QueryColumn(this, "user_id");

    
    public final QueryColumn CREATED_AT = new QueryColumn(this, "created_at");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{USER_ID, ROLE_ID, CREATED_AT};

    public SysUserRoleTableDef() {
        super("", "sys_user_role");
    }

    private SysUserRoleTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public SysUserRoleTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new SysUserRoleTableDef("", "sys_user_role", alias));
    }

}
