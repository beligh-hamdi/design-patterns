package com.example.demo.chain;

import com.example.demo.chain.model.RequestContext;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class ChainController {

    private final ChainService chain;

    @GetMapping("/run")
    public Mono<String> run() {
        RequestContext<?> ctx = new RequestContext<>();
        return chain.execute(ctx)
                .thenReturn("Done! Correlation: " + ctx.getCorrelationId());
    }
}
