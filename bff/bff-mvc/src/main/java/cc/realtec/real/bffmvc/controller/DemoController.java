package cc.realtec.real.bffmvc.controller;

import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {


    @GetMapping("/hello")
    public Ret hello(OAuth2AuthenticationToken auth2AuthenticationToken) {
        String name = auth2AuthenticationToken.getPrincipal().getAttribute("name");
        return new Ret(name);
    }

    public static record Ret(String name) {
    }

}
