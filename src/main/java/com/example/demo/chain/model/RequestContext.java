package com.example.demo.chain.model;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class RequestContext<T> {
    private final Map<String, T> data = new HashMap<>();
    @Getter
    private final String correlationId = UUID.randomUUID().toString();

    public void put(String key, T value) { data.put(key, value); }
    public Object get(String key) { return data.get(key); }

    @Override
    public String toString() { return "Context{" + "id=" + correlationId + ", data=" + data + '}'; }
}
