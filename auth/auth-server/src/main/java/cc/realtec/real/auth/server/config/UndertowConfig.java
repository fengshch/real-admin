package cc.realtec.real.auth.server.config;

import org.springframework.boot.web.embedded.undertow.UndertowServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration(proxyBeanMethods = false)
public class UndertowConfig {

//    @Bean
    public WebServerFactoryCustomizer<UndertowServletWebServerFactory> undertowServerCustomizer() {
        return factory -> factory.addBuilderCustomizers(builder -> {
            // Add an HTTP listener on port 9000
            builder.addHttpListener(9000, "0.0.0.0"); // HTTP port
        });
    }

//    @Bean
    public UndertowServletWebServerFactory undertowServletWebServerFactory() {
        UndertowServletWebServerFactory factory = new UndertowServletWebServerFactory();
        factory.addDeploymentInfoCustomizers(deploymentInfo -> {
            deploymentInfo.addInitialHandlerChainWrapper(handler -> exchange -> {
                // Redirect HTTP to HTTPS
                if (exchange.getRequestScheme().equals("http")) {
                    String host = exchange.getHostAndPort();
                    String query = exchange.getQueryString();
                    exchange.setStatusCode(302); // Redirect status code
                    exchange.getResponseHeaders().put(
                        io.undertow.util.Headers.LOCATION,
                        "https://" + host.replace(":9000", ":9443") + exchange.getRequestURI() +
                            (query != null && !query.isEmpty() ? "?" + query : ""));
                    return;
                }
                handler.handleRequest(exchange);
            });
        });
        return factory;
    }
}
