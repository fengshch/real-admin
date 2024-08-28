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
public class UploadedFileTableDef extends TableDef {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    public static final UploadedFileTableDef UPLOADED_FILE_PO = new UploadedFileTableDef();

    
    public final QueryColumn ID = new QueryColumn(this, "id");

    
    public final QueryColumn URL = new QueryColumn(this, "url");

    
    public final QueryColumn SIZE = new QueryColumn(this, "size");

    
    public final QueryColumn UPLOAD_AT = new QueryColumn(this, "upload_at");

    
    public final QueryColumn CREATED_AT = new QueryColumn(this, "created_at");

    
    public final QueryColumn EXTENSION = new QueryColumn(this, "extension");

    
    public final QueryColumn ORIGINAL_FILE_NAME = new QueryColumn(this, "original_file_name");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{ID, ORIGINAL_FILE_NAME, EXTENSION, SIZE, URL, UPLOAD_AT, CREATED_AT};

    public UploadedFileTableDef() {
        super("", "uploaded_file");
    }

    private UploadedFileTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public UploadedFileTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new UploadedFileTableDef("", "uploaded_file", alias));
    }

}
