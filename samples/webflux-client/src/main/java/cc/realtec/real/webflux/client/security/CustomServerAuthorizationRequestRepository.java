package cc.realtec.real.webflux.client.security;

import org.springframework.security.oauth2.client.web.server.ServerAuthorizationRequestRepository;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest;
import org.springframework.util.Assert;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebSession;
import reactor.core.publisher.Mono;

import java.util.Map;

public class CustomServerAuthorizationRequestRepository implements ServerAuthorizationRequestRepository<OAuth2AuthorizationRequest> {

    private static final String AUTHORIZATION_REQUEST_ATTR_NAME = OAuth2AuthorizationRequest.class.getName();
//    private static final String AUTHORIZATION_REQUEST_ATTR_NAME = "SPRING_SECURITY_SAVED_REQUEST";

    @Override
    public Mono<OAuth2AuthorizationRequest> loadAuthorizationRequest(ServerWebExchange exchange) {
        return Mono.defer(() -> {
            OAuth2AuthorizationRequest authorizationRequest = exchange.getAttribute(AUTHORIZATION_REQUEST_ATTR_NAME);
            if (authorizationRequest == null) {
                System.out.println("Authorization request not found");
                return Mono.empty();
            }
            System.out.println("Loaded authorization request: " + authorizationRequest);
            return Mono.just(authorizationRequest);
        });
    }

    @Override
    public Mono<Void> saveAuthorizationRequest(OAuth2AuthorizationRequest authorizationRequest, ServerWebExchange exchange) {
//        return Mono.fromRunnable(() -> {
//            if (authorizationRequest != null) {
//                exchange.getAttributes().put(AUTHORIZATION_REQUEST_ATTR_NAME, authorizationRequest);
//                System.out.println("Saved authorization request: " + authorizationRequest);
//            }
//        });

        Assert.notNull(authorizationRequest, "authorizationRequest cannot be null");
        Assert.notNull(exchange, "exchange cannot be null");
        // @formatter:off
        return exchange.getSession().map(WebSession::getAttributes).doOnNext(attrs->{
            Assert.hasText(authorizationRequest.getState(), "authorizationRequest.state cannot be empty");
            attrs.put(AUTHORIZATION_REQUEST_ATTR_NAME, authorizationRequest);
            System.out.println("Saved authorization request: " + authorizationRequest);
        }).then();
        // @formatter:on
    }

    @Override
    public Mono<OAuth2AuthorizationRequest> removeAuthorizationRequest(ServerWebExchange exchange) {
        return exchange.getSession().map(WebSession::getAttributes).flatMap(attrs -> {
            Map<String, Object> sessionAttrs = attrs;
            OAuth2AuthorizationRequest authorizationRequest = (OAuth2AuthorizationRequest) sessionAttrs.get(AUTHORIZATION_REQUEST_ATTR_NAME);
            if (authorizationRequest != null) {
                System.out.println("Removed authorization request: " + authorizationRequest);
                return Mono.just(authorizationRequest);
            }
            System.out.println("Authorization request not found for removal");
            return Mono.empty();
        });
    }
}
