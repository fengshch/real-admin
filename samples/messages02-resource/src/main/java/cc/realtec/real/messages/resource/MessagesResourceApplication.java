package cc.realtec.real.messages.resource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication(scanBasePackages = "cc.realtec.real")
@EnableDiscoveryClient
public class MessagesResourceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MessagesResourceApplication.class, args);
    }

}
