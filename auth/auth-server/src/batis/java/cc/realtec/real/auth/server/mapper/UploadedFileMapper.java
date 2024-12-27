package cc.realtec.real.auth.server.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.mybatisflex.core.BaseMapper;
import cc.realtec.real.auth.server.po.UploadedFilePo;

/**
 *  映射层。
 *
 * @author bill
 * @since 2024-10-24
 */
@Mapper
public interface UploadedFileMapper extends BaseMapper<UploadedFilePo> {

}
