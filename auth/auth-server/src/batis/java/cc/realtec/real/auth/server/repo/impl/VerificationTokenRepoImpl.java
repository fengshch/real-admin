package cc.realtec.real.auth.server.repo.impl;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import cc.realtec.real.auth.server.po.VerificationTokenPO;
import cc.realtec.real.auth.server.mapper.VerificationTokenMapper;
import cc.realtec.real.auth.server.repo.VerificationTokenRepo;
import org.springframework.stereotype.Service;

/**
 *  服务层实现。
 *
 * @author bill
 * @since 2024-08-20
 */
@Service
public class VerificationTokenRepoImpl extends ServiceImpl<VerificationTokenMapper, VerificationTokenPO> implements VerificationTokenRepo {

}
