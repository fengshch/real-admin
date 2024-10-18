package cc.realtec.real.xxljob.controller;

import cc.realtec.real.xxljob.controller.annotation.PermissionLimit;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/demo")
public class DemoApiController {

    @PostMapping("/hello/{username}")
    @PermissionLimit(limit = false)
    public String hello(@PathVariable String username) {
        return "Hello " + username + " World!";
    }

}
