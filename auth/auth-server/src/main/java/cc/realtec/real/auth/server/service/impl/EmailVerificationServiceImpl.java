package cc.realtec.real.auth.server.service.impl;

import cc.realtec.real.auth.server.config.ApplicationProperties;
import cc.realtec.real.auth.server.domain.EmailVerification;
import cc.realtec.real.auth.server.domain.converter.EmailVerificationConverter;
import cc.realtec.real.auth.server.po.EmailVerificationPo;
import cc.realtec.real.auth.server.po.SysUserPo;
import cc.realtec.real.auth.server.repo.EmailVerificationRepo;
import cc.realtec.real.auth.server.repo.SysUserRepo;
import cc.realtec.real.auth.server.service.EmailVerificationService;
import cc.realtec.real.common.web.exception.BusinessException;
import cc.realtec.real.email.entity.EmailMessage;
import com.mybatisflex.core.query.QueryWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

import java.time.LocalDateTime;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmailVerificationServiceImpl implements EmailVerificationService {

    private final EmailVerificationRepo emailVerificationRepo;
    private final SysUserRepo sysUserRepo;
    private final ApplicationProperties applicationProperties;
    private final RabbitTemplate rabbitTemplate;
    private final PasswordEncoder passwordEncoder;
    private final EmailVerificationConverter emailVerificationConverter = EmailVerificationConverter.INSTANCE;

    @Override
    public EmailVerification createVerificationToken(String userId) {
        return null;
    }

    @Override
    public EmailVerification getVerificationToken(String token) {
        return null;
    }

    @Override
    public void deleteVerificationToken(String token) {

    }

    @Override
    public void deleteVerificationTokenByUserId(String userId) {

    }

    @Override
    public void deleteExpiredVerificationToken() {

    }

    @Override
    public void sendVerificationToken(SysUserPo sysUserPo, String baseUrl) {
        EmailVerification emailVerification = new EmailVerification(sysUserPo.getId());
        emailVerification.setUsername(sysUserPo.getUsername());
        emailVerification.setEmail(sysUserPo.getEmail());
        sysUserPo.setEmailToken(passwordEncoder.encode(emailVerification.getToken()));
        sysUserRepo.updateById(sysUserPo);
        deleteByUserId(emailVerification.getUserId());
        sendWelcomeEmail(emailVerification, baseUrl);
    }

    @Override
    public void sendVerificationTokenByEmail(String email, String baseUrl) {
        QueryWrapper queryWrapper = QueryWrapper.create().eq("email", email);
        SysUserPo sysUserPO = sysUserRepo.getOne(queryWrapper);
        if (sysUserPO == null) {
            throw new BusinessException("User is not existed");
        }

        sendVerificationToken(sysUserPO, baseUrl);
    }


    @Override
    public void deleteByUserId(String userId) {
        QueryWrapper queryWrapper = new QueryWrapper();
//        queryWrapper.eq("user_id", userId);
        queryWrapper.eq(EmailVerification::getUserId, userId);
        emailVerificationRepo.remove(queryWrapper);
    }



    private void sendWelcomeEmail(EmailVerification emailVerification, String baseUrl) {
        String verificationLink = baseUrl + "/verify-email-result?token=" + emailVerification.getToken();
        Context thyemeleafContext = new Context();
        thyemeleafContext.setVariable("username", emailVerification.getUsername());
        thyemeleafContext.setVariable("verificationLink", verificationLink);
        thyemeleafContext.setVariable("applicationName", applicationProperties.getApplicationName());
        String htmlBody = thymeleafTemplateEngine().process("verification-token-email", thyemeleafContext);
        EmailMessage emailMessage = EmailMessage.builder()
                .from(applicationProperties.getSystem().getEmail())
                .to(emailVerification.getEmail())
                .subject("Welcome to " + applicationProperties.getApplicationName())
                .body(htmlBody)
                .html(true)
                .build();
        rabbitTemplate.convertAndSend("emailQueue", emailMessage);
        emailVerification.setSent(true);
        EmailVerificationPo emailVerificationPo = emailVerificationConverter.domainToPo(emailVerification);
        emailVerificationRepo.save(emailVerificationPo);
    }



    private ITemplateResolver thymeleafTemplateResolver() {
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setPrefix("mail-templates/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode("HTML");
        templateResolver.setCharacterEncoding("UTF-8");
        return templateResolver;
    }

    private SpringTemplateEngine thymeleafTemplateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(thymeleafTemplateResolver());
        return templateEngine;
    }

    @Override
    public void verifyEmail(String token) throws Exception {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("token", token);
        EmailVerificationPo emailVerificationPo = emailVerificationRepo.getOne(queryWrapper);
        if (emailVerificationPo == null) {
            throw new Exception("Invalid token");
        }
        if (emailVerificationPo.getExpiredAt().isBefore(LocalDateTime.now())) {
            throw new Exception("Token expired");
        }
        SysUserPo sysUserPo = sysUserRepo.getById(emailVerificationPo.getUserId());
        if(!passwordEncoder.matches(token, sysUserPo.getEmailToken())) {
            throw new Exception("Invalid token");
        }
        sysUserPo.setEmailVerified(true);
        sysUserPo.setEmailToken("");
        sysUserRepo.updateById(sysUserPo);
        emailVerificationRepo.removeById(emailVerificationPo);
    }
}
