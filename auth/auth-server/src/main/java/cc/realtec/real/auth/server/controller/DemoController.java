package cc.realtec.real.auth.server.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @GetMapping("/hello")
    public String hello(){
        return "Hello";
    }

    @GetMapping("/business")
    public String error(){
        throw new RuntimeException("business error");
    }
}
