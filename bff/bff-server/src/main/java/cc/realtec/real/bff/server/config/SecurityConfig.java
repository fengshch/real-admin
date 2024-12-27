package cc.realtec.real.bff.server.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.DelegatingServerAuthenticationEntryPoint;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.ServerAuthenticationEntryPoint;
import org.springframework.security.web.server.authentication.RedirectServerAuthenticationEntryPoint;
import org.springframework.security.web.server.authentication.RedirectServerAuthenticationSuccessHandler;
import org.springframework.security.web.server.authentication.logout.DelegatingServerLogoutHandler;
import org.springframework.security.web.server.authentication.logout.SecurityContextServerLogoutHandler;
import org.springframework.security.web.server.authentication.logout.ServerLogoutHandler;
import org.springframework.security.web.server.csrf.CookieServerCsrfTokenRepository;
import org.springframework.security.web.server.csrf.CsrfServerLogoutHandler;
import org.springframework.security.web.server.csrf.ServerCsrfTokenRepository;
import org.springframework.security.web.server.csrf.ServerCsrfTokenRequestAttributeHandler;
import org.springframework.security.web.server.util.matcher.MediaTypeServerWebExchangeMatcher;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@EnableWebFluxSecurity
@Configuration(proxyBeanMethods = false)
@RequiredArgsConstructor
public class SecurityConfig {

    @Value("${app.base-uri}")
    private String appBaseUri;

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        CookieServerCsrfTokenRepository cookieCsrfTokenRepository = CookieServerCsrfTokenRepository.withHttpOnlyFalse();
        ServerCsrfTokenRequestAttributeHandler csrfTokenRequestAttributeHandler = new ServerCsrfTokenRequestAttributeHandler();

        http.authorizeExchange(auth -> auth
                .pathMatchers("/h2-console/**").permitAll()
                .pathMatchers("/auth/**").permitAll()
                .anyExchange().authenticated())
            .oauth2Login(Customizer.withDefaults())
            .csrf(csrf -> csrf
                .csrfTokenRepository(cookieCsrfTokenRepository)
                .csrfTokenRequestHandler(csrfTokenRequestAttributeHandler))
            .cors(Customizer.withDefaults())
            .exceptionHandling(exceptionHandling -> exceptionHandling
                .authenticationEntryPoint(authenticationEntryPoint()))
            .oauth2Login(oauth2Login -> oauth2Login
                .authenticationSuccessHandler(new RedirectServerAuthenticationSuccessHandler(this.appBaseUri)))
            .logout(logout -> logout
                .logoutHandler(logoutHandler(cookieCsrfTokenRepository))
                .logoutSuccessHandler((exchange, authentication) -> Mono.fromRunnable(() -> {
                    exchange.getExchange().getResponse().setStatusCode(HttpStatus.OK);
                })));

        return http.build();
    }

    private ServerAuthenticationEntryPoint authenticationEntryPoint() {
        RedirectServerAuthenticationEntryPoint authenticationEntryPoint =
            new RedirectServerAuthenticationEntryPoint("/oauth2/authorization/gateway-client-oidc");

        MediaTypeServerWebExchangeMatcher textHtmlMatcher = new MediaTypeServerWebExchangeMatcher(MediaType.TEXT_HTML);
        textHtmlMatcher.setUseEquals(true);

        List<DelegatingServerAuthenticationEntryPoint.DelegateEntry> entryPoints = new ArrayList<>();
        entryPoints.add(new DelegatingServerAuthenticationEntryPoint.DelegateEntry(textHtmlMatcher, authenticationEntryPoint));

        DelegatingServerAuthenticationEntryPoint delegatingAuthenticationEntryPoint = new DelegatingServerAuthenticationEntryPoint(entryPoints);
        delegatingAuthenticationEntryPoint.setDefaultEntryPoint((exchange, e) -> Mono.error(new ResponseStatusException(HttpStatus.UNAUTHORIZED)));

        return delegatingAuthenticationEntryPoint;
    }

    private ServerLogoutHandler logoutHandler(ServerCsrfTokenRepository csrfTokenRepository) {
        return new DelegatingServerLogoutHandler(
            new SecurityContextServerLogoutHandler(),
            new CsrfServerLogoutHandler(csrfTokenRepository)
        );
    }
}
