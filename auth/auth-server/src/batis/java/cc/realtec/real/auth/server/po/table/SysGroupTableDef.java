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
public class SysGroupTableDef extends TableDef {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    public static final SysGroupTableDef SYS_GROUP_PO = new SysGroupTableDef();

    
    public final QueryColumn ID = new QueryColumn(this, "id");

    
    public final QueryColumn NAME = new QueryColumn(this, "name");

    
    public final QueryColumn SYSTEM = new QueryColumn(this, "system");

    
    public final QueryColumn PARENT_ID = new QueryColumn(this, "parent_id");

    
    public final QueryColumn CREATED_AT = new QueryColumn(this, "created_at");

    
    public final QueryColumn CREATED_BY = new QueryColumn(this, "created_by");

    
    public final QueryColumn UPDATED_AT = new QueryColumn(this, "updated_at");

    
    public final QueryColumn UPDATED_BY = new QueryColumn(this, "updated_by");

    
    public final QueryColumn DESCRIPTION = new QueryColumn(this, "description");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{ID, PARENT_ID, NAME, DESCRIPTION, SYSTEM, CREATED_BY, UPDATED_BY, CREATED_AT, UPDATED_AT};

    public SysGroupTableDef() {
        super("", "sys_group");
    }

    private SysGroupTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public SysGroupTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new SysGroupTableDef("", "sys_group", alias));
    }

}
