package cc.realtec.real.bff.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.security.oauth2.client.R2dbcReactiveOAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.ReactiveOAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.registration.ReactiveClientRegistrationRepository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class OAuth2ClientConfig {
    @Bean
    public ReactiveOAuth2AuthorizedClientService authorizedClientService(DatabaseClient databaseClient,
                                                                         ReactiveClientRegistrationRepository clientRegistrationRepository) {
        return new R2dbcReactiveOAuth2AuthorizedClientService(databaseClient, clientRegistrationRepository);
    }
}
