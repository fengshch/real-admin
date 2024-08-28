package cc.realtec.real.gateway.server.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/config")
@RefreshScope
public class ConfigController {

    @Value("${foo:default}")
    private String foo;

    @RequestMapping("/foo")
    public String getFoo(){
        return foo;
    }
}
