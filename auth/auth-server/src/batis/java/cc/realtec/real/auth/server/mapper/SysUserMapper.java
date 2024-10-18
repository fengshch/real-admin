package cc.realtec.real.auth.server.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.mybatisflex.core.BaseMapper;
import cc.realtec.real.auth.server.po.SysUserPo;

/**
 *  映射层。
 *
 * @author bill
 * @since 2024-10-18
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUserPo> {

}
