package com.example.demo.chain;

import com.example.demo.chain.model.RequestContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ChainServiceTest {

    @Autowired
    private ChainService chain;

    @Test
    void execute() {
        RequestContext ctx = new RequestContext();
        chain.execute(ctx)
                .thenReturn("Done! Correlation: " + ctx.getCorrelationId());
    }

}