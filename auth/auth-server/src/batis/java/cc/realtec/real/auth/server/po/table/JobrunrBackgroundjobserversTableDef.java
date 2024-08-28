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
public class JobrunrBackgroundjobserversTableDef extends TableDef {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    public static final JobrunrBackgroundjobserversTableDef JOBRUNR_BACKGROUNDJOBSERVERS_PO = new JobrunrBackgroundjobserversTableDef();

    
    public final QueryColumn ID = new QueryColumn(this, "id");

    
    public final QueryColumn NAME = new QueryColumn(this, "name");

    
    public final QueryColumn RUNNING = new QueryColumn(this, "running");

    
    public final QueryColumn LASTHEARTBEAT = new QueryColumn(this, "lastheartbeat");

    
    public final QueryColumn SYSTEMCPULOAD = new QueryColumn(this, "systemcpuload");

    
    public final QueryColumn FIRSTHEARTBEAT = new QueryColumn(this, "firstheartbeat");

    
    public final QueryColumn PROCESSCPULOAD = new QueryColumn(this, "processcpuload");

    
    public final QueryColumn WORKERPOOLSIZE = new QueryColumn(this, "workerpoolsize");

    
    public final QueryColumn PROCESSMAXMEMORY = new QueryColumn(this, "processmaxmemory");

    
    public final QueryColumn SYSTEMFREEMEMORY = new QueryColumn(this, "systemfreememory");

    
    public final QueryColumn PROCESSFREEMEMORY = new QueryColumn(this, "processfreememory");

    
    public final QueryColumn SYSTEMTOTALMEMORY = new QueryColumn(this, "systemtotalmemory");

    
    public final QueryColumn POLLINTERVALINSECONDS = new QueryColumn(this, "pollintervalinseconds");

    
    public final QueryColumn PROCESSALLOCATEDMEMORY = new QueryColumn(this, "processallocatedmemory");

    
    public final QueryColumn DELETESUCCEEDEDJOBSAFTER = new QueryColumn(this, "deletesucceededjobsafter");

    
    public final QueryColumn PERMANENTLYDELETEJOBSAFTER = new QueryColumn(this, "permanentlydeletejobsafter");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{ID, WORKERPOOLSIZE, POLLINTERVALINSECONDS, FIRSTHEARTBEAT, LASTHEARTBEAT, RUNNING, SYSTEMTOTALMEMORY, SYSTEMFREEMEMORY, SYSTEMCPULOAD, PROCESSMAXMEMORY, PROCESSFREEMEMORY, PROCESSALLOCATEDMEMORY, PROCESSCPULOAD, DELETESUCCEEDEDJOBSAFTER, PERMANENTLYDELETEJOBSAFTER, NAME};

    public JobrunrBackgroundjobserversTableDef() {
        super("", "jobrunr_backgroundjobservers");
    }

    private JobrunrBackgroundjobserversTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public JobrunrBackgroundjobserversTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new JobrunrBackgroundjobserversTableDef("", "jobrunr_backgroundjobservers", alias));
    }

}
