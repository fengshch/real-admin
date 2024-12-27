package cc.realtec.real.bffmvc.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

public class OAuth2AuthorizedClientFilter extends GenericFilterBean {
    private final SecurityContextHolderStrategy securityContextHolderStrategy = SecurityContextHolder.getContextHolderStrategy();

    private final OAuth2AuthorizedClientManager authorizedClientManager;

    public OAuth2AuthorizedClientFilter(OAuth2AuthorizedClientManager authorizedClientManager) {
        this.authorizedClientManager = authorizedClientManager;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        Authentication principal = (Authentication) request.getUserPrincipal();
        if (principal != null) {
            if (principal instanceof OAuth2AuthenticationToken token) {
                String clientRegistrationId = token.getAuthorizedClientRegistrationId();
                if (clientRegistrationId != null) {
                    OAuth2AuthorizeRequest authorizeRequest = OAuth2AuthorizeRequest
                        .withClientRegistrationId(clientRegistrationId)
                        .principal(principal)
                        .build();
                    try{
                        this.authorizedClientManager.authorize(authorizeRequest);
                    }catch (Exception e){
                        createAnonymousAuthentication();
                        throw new AuthorizationDeniedException("Not authorized");
                    }
                }
            }
        }
        chain.doFilter(servletRequest, servletResponse);
    }

    private void createAnonymousAuthentication(){
        String key = "anonymousKey";
        Object principal = "anonymousUser";
        var authorities = AuthorityUtils.createAuthorityList("ROLE_ANONYMOUS");

        // Create the AnonymousAuthenticationToken
        AnonymousAuthenticationToken anonymousToken = new AnonymousAuthenticationToken(key, principal, authorities);

        // Set the AnonymousAuthenticationToken in the SecurityContextHolder
        securityContextHolderStrategy.getContext().setAuthentication(anonymousToken);
    }

}
