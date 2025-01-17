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
public class SysGroupRoleTableDef extends TableDef {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    public static final SysGroupRoleTableDef SYS_GROUP_ROLE_PO = new SysGroupRoleTableDef();

    
    public final QueryColumn ROLE_ID = new QueryColumn(this, "role_id");

    
    public final QueryColumn SYSTEM = new QueryColumn(this, "system");

    
    public final QueryColumn GROUP_ID = new QueryColumn(this, "group_id");

    
    public final QueryColumn CREATED_AT = new QueryColumn(this, "created_at");

    
    public final QueryColumn CREATED_BY = new QueryColumn(this, "created_by");

    
    public final QueryColumn UPDATED_AT = new QueryColumn(this, "updated_at");

    
    public final QueryColumn UPDATED_BY = new QueryColumn(this, "updated_by");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{GROUP_ID, ROLE_ID, SYSTEM, CREATED_BY, UPDATED_BY, CREATED_AT, UPDATED_AT};

    public SysGroupRoleTableDef() {
        super("", "sys_group_role");
    }

    private SysGroupRoleTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public SysGroupRoleTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new SysGroupRoleTableDef("", "sys_group_role", alias));
    }

}
