package cc.realtec.real.auth.server.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.mybatisflex.core.BaseMapper;
import cc.realtec.real.auth.server.po.PasswordResetTokenPO;

/**
 *  映射层。
 *
 * @author bill
 * @since 2024-08-20
 */
@Mapper
public interface PasswordResetTokenMapper extends BaseMapper<PasswordResetTokenPO> {

}
