package cc.realtec.real.auth.server.service;

import cc.realtec.real.auth.server.domain.ResetPasswordRequest;
import cc.realtec.real.auth.server.po.SysUserPo;

public interface PasswordResetService {
    void sendVerificationToken(SysUserPo sysUserPo , String baseUrl);

    void sendVerificationTokenByEmail(String email , String baseUrl);

    void verifyEmail(String token) throws Exception;

    void deleteByUserId(String userId);

    void sendResetPasswordTokenByEmail(String email, String baseUrl) throws Exception;

    void resetPassword(ResetPasswordRequest resetPasswordRequest) throws Exception;
}
