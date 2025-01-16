package cc.realtec.real.auth.server.config;

public class SecurityConstants {
    public static final String[] PERMIT_URLS = {
        "/v3/api-docs/**",
        "/swagger-ui/**",
        "/swagger-ui.html",
        "/static/**",
        "/favicon.ico",
        "/webjars/**",
        "/login",
        "/logout",
        "/register",
        "/verify-email",
        "/verify-email-result",
        "/forget-password",
        "/reset-password",
        "/hello",
        "/reset-password-result"
    };

    public static final String[] API_PERMIT_URLS = {
        "/api/register",
        "/api/resend-verification"
    };
}