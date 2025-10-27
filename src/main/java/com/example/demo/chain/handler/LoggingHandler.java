package com.example.demo.chain.handler;

import com.example.demo.chain.model.RequestContext;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component("loggingHandler")
public class LoggingHandler implements CommandHandler {
    @Override
    public String getName() { return "loggingHandler"; }

    @Override
    public Mono<Void> handle(RequestContext context) {
        System.out.println("📝 Logging context: " + context);
        return Mono.empty();
    }
}
