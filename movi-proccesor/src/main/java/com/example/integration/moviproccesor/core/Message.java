package com.example.integration.moviproccesor.core;

import org.springframework.messaging.MessageHeaders;

public interface Message<T> {
    T getPayload();

    MessageHeaders getHeaders();
}
