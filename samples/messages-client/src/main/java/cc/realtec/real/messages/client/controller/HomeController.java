package cc.realtec.real.messages.client.controller;

import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

    @GetMapping("/")
    @ResponseBody
    public String home(OAuth2AuthenticationToken authentication){
       return "Hello, "  + authentication.getPrincipal().getAttribute("name");
    }
}
