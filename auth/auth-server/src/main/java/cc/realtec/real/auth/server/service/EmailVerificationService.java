package cc.realtec.real.auth.server.service;

import cc.realtec.real.auth.server.domain.EmailVerification;
import cc.realtec.real.auth.server.po.SysUserPo;

public interface EmailVerificationService {

    EmailVerification createVerificationToken(String userId);

    EmailVerification getVerificationToken(String token);

    void deleteVerificationToken(String token);

    void deleteVerificationTokenByUserId(String userId);

    void deleteExpiredVerificationToken();

    void sendVerificationToken(SysUserPo sysUserPo , String baseUrl);

    void sendVerificationTokenByEmail(String email , String baseUrl) throws Exception;

    void verifyEmail(String token) throws Exception;

    void deleteByUserId(String userId);
}
