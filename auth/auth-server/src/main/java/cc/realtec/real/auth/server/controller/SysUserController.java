package cc.realtec.real.auth.server.controller;

import cc.realtec.real.auth.server.service.SysUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class SysUserController {
   private final SysUserService sysUserService;

}
