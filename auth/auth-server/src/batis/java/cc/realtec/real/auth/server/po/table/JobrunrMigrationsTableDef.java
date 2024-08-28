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
public class JobrunrMigrationsTableDef extends TableDef {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    public static final JobrunrMigrationsTableDef JOBRUNR_MIGRATIONS_PO = new JobrunrMigrationsTableDef();

    
    public final QueryColumn ID = new QueryColumn(this, "id");

    
    public final QueryColumn SCRIPT = new QueryColumn(this, "script");

    
    public final QueryColumn INSTALLEDON = new QueryColumn(this, "installedon");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{ID, SCRIPT, INSTALLEDON};

    public JobrunrMigrationsTableDef() {
        super("", "jobrunr_migrations");
    }

    private JobrunrMigrationsTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public JobrunrMigrationsTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new JobrunrMigrationsTableDef("", "jobrunr_migrations", alias));
    }

}
