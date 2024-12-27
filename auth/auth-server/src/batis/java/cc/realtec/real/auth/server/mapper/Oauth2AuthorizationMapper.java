package cc.realtec.real.auth.server.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.mybatisflex.core.BaseMapper;
import cc.realtec.real.auth.server.po.Oauth2AuthorizationPo;

/**
 *  映射层。
 *
 * @author bill
 * @since 2024-10-24
 */
@Mapper
public interface Oauth2AuthorizationMapper extends BaseMapper<Oauth2AuthorizationPo> {

}
