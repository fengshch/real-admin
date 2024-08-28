package cc.realtec.real.auth.server.service.impl;

import cc.realtec.real.auth.common.entity.SysUser;
import cc.realtec.real.auth.server.config.ApplicationProperties;
import cc.realtec.real.auth.server.service.SysUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class InitializeApplication implements ApplicationRunner {
    private final ApplicationProperties applicationProperties;
    private final SysUserService sysUserService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("Application initialized");
        if (!sysUserService.existsByUsername(applicationProperties.getAdminUsername())) {
            log.info("Create admin user");
            SysUser adminUser = new SysUser();
            adminUser.setUsername(applicationProperties.getAdminUsername());
            adminUser.setEmail(applicationProperties.getAdminEmail());
            adminUser.setPasswordPlain(applicationProperties.getAdminPassword());

            sysUserService.create(adminUser);
            log.info("Admin user created");
        }
    }
}
