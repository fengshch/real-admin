package cc.realtec.real.auth.server.service.impl;

import cc.realtec.real.auth.server.po.SysUserPo;
import cc.realtec.real.auth.server.repo.PasswordResetRepo;
import cc.realtec.real.auth.server.repo.SysUserRepo;
import cc.realtec.real.auth.server.service.PasswordResetService;
import cc.realtec.real.common.web.exception.BusinessException;
import com.mybatisflex.core.query.QueryWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class PasswordResetServiceImpl implements PasswordResetService {
    private final SysUserRepo sysUserRepo;
    private final PasswordResetRepo passwordResetRepo;
    @Override
    public void sendVerificationToken(SysUserPo sysUserPo, String baseUrl) {

    }

    @Override
    public void sendVerificationTokenByEmail(String email, String baseUrl) {

    }

    @Override
    public void verifyEmail(String token) throws Exception {

    }

    @Override
    public void deleteByUserId(String userId) {

    }

    @Override
    public void sendResetPasswordTokenByEmail(String email, String baseUrl) {
        QueryWrapper queryWrapper = QueryWrapper.create().eq("email", email);
        SysUserPo sysUserPo = sysUserRepo.getOne(queryWrapper);
        if (sysUserPo == null) {
            throw new BusinessException("User is not existed");
        }

        sendResetPasswordToken(sysUserPo, baseUrl);
    }

    private void sendResetPasswordToken(SysUserPo sysUserPo, String baseUrl) {

    }
}
