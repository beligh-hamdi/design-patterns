package com.example.demo.chain.handler;

import com.example.demo.chain.model.RequestContext;
import reactor.core.publisher.Mono;

public interface CommandHandler {
    String getName();
    Mono<Void> handle(RequestContext context);
}
