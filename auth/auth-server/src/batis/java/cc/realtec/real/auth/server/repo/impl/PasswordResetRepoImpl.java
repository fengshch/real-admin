package cc.realtec.real.auth.server.repo.impl;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import cc.realtec.real.auth.server.po.PasswordResetPo;
import cc.realtec.real.auth.server.mapper.PasswordResetMapper;
import cc.realtec.real.auth.server.repo.PasswordResetRepo;
import org.springframework.stereotype.Service;

/**
 *  服务层实现。
 *
 * @author bill
 * @since 2024-10-18
 */
@Service
public class PasswordResetRepoImpl extends ServiceImpl<PasswordResetMapper, PasswordResetPo> implements PasswordResetRepo {

}
