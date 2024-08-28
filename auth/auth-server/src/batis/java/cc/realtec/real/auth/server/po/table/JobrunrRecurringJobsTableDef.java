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
public class JobrunrRecurringJobsTableDef extends TableDef {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    public static final JobrunrRecurringJobsTableDef JOBRUNR_RECURRING_JOBS_PO = new JobrunrRecurringJobsTableDef();

    
    public final QueryColumn ID = new QueryColumn(this, "id");

    
    public final QueryColumn VERSION = new QueryColumn(this, "version");

    
    public final QueryColumn CREATEDAT = new QueryColumn(this, "createdat");

    
    public final QueryColumn JOBASJSON = new QueryColumn(this, "jobasjson");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{ID, VERSION, JOBASJSON, CREATEDAT};

    public JobrunrRecurringJobsTableDef() {
        super("", "jobrunr_recurring_jobs");
    }

    private JobrunrRecurringJobsTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public JobrunrRecurringJobsTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new JobrunrRecurringJobsTableDef("", "jobrunr_recurring_jobs", alias));
    }

}
