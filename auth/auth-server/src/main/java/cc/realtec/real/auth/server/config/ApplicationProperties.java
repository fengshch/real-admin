package cc.realtec.real.auth.server.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "application")
@Setter
@Getter
public class ApplicationProperties {
    private String adminUsername;
    private String adminEmail;
    private String adminPassword;
}
