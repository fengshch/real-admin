package cc.realtec.real.auth.server.config;

import cc.realtec.real.common.web.advice.CustomResponseBodyAdvice;
import cc.realtec.real.common.web.advice.GlobalExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

@Configuration
public class AdviceConfig {

    @Bean
    public CustomResponseBodyAdvice customResponseBodyAdvice(MappingJackson2HttpMessageConverter converter) {
        return new CustomResponseBodyAdvice(converter);
    }

    @Bean
    public GlobalExceptionHandler globalExceptionHandler() {
        return new GlobalExceptionHandler();
    }
}
