package com.example.demo.chain.handler;

import com.example.demo.chain.model.RequestContext;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Component("businessHandler")
public class BusinessHandler implements CommandHandler {
    @Override
    public String getName() { return "businessHandler"; }

    @Override
    public Mono<Void> handle(RequestContext context) {
        System.out.println("💼 Processing business logic...");
        return Mono.delay(Duration.ofMillis(500)).then();
    }
}
