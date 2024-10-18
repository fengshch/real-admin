package cc.realtec.real.auth.server.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "app")
@Setter
@Getter
public class ApplicationProperties {
    private String applicationName;

    @NestedConfigurationProperty
    private System system;

    @Getter
    @Setter
    public static class System {
        private String username;
        private String password;
        private String email;
    }
}
