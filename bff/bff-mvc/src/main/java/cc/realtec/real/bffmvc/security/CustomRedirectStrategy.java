package cc.realtec.real.bffmvc.security;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.log.LogMessage;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.util.Assert;

import java.io.IOException;

public class CustomRedirectStrategy extends DefaultRedirectStrategy {
    private HttpStatus statusCode = HttpStatus.FOUND;

    @Override
    public void sendRedirect(HttpServletRequest request, HttpServletResponse response, String url) throws IOException {
        String redirectUrl = calculateRedirectUrl(request.getContextPath(), url);
        redirectUrl = response.encodeRedirectURL(redirectUrl);
        if (this.logger.isDebugEnabled()) {
            this.logger.debug(LogMessage.format("Redirecting to %s", redirectUrl));
        }
        if (this.statusCode == HttpStatus.FOUND) {

            RequestDispatcher dispatcher = request.getRequestDispatcher(redirectUrl);
            try {
                response.addHeader(HttpHeaders.ACCEPT, String.valueOf(MediaType.TEXT_HTML));
                response.addHeader("x", "y");
                dispatcher.forward(request, response);
            } catch (ServletException e) {
                throw new RuntimeException(e);
            }
//            response.addHeader(HttpHeaders.ACCEPT, String.valueOf(MediaType.TEXT_HTML));
//            response.addHeader("x", "y");
//            response.sendRedirect(redirectUrl);
        }
        else {
            response.setHeader(HttpHeaders.LOCATION, redirectUrl);
            response.setStatus(this.statusCode.value());
            response.getWriter().flush();
        }
    }

    public void setStatusCode(HttpStatus statusCode) {
        Assert.notNull(statusCode, "statusCode cannot be null");
        this.statusCode = statusCode;
    }
}
