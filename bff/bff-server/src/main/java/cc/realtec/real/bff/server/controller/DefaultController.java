package cc.realtec.real.bff.server.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.net.URI;

@RestController
public class DefaultController {

    @Value("${app.base-uri}")
    private String appBaseUri;

    @GetMapping("/")
    public Mono<ResponseEntity<Void>> root() {
        return Mono.just(ResponseEntity.status(HttpStatus.FOUND)
            .location(URI.create(appBaseUri))
            .build());
    }

    @GetMapping("/authorized")
    public Mono<ResponseEntity<Void>> authorized() {
        return Mono.just(ResponseEntity.status(HttpStatus.FOUND)
            .location(URI.create(appBaseUri))
            .build());
    }
}
