package com.myqueue.core.configuration;

import java.util.List;

public class QueueConfig {

    public final int MAX_DELIVERY_ATTEMPTS = 3;
    private final List<String> queues = List.of("test");

    public List<String> getQueues() {
        return queues;
    }
}
