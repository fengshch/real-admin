package cc.realtec.real.demo01.controller;

import cc.realtec.real.common.web.exception.ResourceNotFoundException;
import cc.realtec.real.demo01.entity.Demo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public ResponseEntity<Object> hello() {
        return ResponseEntity.ok("Hello World!");
    }

    @GetMapping("/demo")
    public Demo demo(){
        return Demo.builder().id("001").name("demo001").build();
    }

    @GetMapping("/fail")
    public Demo error(){
       throw new RuntimeException("test");
    }

    @GetMapping("/not_found")
    public Demo notFound(){
        throw new ResourceNotFoundException("demo not found");
    }
}
