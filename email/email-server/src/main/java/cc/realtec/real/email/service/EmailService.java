package cc.realtec.real.email.service;

import java.util.List;

public interface EmailService {

    void sendHTMLMessage(String from, List<String> to, String subject, String htmlBody);

    void sendSimpleMessage(String from ,List<String> to, String subject, String content);

}
