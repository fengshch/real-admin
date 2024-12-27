package cc.realtec.real.auth.server.listener;

import cc.realtec.real.auth.server.config.ApplicationProperties;
import cc.realtec.real.auth.server.domain.SysUserRequest;
import cc.realtec.real.auth.server.service.SysUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class SetupDataOnLoader implements ApplicationListener<ContextRefreshedEvent> {
    private final ApplicationProperties applicationProperties;
    private final SysUserService sysUserService;


    @Override
    public void onApplicationEvent(@NonNull ContextRefreshedEvent event) {
        createSystemManagerIfNotFound();
    }

    private void createSystemManagerIfNotFound() {
        log.info("Application initialized");
        if (!sysUserService.existsByUsername(applicationProperties.getSystem().getUsername())) {
            log.info("Create System Manager");
            SysUserRequest adminUser = new SysUserRequest();
            adminUser.setUsername(applicationProperties.getSystem().getUsername());
            adminUser.setEmail(applicationProperties.getSystem().getEmail());
            adminUser.setPassword(applicationProperties.getSystem().getPassword());
            adminUser.setSystem(true);
            adminUser.setEmailVerified(true);
            log.info("System Manager created");
            try {
                sysUserService.create(adminUser);
            } catch (Exception e) {
                log.error("Create System Manager failed", e);
            }
        }
    }
}
