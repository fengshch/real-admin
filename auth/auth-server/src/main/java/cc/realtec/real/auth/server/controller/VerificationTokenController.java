package cc.realtec.real.auth.server.controller;

import cc.realtec.real.auth.server.service.EmailVerificationService;
import cc.realtec.real.auth.server.service.SysUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class VerificationTokenController {
    private final EmailVerificationService emailVerificationService;
    private final SysUserService sysUserService;

//    @GetMapping("/verification-token")
//    public String verificationToken() {
//        return "verificationToken";
//    }
//
//    @PostMapping("/send-verification-token")
//    public void sendVerificationToken(String username) {
//        SysUserDto sysUserDto = sysUserService.findByUsername(username);
//        emailVerificationService.sendVerificationToken(sysUserDto);
//    }
}
