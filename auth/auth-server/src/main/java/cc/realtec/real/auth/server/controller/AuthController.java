package cc.realtec.real.auth.server.controller;

import cc.realtec.real.auth.server.domain.SysUserRequest;
import cc.realtec.real.auth.server.po.SysUserPo;
import cc.realtec.real.auth.server.service.EmailVerificationService;
import cc.realtec.real.auth.server.service.PasswordResetService;
import cc.realtec.real.auth.server.service.SysUserService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Controller
@RequiredArgsConstructor
@Slf4j
public class AuthController {
    private final EmailVerificationService emailVerificationService;
    private final SysUserService sysUserService;
    private final PasswordResetService passwordResetService;

    @GetMapping("/login")
    public String login(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);  // Retrieve the existing session, if any

        if (session != null) {
            // Get the error message from the session
            String errorMessage = (String) session.getAttribute("errorMessage");

            if (errorMessage != null) {
                // Add the error message to the model and remove it from the session
                model.addAttribute("errorMessage", errorMessage);
                session.removeAttribute("errorMessage");  // Clear it to prevent reuse
            }
        }

        return "login";  // Return the login page (login.html)
    }

    @RequestMapping(value = "/register", method = {RequestMethod.GET, RequestMethod.POST})
    public String register(HttpServletRequest request, Model model, @RequestBody(required = false) SysUserRequest userRequest) {
        if (request.getMethod().equalsIgnoreCase("POST") && userRequest != null) {
            try {
                SysUserPo sysUserPo = sysUserService.create(userRequest);
                String baseUrl = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
                emailVerificationService.sendVerificationToken(sysUserPo, baseUrl);
                log.info("User registered successfully: {}", sysUserPo);
                model.addAttribute("message", "User registered successfully, Please check your email.");
            } catch (Exception e) {
                log.error("User registration failed: {}", e.getMessage());
                model.addAttribute("message", "Registration failed: " + e.getMessage());
            }
        }
        return "register";
    }

    @GetMapping("/verify-email-result")
    public String showVerifyEmailResult(Model model, @RequestParam String token) {
        try {
            emailVerificationService.verifyEmail(token);
            log.info("Email verification succeeded");
            model.addAttribute("message", "Email verification succeeded");
            model.addAttribute("success", true);
        } catch (Exception e) {
            log.error("Email verification failed: {}", e.getMessage());
            model.addAttribute("message", "Email verification failed: " + e.getMessage());
            model.addAttribute("success", false);
        }
        return "verify-email-result";
    }

    @RequestMapping(value = "/verify-email", method = {RequestMethod.GET, RequestMethod.POST})
    public Object verifyEmail(HttpServletRequest request, Model model, @RequestParam(required = false) String email) {
        if (request.getMethod().equalsIgnoreCase("POST") && email != null) {
            try {
                String baseUrl = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
                emailVerificationService.sendVerificationTokenByEmail(email, baseUrl);
                model.addAttribute("message", "Verification email sent successfully.");
            } catch (Exception e) {
                log.error("Failed to send verification email: {}", e.getMessage());
                model.addAttribute("message", "Failed to send verification email: " + e.getMessage());
            }
        }
        return "verify-email";  // Return the view name for GET requests
    }

    @RequestMapping(value = "/forget-password", method = {RequestMethod.GET, RequestMethod.POST})
    public String forgotPassword(HttpServletRequest request, Model model, @RequestParam(required = false) String email) {
        if(request.getMethod().equalsIgnoreCase("POST") && email != null) {
            try {
                String baseUrl = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
                passwordResetService.sendResetPasswordTokenByEmail(email, baseUrl);
                model.addAttribute("message", "Reset password email sent successfully.");
            } catch (Exception e) {
                log.error("Failed to send reset password email: {}", e.getMessage());
                model.addAttribute("message", "Failed to send reset password email: " + e.getMessage());
            }
        }
        return "forget-password";
    }

    @GetMapping("/reset-password")
    public String resetPassword() {
        return "reset-password";
    }

    @GetMapping("/reset-password-result")
    public String resetPasswordResult() {
        return "reset-password-result";
    }
}
