package com.example.demo.chain.model;

import lombok.Data;

@Data
public class Step {

    private String name;
    private String timeout = "30s";
    private int retry = 3;
    private boolean stopOnError = true;
    private boolean parallel = false;
}
