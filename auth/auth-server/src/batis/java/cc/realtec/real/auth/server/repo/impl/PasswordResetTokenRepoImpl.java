package cc.realtec.real.auth.server.repo.impl;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import cc.realtec.real.auth.server.po.PasswordResetTokenPO;
import cc.realtec.real.auth.server.mapper.PasswordResetTokenMapper;
import cc.realtec.real.auth.server.repo.PasswordResetTokenRepo;
import org.springframework.stereotype.Service;

/**
 *  服务层实现。
 *
 * @author bill
 * @since 2024-08-20
 */
@Service
public class PasswordResetTokenRepoImpl extends ServiceImpl<PasswordResetTokenMapper, PasswordResetTokenPO> implements PasswordResetTokenRepo {

}
