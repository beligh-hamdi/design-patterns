package com.example.demo.chain.handler;

import com.example.demo.chain.model.RequestContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Slf4j
@Component("authHandler")
public class AuthHandler implements CommandHandler {
    @Override
    public String getName() { return "authHandler"; }

    @Override
    public Mono<Void> handle(RequestContext context) {
        log.info("🔐 Authenticating...");
        context.put("user", "john.doe");
        return Mono.empty();
    }
}
