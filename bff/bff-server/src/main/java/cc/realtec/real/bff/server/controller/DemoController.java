package cc.realtec.real.bff.server.controller;

import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class DemoController {

    @GetMapping("/")
    public Mono<String> index(OAuth2AuthenticationToken auth2AuthenticationToken) {
        return Mono.just("Hello, " + auth2AuthenticationToken.getPrincipal().getAttribute("name"));
    }

    @GetMapping("/hello")
    public Mono<Ret> hello(OAuth2AuthenticationToken auth2AuthenticationToken) {
        String name = auth2AuthenticationToken.getPrincipal().getAttribute("name");
        return Mono.just(new Ret(name));
    }


    public static record Ret(String name) {
    }

}
