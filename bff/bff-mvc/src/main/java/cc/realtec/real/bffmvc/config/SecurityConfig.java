package cc.realtec.real.bffmvc.config;

import cc.realtec.real.bffmvc.security.TokenRevocationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.client.OidcBackChannelLogoutHandler;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.client.oidc.session.InMemoryOidcSessionRegistry;
import org.springframework.security.oauth2.client.oidc.session.OidcSessionRegistry;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.DelegatingAuthenticationEntryPoint;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.ForwardLogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.security.web.util.matcher.MediaTypeRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import java.util.LinkedHashMap;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
@Slf4j
public class SecurityConfig {
    private final OAuth2AuthorizedClientService authorizedClientService;
    private final ClientRegistrationRepository clientRegistrationRepository;

    @Value("${app.base-uri}")
    private String appBaseUri;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        CookieCsrfTokenRepository cookieCsrfTokenRepository = CookieCsrfTokenRepository.withHttpOnlyFalse();
        CsrfTokenRequestAttributeHandler csrfTokenRequestAttributeHandler = new CsrfTokenRequestAttributeHandler();
        csrfTokenRequestAttributeHandler.setCsrfRequestAttributeName(null);

        http.authorizeHttpRequests(auth -> auth
                .requestMatchers("/hello").permitAll()
                .requestMatchers("/auth/**").permitAll()
                .anyRequest().authenticated())
//            .oauth2Login(Customizer.withDefaults())
            .csrf(csrf -> csrf
                .csrfTokenRepository(cookieCsrfTokenRepository)
                .csrfTokenRequestHandler(csrfTokenRequestAttributeHandler))
            .cors(Customizer.withDefaults())
            .exceptionHandling(exceptionHandling -> exceptionHandling
                .authenticationEntryPoint(authenticationEntryPoint()))
            .oauth2Login(oauth2Login -> oauth2Login
                .successHandler(new SimpleUrlAuthenticationSuccessHandler(this.appBaseUri)))
            .logout(logout -> logout
                .addLogoutHandler(revokeTokenLogoutHandler())
                    .logoutSuccessHandler(oidcLogoutSuccessHandler())
            )
            .oauth2Client(Customizer.withDefaults());
        return http.build();
    }

    private AuthenticationEntryPoint authenticationEntryPoint() {
        AuthenticationEntryPoint authenticationEntryPoint =
            new LoginUrlAuthenticationEntryPoint("/oauth2/authorization/gateway-client-oidc");
        MediaTypeRequestMatcher textHtmlMatcher = new MediaTypeRequestMatcher(MediaType.TEXT_HTML);
        textHtmlMatcher.setUseEquals(true);

        LinkedHashMap<RequestMatcher, AuthenticationEntryPoint> entryPoints = new LinkedHashMap<>();
        entryPoints.put(textHtmlMatcher, authenticationEntryPoint);

        DelegatingAuthenticationEntryPoint delegatingAuthenticationEntryPoint = new DelegatingAuthenticationEntryPoint(entryPoints);
        delegatingAuthenticationEntryPoint.setDefaultEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED));

        return delegatingAuthenticationEntryPoint;
    }

    private LogoutSuccessHandler oidcLogoutSuccessHandler() {
        return new ForwardLogoutSuccessHandler("/auth/logout");
    }

    private LogoutHandler revokeTokenLogoutHandler() {
        return (request, response, authentication) -> {
            if (authentication != null) {
                String clientRegistrationId = ((OAuth2AuthenticationToken) authentication).getAuthorizedClientRegistrationId();
                String principalName = authentication.getName();
                try {
                    TokenRevocationService tokenRevocationService = new TokenRevocationService(clientRegistrationRepository, authorizedClientService);
                    tokenRevocationService.revokeToken(clientRegistrationId, principalName);
                } catch (Exception e) {
                    log.error("Failed to revoke token: {}", e.getMessage());
                }
            }
        };
    }

    @Bean
    public OidcSessionRegistry oidcSessionRegistry() {
        return new InMemoryOidcSessionRegistry();
    }

    @Bean
    public OidcBackChannelLogoutHandler oidcLogoutHandler(OidcSessionRegistry oidcSessionRegistry) {
        return new OidcBackChannelLogoutHandler(oidcSessionRegistry);
    }
}
