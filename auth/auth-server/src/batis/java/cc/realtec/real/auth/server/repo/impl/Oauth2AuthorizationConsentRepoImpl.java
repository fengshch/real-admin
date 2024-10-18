package cc.realtec.real.auth.server.repo.impl;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import cc.realtec.real.auth.server.po.Oauth2AuthorizationConsentPo;
import cc.realtec.real.auth.server.mapper.Oauth2AuthorizationConsentMapper;
import cc.realtec.real.auth.server.repo.Oauth2AuthorizationConsentRepo;
import org.springframework.stereotype.Service;

/**
 *  服务层实现。
 *
 * @author bill
 * @since 2024-10-18
 */
@Service
public class Oauth2AuthorizationConsentRepoImpl extends ServiceImpl<Oauth2AuthorizationConsentMapper, Oauth2AuthorizationConsentPo> implements Oauth2AuthorizationConsentRepo {

}
