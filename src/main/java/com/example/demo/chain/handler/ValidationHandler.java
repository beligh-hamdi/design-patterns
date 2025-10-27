package com.example.demo.chain.handler;

import com.example.demo.chain.model.RequestContext;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component("validationHandler")
public class ValidationHandler implements CommandHandler {
    @Override
    public String getName() { return "validationHandler"; }

    @Override
    public Mono<Void> handle(RequestContext context) {
        System.out.println("✅ Validating request...");
        if (context.get("user") == null) {
            return Mono.error(new RuntimeException("User missing"));
        }
        return Mono.empty();
    }
}
