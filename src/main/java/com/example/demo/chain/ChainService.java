package com.example.demo.chain;

import com.example.demo.chain.handler.CommandHandler;
import com.example.demo.chain.model.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Component
public class ChainService {

    private final Map<String, CommandHandler> handlerMap;
    private final ChainProperties properties;

    public ChainService(List<CommandHandler> commandHandlers, ChainProperties properties) {
        this.handlerMap = commandHandlers.stream()
                .collect(Collectors.toMap(CommandHandler::getName, h -> h));
        this.properties = properties;
    }

    public Mono<Void> execute(RequestContext<?> context) {
        log.info("🚀 Starting chain with correlation ID: {}", context.getCorrelationId());

        List<HandlerDefinition> steps = properties.getSteps();
        Mono<Void> chain = Mono.empty();

        for (HandlerDefinition step : steps) {
            CommandHandler commandHandler = handlerMap.get(step.getName());
            if (commandHandler == null) {
                System.out.println("⚠️ Handler not found: " + step.getName());
                continue;
            }

            Mono<Void> handlerMono = Mono.defer(() -> commandHandler.handle(context))
                    .timeout(Duration.parse("PT" + step.getTimeout().replace("s", "S")))
                    .retry(step.getRetry())
                    .doOnError(e -> log.error("❌ Error in {}: {}", step.getName(), e.getMessage()))
                    .onErrorResume(e -> step.isStopOnError() ? Mono.error(e) : Mono.empty());

            if (step.isParallel()) {
                chain = chain.then(Mono.when(handlerMono));
            } else {
                chain = chain.then(handlerMono);
            }
        }

        return chain
                .doOnSuccess(v -> log.info("✅ Chain completed successfully"))
                .doOnError(e -> log.error("💥 Chain stopped: {}", e.getMessage()));
    }
}
