package cc.realtec.real.auth.server.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.mybatisflex.core.BaseMapper;
import cc.realtec.real.auth.server.po.EmailVerificationPo;

/**
 *  映射层。
 *
 * @author bill
 * @since 2024-10-18
 */
@Mapper
public interface EmailVerificationMapper extends BaseMapper<EmailVerificationPo> {

}