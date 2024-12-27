package cc.realtec.real.demo.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DefaultController {

    @Value("${app.base-uri}")
    private String appBaseUri;

    @GetMapping("/")
    public String root() {
        return "redirect:" + appBaseUri;
    }

//    @GetMapping("/authorized")
//    public String authorized(){
//        return "redirect:" + appBaseUri;
//    }
}