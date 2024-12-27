package cc.realtec.real.auth.server.config;

import cc.realtec.real.auth.server.security.CustomAuthenticationFailureHandler;
import cc.realtec.real.auth.server.security.CustomUserDetailsService;
import cc.realtec.real.auth.server.service.SysUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.server.resource.introspection.OpaqueTokenIntrospector;
import org.springframework.security.oauth2.server.resource.introspection.SpringOpaqueTokenIntrospector;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.DelegatingAuthenticationEntryPoint;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.security.web.util.matcher.MediaTypeRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import java.util.LinkedHashMap;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class DefaultSecurityConfig {
    private final CustomAuthenticationFailureHandler customAuthenticationFailureHandler;
    private final SysUserService sysUserService;

    @Bean
    public OpaqueTokenIntrospector introspector() {
        return new SpringOpaqueTokenIntrospector("http://localhost:9000/oauth2/introspect", "gateway-client", "secret");
    }

//    @Bean
//    public WebSecurityCustomizer webSecurityCustomizer() {
//        return (web) -> web.ignoring().requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html",
//                "/favicon.ico",
//                "/assets/**", "/webjars/**", "/login");
//    }

    /**
     * Configures a security filter chain for JWT-based authentication.
     * <p>
     * This configuration applies to requests matching the "/api/**" pattern.
     * It ensures that all matched requests are authenticated and uses opaque token
     * introspection
     * for OAuth2 resource server configuration. Additionally, it disables CSRF and
     * CORS.
     *
     * @param http the HttpSecurity to modify
     * @return the configured SecurityFilterChain
     * @throws Exception if an error occurs while configuring the security filter
     *                   chain
     */
    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE + 1)
    public SecurityFilterChain jwtSecurityChain(HttpSecurity http) throws Exception {
        http.securityMatchers(matchers -> matchers.requestMatchers("/api/**"))
                .authorizeHttpRequests((authorizeRequests) -> authorizeRequests
                        .requestMatchers(SecurityConstants.API_PERMIT_URLS).permitAll()
                        .anyRequest().authenticated())
//                .exceptionHandling(exception->exception
//                .authenticationEntryPoint(authenticationEntryPoint()))
                .sessionManagement(
                        sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .oauth2ResourceServer(oauth2 -> oauth2.opaqueToken(Customizer.withDefaults()));
        http.csrf(AbstractHttpConfigurer::disable);
//        http.cors(Customizer.withDefaults());
        http.cors(AbstractHttpConfigurer::disable);
        return http.build();
    }

    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE + 2)
    public SecurityFilterChain defaultSecurityChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((authorizeRequests) -> authorizeRequests
                        .requestMatchers(SecurityConstants.PERMIT_URLS).permitAll()
                        .anyRequest().authenticated())
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()))
//            .exceptionHandling(exception->exception
//                .authenticationEntryPoint(authenticationEntryPoint()))
                // .oauth2ResourceServer(oauth2 ->
                // oauth2.opaqueToken(Customizer.withDefaults()))
                .formLogin(formLogin -> formLogin.loginPage("/login")
                        .failureHandler(customAuthenticationFailureHandler)
                        .permitAll());
        http.csrf(AbstractHttpConfigurer::disable);
        http.cors(AbstractHttpConfigurer::disable);
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new CustomUserDetailsService(sysUserService);
    }

    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }

    @Bean
    public HttpSessionEventPublisher httpSessionEventPublisher() {
        return new HttpSessionEventPublisher();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(PasswordEncoder passwordEncoder){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder);
        authenticationProvider.setHideUserNotFoundExceptions(false);
        return authenticationProvider;
    }

    private AuthenticationEntryPoint authenticationEntryPoint() {
        AuthenticationEntryPoint authenticationEntryPoint =
            new LoginUrlAuthenticationEntryPoint("/login");
        MediaTypeRequestMatcher textHtmlMatcher = new MediaTypeRequestMatcher(MediaType.TEXT_HTML);
        textHtmlMatcher.setUseEquals(true);

        MediaTypeRequestMatcher allMatcher = new MediaTypeRequestMatcher(MediaType.TEXT_HTML);
//        BffRequestMatcher bffRequestMatcher = new BffRequestMatcher();
        LinkedHashMap<RequestMatcher, AuthenticationEntryPoint> entryPoints = new LinkedHashMap<>();
        entryPoints.put(textHtmlMatcher, authenticationEntryPoint);
        entryPoints.put(allMatcher, authenticationEntryPoint);

        DelegatingAuthenticationEntryPoint delegatingAuthenticationEntryPoint = new DelegatingAuthenticationEntryPoint(entryPoints);
        delegatingAuthenticationEntryPoint.setDefaultEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED));

        return delegatingAuthenticationEntryPoint;
    }
}
