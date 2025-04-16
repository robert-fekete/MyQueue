package com.myqueue.core.configuration;

import java.util.List;

public class QueueConfig {

    private List<String> queues = List.of("test");

    public List<String> getQueues() {
        return queues;
    }
}
