package cc.realtec.real.auth.server.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import java.io.IOException;

public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {
    private final RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
    private final String defaultTargetUrl;

    public CustomLogoutSuccessHandler(String defaultTargetUrl) {
        this.defaultTargetUrl = defaultTargetUrl;
    }

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        redirectStrategy.sendRedirect(request, response, defaultTargetUrl);
    }
}
