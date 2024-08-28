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
public class JobrunrMetadataTableDef extends TableDef {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    public static final JobrunrMetadataTableDef JOBRUNR_METADATA_PO = new JobrunrMetadataTableDef();

    
    public final QueryColumn ID = new QueryColumn(this, "id");

    
    public final QueryColumn NAME = new QueryColumn(this, "name");

    
    public final QueryColumn OWNER = new QueryColumn(this, "owner");

    
    public final QueryColumn VALUE = new QueryColumn(this, "value");

    
    public final QueryColumn CREATEDAT = new QueryColumn(this, "createdat");

    
    public final QueryColumn UPDATEDAT = new QueryColumn(this, "updatedat");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{ID, NAME, OWNER, VALUE, CREATEDAT, UPDATEDAT};

    public JobrunrMetadataTableDef() {
        super("", "jobrunr_metadata");
    }

    private JobrunrMetadataTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public JobrunrMetadataTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new JobrunrMetadataTableDef("", "jobrunr_metadata", alias));
    }

}
