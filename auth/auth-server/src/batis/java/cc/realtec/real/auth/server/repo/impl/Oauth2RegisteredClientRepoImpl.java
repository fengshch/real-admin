package cc.realtec.real.auth.server.repo.impl;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import cc.realtec.real.auth.server.po.Oauth2RegisteredClientPo;
import cc.realtec.real.auth.server.mapper.Oauth2RegisteredClientMapper;
import cc.realtec.real.auth.server.repo.Oauth2RegisteredClientRepo;
import org.springframework.stereotype.Service;

/**
 *  服务层实现。
 *
 * @author bill
 * @since 2024-10-24
 */
@Service
public class Oauth2RegisteredClientRepoImpl extends ServiceImpl<Oauth2RegisteredClientMapper, Oauth2RegisteredClientPo> implements Oauth2RegisteredClientRepo {

}
