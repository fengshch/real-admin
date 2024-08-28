package cc.realtec.real.webflux.client.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {
    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {

        http
                .authorizeExchange(exchanges ->
                        exchanges.anyExchange().authenticated()
                )
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .oauth2Login(Customizer.withDefaults());
        return http.build();
    }
//    @Bean
//    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http, ReactiveClientRegistrationRepository clientRegistrationRepository) {
//        CustomServerAuthorizationRequestRepository customRepository = new CustomServerAuthorizationRequestRepository();
//        DefaultServerOAuth2AuthorizationRequestResolver authorizationRequestResolver = new DefaultServerOAuth2AuthorizationRequestResolver(clientRegistrationRepository);
//
//
//
//        http
//                .authorizeExchange(exchanges ->
//                        exchanges.anyExchange().authenticated()
//                )
//                .csrf(ServerHttpSecurity.CsrfSpec::disable)
//                .oauth2Login(oauth2 -> oauth2
//                        .authorizationRequestRepository(customRepository)
//                        .authorizationRequestResolver(authorizationRequestResolver)
//                        .authenticationSuccessHandler((webFilterExchange, authentication) -> {
//                            System.out.println("Successfully Authenticated: " + authentication.getName());
//                            return webFilterExchange.getExchange().getSession()
//                                    .doOnNext(session -> session.getAttributes().put("authentication", authentication))
//                                    .then();
//                        })
//                        .authenticationFailureHandler((webFilterExchange, exception) -> {
//                            System.out.println("Authentication Failed: " + exception.getMessage());
//                            return webFilterExchange.getExchange().getResponse().setComplete();
//                        }));
//        return http.build();
//    }
//
}
