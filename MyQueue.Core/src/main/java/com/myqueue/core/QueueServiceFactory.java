package com.myqueue.core;

import com.myqueue.core.configuration.QueueConfig;

public class QueueServiceFactory {

    public IQueueService create() {
        return new QueueService(new QueueConfig());
    }
}
