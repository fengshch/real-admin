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
public class JobrunrJobsTableDef extends TableDef {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    public static final JobrunrJobsTableDef JOBRUNR_JOBS_PO = new JobrunrJobsTableDef();

    
    public final QueryColumn ID = new QueryColumn(this, "id");

    
    public final QueryColumn STATE = new QueryColumn(this, "state");

    
    public final QueryColumn VERSION = new QueryColumn(this, "version");

    
    public final QueryColumn CREATEDAT = new QueryColumn(this, "createdat");

    
    public final QueryColumn JOBASJSON = new QueryColumn(this, "jobasjson");

    
    public final QueryColumn UPDATEDAT = new QueryColumn(this, "updatedat");

    
    public final QueryColumn SCHEDULEDAT = new QueryColumn(this, "scheduledat");

    
    public final QueryColumn JOBSIGNATURE = new QueryColumn(this, "jobsignature");

    
    public final QueryColumn RECURRINGJOBID = new QueryColumn(this, "recurringjobid");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{ID, VERSION, JOBASJSON, JOBSIGNATURE, STATE, CREATEDAT, UPDATEDAT, SCHEDULEDAT, RECURRINGJOBID};

    public JobrunrJobsTableDef() {
        super("", "jobrunr_jobs");
    }

    private JobrunrJobsTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public JobrunrJobsTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new JobrunrJobsTableDef("", "jobrunr_jobs", alias));
    }

}
