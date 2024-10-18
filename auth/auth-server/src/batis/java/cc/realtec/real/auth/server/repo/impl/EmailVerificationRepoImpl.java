package cc.realtec.real.auth.server.repo.impl;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import cc.realtec.real.auth.server.po.EmailVerificationPo;
import cc.realtec.real.auth.server.mapper.EmailVerificationMapper;
import cc.realtec.real.auth.server.repo.EmailVerificationRepo;
import org.springframework.stereotype.Service;

/**
 *  服务层实现。
 *
 * @author bill
 * @since 2024-10-18
 */
@Service
public class EmailVerificationRepoImpl extends ServiceImpl<EmailVerificationMapper, EmailVerificationPo> implements EmailVerificationRepo {

}
