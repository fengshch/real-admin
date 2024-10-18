package cc.realtec.real.email.listener;

import cc.realtec.real.email.entity.EmailMessage;
import cc.realtec.real.email.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class EmailListener {
    private final EmailService emailService;

    @RabbitListener(queues = "emailQueue")
    public void receiveMessage(EmailMessage emailMessage) {
        if (emailMessage.getHtml()) {
            emailService.sendHTMLMessage(emailMessage.getFrom(), List.of(emailMessage.getTo()), emailMessage.getSubject(), emailMessage.getBody());
        } else {
            emailService.sendSimpleMessage(emailMessage.getFrom(), List.of(emailMessage.getTo()), emailMessage.getSubject(), emailMessage.getBody());
        }
    }
}
