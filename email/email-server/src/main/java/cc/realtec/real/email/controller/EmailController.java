package cc.realtec.real.email.controller;

import cc.realtec.real.email.entity.EmailMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class EmailController {

    private final RabbitTemplate rabbitTemplate;

    @PostMapping("/send-email")
    public String sendEmail(@RequestBody EmailMessage emailMessage){
        rabbitTemplate.convertAndSend("emailQueue", emailMessage);
        return "Message sent to RabbitMQ!";
    }
}
