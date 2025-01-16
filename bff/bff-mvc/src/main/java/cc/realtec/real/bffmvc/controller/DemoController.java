package cc.realtec.real.bffmvc.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {


    @GetMapping("/hello")
    public Ret hello() {
        return new Ret("hello");
    }

    public static record Ret(String name) {
    }

}
