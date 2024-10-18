package cc.realtec.real.auth.server.domain;

import lombok.Data;
import org.apache.commons.lang3.RandomStringUtils;

import java.time.LocalDateTime;

@Data
public class EmailVerification {
    private String userId;
    private String username;
    private String email;
    private String token;
    private Boolean sent;
    private LocalDateTime expiredAt;

    public EmailVerification(String userId){
        this.userId = userId;
        this.token = RandomStringUtils.randomAlphanumeric(6);
        this.expiredAt = LocalDateTime.now().plusHours(24);
    }
}
