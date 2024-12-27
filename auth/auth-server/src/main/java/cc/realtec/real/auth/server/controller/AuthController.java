package cc.realtec.real.auth.server.controller;

import cc.realtec.real.auth.common.domain.dto.SysUserDto;
import cc.realtec.real.auth.server.domain.ChangePasswordRequest;
import cc.realtec.real.auth.server.domain.ResetPasswordRequest;
import cc.realtec.real.auth.server.domain.SysUserProfile;
import cc.realtec.real.auth.server.domain.SysUserRequest;
import cc.realtec.real.auth.server.domain.converter.SysUserConverter;
import cc.realtec.real.auth.server.po.SysUserPo;
import cc.realtec.real.auth.server.service.EmailVerificationService;
import cc.realtec.real.auth.server.service.MinioService;
import cc.realtec.real.auth.server.service.PasswordResetService;
import cc.realtec.real.auth.server.service.SysUserService;
import cc.realtec.real.common.web.domain.Message;
import cc.realtec.real.common.web.exception.BusinessException;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Set;

@Controller
@RequiredArgsConstructor
@Slf4j
public class AuthController {
    private final EmailVerificationService emailVerificationService;
    private final SysUserService sysUserService;
    private final PasswordResetService passwordResetService;
    private final Validator validator;
    private final MinioService minioService;
    private final SysUserConverter sysUserConverter = SysUserConverter.INSTANCE;

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
        if (request.getMethod().equalsIgnoreCase("POST") && email != null) {
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

    @RequestMapping(value = "/reset-password", method = {RequestMethod.GET, RequestMethod.POST})
    public String resetPassword(HttpServletRequest request,
                                @RequestParam String token,
                                @RequestBody ResetPasswordRequest resetPasswordRequest, Model model) {
        if (!StringUtils.hasLength(token)) {
            model.addAttribute("message", "Token is required.");
            return "reset-password";
        }

        if (request.getMethod().equalsIgnoreCase("GET")) {
            model.asMap().remove("message");
            return "reset-password";
        }

        Set<ConstraintViolation<ResetPasswordRequest>> violations = validator.validate(resetPasswordRequest);
        if (!violations.isEmpty()) {
            StringBuilder errorMessage = new StringBuilder("Validation failed: ");
            for (ConstraintViolation<ResetPasswordRequest> violation : violations) {
                errorMessage.append(violation.getMessage()).append(" ");
            }
            model.addAttribute("message", errorMessage.toString());
            return "reset-password";
        }

        try {
            passwordResetService.resetPassword(resetPasswordRequest);
            model.addAttribute("message", "Password reset successfully.");
        } catch (Exception e) {
            log.error("Failed to reset password: {}", e.getMessage());
            model.addAttribute("message", "Failed to reset password: " + e.getMessage());
        }
        return "reset-password";
    }

    @GetMapping("/reset-password-result")
    public String resetPasswordResult() {
        return "reset-password-result";
    }

    @RequestMapping(value = "/change-password", method = {RequestMethod.GET, RequestMethod.POST})
    public String changePassword(HttpServletRequest request,
                                 @AuthenticationPrincipal User user,
                                 @RequestBody ChangePasswordRequest changePasswordRequest, Model model) {

        if (request.getMethod().equalsIgnoreCase("GET")) {
            model.asMap().remove("message");
            return "change-password";
        }

        Set<ConstraintViolation<ChangePasswordRequest>> violations = validator.validate(changePasswordRequest);
        if (!violations.isEmpty()) {
            StringBuilder errorMessage = new StringBuilder("Validation failed: ");
            for (ConstraintViolation<ChangePasswordRequest> violation : violations) {
                errorMessage.append(violation.getMessage()).append(" ");
            }
            model.addAttribute("message", errorMessage.toString());
            return "change-password";
        }

        try {
            sysUserService.changePassword(user.getUsername(), changePasswordRequest);
//            passwordResetService.resetPassword(resetPasswordRequest);
            model.addAttribute("message", "Password changed successfully.");
        } catch (Exception e) {
            log.error("Failed to change password: {}", e.getMessage());
            model.addAttribute("message", "Failed to change password: " + e.getMessage());
        }
        return "change-password";
    }

    @RequestMapping(value = "/profile", method = {RequestMethod.GET, RequestMethod.POST})
    public String profile(@AuthenticationPrincipal User user,
                          HttpServletRequest request,
                          Model model,
                          @RequestParam(value = "avatarFile", required = false) MultipartFile avatarFile,
                          @RequestBody SysUserProfile sysUserProfile)  {
        String username = user.getUsername();
        SysUserDto sysUserDto = sysUserService.findByUsername(username);

        if (isPostRequest(request) && sysUserProfile != null) {
            String avatarName = null;
            try {
                avatarName = handleAvatarFile(avatarFile);
            } catch (Exception e) {
                log.error("Failed to save avatar file: {}", e.getMessage());
                addMessageToModel(model, "Failed to save avatar file: " + e.getMessage(), "message-error");
                model.addAttribute("sysUser", sysUserProfile);
                return "profile";
            }
            if (avatarName != null) {
                sysUserProfile.setAvatarName(avatarName);
                sysUserProfile.setAvatarUrl(minioService.getObjectUrlByObjectName(avatarName));
            }
            sysUserDto = sysUserConverter.profileToDto(sysUserProfile, sysUserDto);
            sysUserService.update(sysUserDto);
            addMessageToModel(model, "Profile updated successfully.", "message-success");
        } else if (request.getMethod().equalsIgnoreCase("get")) {
            sysUserProfile = sysUserConverter.dtoToProfile(sysUserDto);
            if(sysUserProfile.getAvatarName() != null){
                sysUserProfile.setAvatarUrl(minioService.getObjectUrlByObjectName(sysUserProfile.getAvatarName()));
            }
        }
        model.addAttribute("sysUser", sysUserProfile);
        return "profile";
    }

    private boolean isPostRequest(HttpServletRequest request) {
        return request.getMethod().equalsIgnoreCase("POST");
    }

    private String handleAvatarFile(MultipartFile avatarFile) throws Exception{
        if (avatarFile != null && !avatarFile.isEmpty()) {
            try {
                String fileName = avatarFile.getOriginalFilename();
                return minioService.uploadFile(fileName, avatarFile.getInputStream(), avatarFile.getContentType());
            } catch (Exception e) {
                log.error("Failed to save avatar file: {}", e.getMessage());
                throw new BusinessException("Failed to save avatar file: " + e.getMessage());
            }
        }
        return null;
    }

    private void addMessageToModel(Model model, String content, String type) {
        Message message = new Message();
        message.setType(type);
        message.setContent(content);
        model.addAttribute("message", message);
    }

}
