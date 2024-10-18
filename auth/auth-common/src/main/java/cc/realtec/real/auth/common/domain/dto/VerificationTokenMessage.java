package cc.realtec.real.auth.common.domain.dto;

import lombok.Data;

@Data
public class VerificationTokenMessage {
    private String applicationName;
    private String username;
    private String email;
    private String verificationLink;
}
