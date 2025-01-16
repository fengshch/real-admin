package cc.realtec.real.auth.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
public class CorsConfig {
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();

        config.setAllowedHeaders(List.of("X-XSRF-TOKEN", "Authorization", "Content-Type", "Accept"));
        config.setAllowedMethods(List.of("GET", "HEAD", "POST", "PUT", "DELETE", "OPTIONS"));
        config.addAllowedOrigin("http://localhost:3000");
        config.addAllowedOrigin("http://localhost:7090");
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}
