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
public class SysUserGroupTableDef extends TableDef {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    public static final SysUserGroupTableDef SYS_USER_GROUP_PO = new SysUserGroupTableDef();

    
    public final QueryColumn USER_ID = new QueryColumn(this, "user_id");

    
    public final QueryColumn GROUP_ID = new QueryColumn(this, "group_id");

    
    public final QueryColumn CREATED_AT = new QueryColumn(this, "created_at");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{USER_ID, GROUP_ID, CREATED_AT};

    public SysUserGroupTableDef() {
        super("", "sys_user_group");
    }

    private SysUserGroupTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public SysUserGroupTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new SysUserGroupTableDef("", "sys_user_group", alias));
    }

}
