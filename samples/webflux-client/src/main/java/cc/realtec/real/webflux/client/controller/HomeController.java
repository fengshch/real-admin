package cc.realtec.real.webflux.client.controller;

import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import reactor.core.publisher.Mono;

@Controller
public class HomeController {

    @GetMapping("/")
    @ResponseBody
    public Mono<String> home(OAuth2AuthenticationToken authentication) {
        return Mono.just("Hello, " + authentication.getPrincipal().getAttributes().get("name"));
    }

    @GetMapping("/user")
    public Mono<String> getUser() {
        return Mono.just("Hello, Reactive World");
    }
}
