package cc.realtec.real.auth.server.repo.impl;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import cc.realtec.real.auth.server.po.Oauth2AuthorizationPO;
import cc.realtec.real.auth.server.mapper.Oauth2AuthorizationMapper;
import cc.realtec.real.auth.server.repo.Oauth2AuthorizationRepo;
import org.springframework.stereotype.Service;

/**
 *  服务层实现。
 *
 * @author bill
 * @since 2024-08-20
 */
@Service
public class Oauth2AuthorizationRepoImpl extends ServiceImpl<Oauth2AuthorizationMapper, Oauth2AuthorizationPO> implements Oauth2AuthorizationRepo {

}
