package cc.realtec.real.messages.resource.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
@RequestMapping("/api/v1")
public class MessagesController {

    @Value("#{'${messages}'.split(',')}")
    private String[] messages;

    @Value("${foo}")
    private String foo;

    @GetMapping("/hello")
    public String[] messages(){
        return messages;
    }

    @GetMapping("/foo")
    public Ret foo(){
        return new Ret("foo");
    }

    @Data
    @AllArgsConstructor
    public static class Ret {
      private String message;
    };
}
