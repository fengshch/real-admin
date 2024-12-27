package cc.realtec.real.auth.server.service.impl;

import cc.realtec.real.auth.server.config.ApplicationProperties;
import cc.realtec.real.auth.server.domain.PasswordReset;
import cc.realtec.real.auth.server.domain.ResetPasswordRequest;
import cc.realtec.real.auth.server.domain.converter.PasswordResetConverter;
import cc.realtec.real.auth.server.po.PasswordResetPo;
import cc.realtec.real.auth.server.po.SysUserPo;
import cc.realtec.real.auth.server.repo.PasswordResetRepo;
import cc.realtec.real.auth.server.repo.SysUserRepo;
import cc.realtec.real.auth.server.service.PasswordResetService;
import cc.realtec.real.common.web.exception.BusinessException;
import cc.realtec.real.common.web.exception.EmailNotFoundException;
import cc.realtec.real.common.web.exception.InvalidTokenException;
import cc.realtec.real.email.entity.EmailMessage;
import com.mybatisflex.core.query.QueryWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.server.resource.InvalidBearerTokenException;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;

import java.time.LocalDateTime;

@Service
@Slf4j
@RequiredArgsConstructor
public class PasswordResetServiceImpl implements PasswordResetService {
    private final SysUserRepo sysUserRepo;
    private final PasswordResetRepo passwordResetRepo;
    private final PasswordEncoder passwordEncoder;
    private final ApplicationProperties applicationProperties;
    private final RabbitTemplate rabbitTemplate;
    private final PasswordResetConverter passwordResetConverter = PasswordResetConverter.INSTANCE;
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
        QueryWrapper queryWrapper = QueryWrapper.create().eq(PasswordResetPo::getUserId, userId);
        passwordResetRepo.remove(queryWrapper);
    }

    @Override
    public void sendResetPasswordTokenByEmail(String email, String baseUrl) throws Exception {
        QueryWrapper queryWrapper = QueryWrapper.create().eq("email", email);
        SysUserPo sysUserPo = sysUserRepo.getOne(queryWrapper);
        if (sysUserPo == null) {
            throw new EmailNotFoundException("Email is not existed");
        }

        sendResetPasswordToken(sysUserPo, baseUrl);
    }

    @Override
    public void resetPassword(ResetPasswordRequest resetPasswordRequest) throws Exception {
        String token = resetPasswordRequest.getToken();
        QueryWrapper queryWrapper = QueryWrapper.create().eq("token", token);
        PasswordResetPo passwordResetPo = passwordResetRepo.getOne(queryWrapper);
        if (passwordResetPo == null) {
            throw new InvalidTokenException("Token is invalid");
        }
        if(passwordResetPo.getExpiredAt().isBefore(LocalDateTime.now())){
            throw new InvalidTokenException("Token is expired");
        }
        SysUserPo sysUserPo = sysUserRepo.getById(passwordResetPo.getUserId());
        if(passwordEncoder.matches(token, sysUserPo.getPasswordToken())) {
            sysUserPo.setPassword(passwordEncoder.encode(resetPasswordRequest.getPassword()));
            sysUserPo.setPasswordToken("");
            sysUserRepo.updateById(sysUserPo);
            passwordResetRepo.remove(queryWrapper);
        }else{
            throw new InvalidTokenException("Token is invalid");
        }
    }

    private void sendResetPasswordToken(SysUserPo sysUserPo, String baseUrl) {
        PasswordReset passwordReset = new PasswordReset(sysUserPo.getId());
        passwordReset.setUsername(sysUserPo.getUsername());
        passwordReset.setEmail(sysUserPo.getEmail());
        sysUserPo.setPasswordToken(passwordEncoder.encode(passwordReset.getToken()));
        sysUserRepo.updateById(sysUserPo);
        deleteByUserId(sysUserPo.getId());
        sendPasswordResetEmail(passwordReset, baseUrl);
    }

    private void sendPasswordResetEmail(PasswordReset passwordReset, String baseUrl) {
        String resetUrl = baseUrl + "/reset-password?token=" + passwordReset.getToken();
        Context thyemeleafContext = new Context();
        thyemeleafContext.setVariable("username", passwordReset.getUsername());
        thyemeleafContext.setVariable("resetUrl", resetUrl);
        thyemeleafContext.setVariable("applicationName", applicationProperties.getApplicationName());
        String htmlBody = new ThymeleafEmailTemplate().thymeleafTemplateEngine().process("password-reset", thyemeleafContext);
        EmailMessage emailMessage = EmailMessage.builder()
                .from(applicationProperties.getSystem().getEmail())
                .to(passwordReset.getEmail())
                .subject("Reset your password for " + applicationProperties.getApplicationName())
                .body(htmlBody)
                .html(true)
                .build();
        rabbitTemplate.convertAndSend("emailQueue", emailMessage);
        passwordReset.setSent(true);
        PasswordResetPo passwordResetPo = passwordResetConverter.domainToPo(passwordReset);
        passwordResetRepo.save(passwordResetPo);
    }
}
