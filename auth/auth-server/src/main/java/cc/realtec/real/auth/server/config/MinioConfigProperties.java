package cc.realtec.real.auth.server.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "minio")
@Data
public class MinioConfigProperties {
        private String url;
        private String accessKey;
        private String secretKey;
        private String defaultBucket;
        private String avatarBucket;
}
