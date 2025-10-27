package com.example.demo.chain.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class HandlerDefinition {
    private String name;
    private String timeout;
    private int retry;
    private boolean stopOnError;
    private boolean parallel;

}
