package cc.realtec.real.auth.server.config;

import cc.realtec.real.auth.common.domain.dto.SysUserDto;
import cc.realtec.real.auth.server.jose.Jwks;
import cc.realtec.real.auth.server.service.MinioService;
import cc.realtec.real.auth.server.service.SysUserService;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.server.authorization.JdbcOAuth2AuthorizationConsentService;
import org.springframework.security.oauth2.server.authorization.JdbcOAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.client.JdbcRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.oauth2.server.authorization.oidc.authentication.OidcUserInfoAuthenticationContext;
import org.springframework.security.oauth2.server.authorization.oidc.authentication.OidcUserInfoAuthenticationToken;
import org.springframework.security.oauth2.server.authorization.settings.AuthorizationServerSettings;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.DelegatingAuthenticationEntryPoint;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.util.matcher.MediaTypeRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import java.util.LinkedHashMap;
import java.util.UUID;
import java.util.function.Function;

@Configuration(proxyBeanMethods = false)
@RequiredArgsConstructor
public class AuthServerConfig {
    private final SysUserService sysUserService;
    private final MinioService minioService;

    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public SecurityFilterChain asSecurityFilterChain(HttpSecurity http) throws Exception {
        OAuth2AuthorizationServerConfigurer authorizationServerConfigurer = OAuth2AuthorizationServerConfigurer.authorizationServer();

        Function<OidcUserInfoAuthenticationContext, OidcUserInfo> userInfoMapper = getUserInfoMapper();

        http.securityMatcher(authorizationServerConfigurer.getEndpointsMatcher())
            .authorizeHttpRequests((authorizeRequests) -> authorizeRequests.anyRequest().authenticated())
            .with(authorizationServerConfigurer, (authorizationServer) ->
                authorizationServer
                    .oidc((oidc) -> oidc
                        .logoutEndpoint(Customizer.withDefaults())
                        .userInfoEndpoint(userInfo -> userInfo.userInfoMapper(userInfoMapper)))
            )
            .cors(Customizer.withDefaults())
            .exceptionHandling((exception) -> exception
//                    .authenticationEntryPoint(authenticationEntryPoint())
                    .defaultAuthenticationEntryPointFor(
                        new LoginUrlAuthenticationEntryPoint("/login"),
                        new MediaTypeRequestMatcher(MediaType.TEXT_HTML)
                    )
            )
            .oauth2ResourceServer(rs -> rs.jwt(Customizer.withDefaults()));
//                .oauth2ResourceServer(rs -> rs.opaqueToken(Customizer.withDefaults()));

        return http.build();
    }

    @NotNull
    private Function<OidcUserInfoAuthenticationContext, OidcUserInfo> getUserInfoMapper() {
        //            return new OidcUserInfo(principal.getToken().getClaims());
        return (context) -> {
            OidcUserInfoAuthenticationToken authenticationContext = context.getAuthentication();
            JwtAuthenticationToken principal = (JwtAuthenticationToken) authenticationContext.getPrincipal();

            SysUserDto sysUserDto = sysUserService.findByUsername(principal.getName());

//            return new OidcUserInfo(principal.getToken().getClaims());
            OidcUserInfo.Builder userBuilder = OidcUserInfo.builder()
                .subject(principal.getName());
            if (sysUserDto != null) {
                if (sysUserDto.getName() != null) {
                    userBuilder.name(sysUserDto.getName());
                }
                userBuilder.nickname(sysUserDto.getNickname())
                    .email(sysUserDto.getEmail());
                if (sysUserDto.getAvatarName() != null) {
                    String avatarUrl = minioService.getObjectUrlByObjectName(sysUserDto.getAvatarName());
                    userBuilder.picture(avatarUrl);
                }

            }
            return userBuilder.build();
        };
    }

    @Bean
    RegisteredClientRepository registeredClientRepository(JdbcTemplate jdbcTemplate) {
        RegisteredClient messageClient = RegisteredClient.withId(UUID.randomUUID().toString())
            .clientId("gateway-client")
            .clientSecret("{noop}secret")
            .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
            .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
            .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
            .redirectUri("http://localhost:8090/login/oauth2/code/gateway-client-oidc")
            .redirectUri("http://localhost:8090/authorized")
            .redirectUri("http://localhost:7090/login/oauth2/code/gateway-client-oidc")
            .redirectUri("http://localhost:7090/authorized")
            .postLogoutRedirectUri("http://localhost:7090/logged-out")
//            .redirectUri("https://localhost:7443/login/oauth2/code/gateway-client-oidc")
//            .redirectUri("https://localhost:7443/authorized")
//            .postLogoutRedirectUri("https://localhost:7443/logged-out")
            .scope(OidcScopes.OPENID)
            .scope(OidcScopes.PROFILE)
            .scope("messages.read")
            .scope("messages.write")
            .build();


        RegisteredClient nextClient = RegisteredClient.withId(UUID.randomUUID().toString())
            .clientId("next-client")
            .clientSecret("{noop}123456")
            .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
            .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
            .redirectUri("http://localhost:3000/api/auth/callback/spring")
            .scope(OidcScopes.OPENID)
            .scope(OidcScopes.EMAIL)
            .scope(OidcScopes.PROFILE)
            .scope("messages.read")
            .scope("messages.write")
            .build();

        RegisteredClient remixClient = RegisteredClient.withId(UUID.randomUUID().toString())
            .clientId("remix-client")
            .clientSecret("{noop}Remix123456")
            .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
            .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
            .redirectUri("http://localhost:5173/auth/callback/spring")
            .scope(OidcScopes.OPENID)
            .scope(OidcScopes.EMAIL)
            .scope(OidcScopes.PROFILE)
            .scope("messages.read")
            .scope("messages.write")
            .build();

        RegisteredClient demoClient = RegisteredClient.withId(UUID.randomUUID().toString())
            .clientId("demo-client")
            .clientSecret("{noop}abcdef")
            .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
            .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
            .redirectUri("http://localhost:3000/auth/callback/demo")
            .scope(OidcScopes.OPENID)
            .scope("demo")
            .build();

        JdbcRegisteredClientRepository jdbcRegisteredClientRepository = new JdbcRegisteredClientRepository(jdbcTemplate);
        RegisteredClient messagingClient = jdbcRegisteredClientRepository.findByClientId("gateway-client");
        if (messagingClient == null) {
            jdbcRegisteredClientRepository.save(messageClient);
        }

        RegisteredClient existNextClient = jdbcRegisteredClientRepository.findByClientId("next-client");
        if (existNextClient == null) {
            jdbcRegisteredClientRepository.save(nextClient);
        }
        RegisteredClient existRemixClient = jdbcRegisteredClientRepository.findByClientId("remix-client");
        if (existRemixClient == null) {
            jdbcRegisteredClientRepository.save(remixClient);
        }
        RegisteredClient existDemoRegisteredClient = jdbcRegisteredClientRepository.findByClientId("demo-client");
        if (existDemoRegisteredClient == null) {
            jdbcRegisteredClientRepository.save(demoClient);
        }
        return jdbcRegisteredClientRepository;
    }

    @Bean
    JdbcOAuth2AuthorizationService jdbcOAuth2AuthorizationService(JdbcOperations jdbcOperations, RegisteredClientRepository registeredClientRepository) {
        return new JdbcOAuth2AuthorizationService(jdbcOperations, registeredClientRepository);
    }

    @Bean
    JdbcOAuth2AuthorizationConsentService jdbcOAuth2AuthorizationConsentService(JdbcOperations jdbcOperations, RegisteredClientRepository registeredClientRepository) {
        return new JdbcOAuth2AuthorizationConsentService(jdbcOperations, registeredClientRepository);
    }

    @Bean
    public JWKSource<SecurityContext> jwkSource() {
        RSAKey rsaKey = Jwks.generateRsa();
        JWKSet jwkSet = new JWKSet(rsaKey);
        return (jwkSelector, securityContext) -> jwkSelector.select(jwkSet);
    }

    @Bean
    public JwtDecoder jwtDecoder(JWKSource<SecurityContext> jwkSource) {
        return OAuth2AuthorizationServerConfiguration.jwtDecoder(jwkSource);
    }

    @Bean
    public AuthorizationServerSettings authorizationServerSettings() {
        return AuthorizationServerSettings.builder()
//                .issuer("http://localhost:9090")
            .build();
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
