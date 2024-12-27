package cc.realtec.real.auth.server.config;

import io.minio.MinioClient;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class MinioConfig {
    private final MinioConfigProperties properties;

//    @Value("${minio.url}")
//    @Getter
//    private String minioUrl;
//
//    @Value("${minio.access-key}")
//    private String accessKey;
//
//    @Value("${minio.secret-key}")
//    private String secretKey;

    @Bean
    public MinioClient minioClient() {
        return MinioClient.builder()
                .endpoint(properties.getUrl())
                .credentials(properties.getAccessKey(), properties.getSecretKey())
                .build();
    }
}
