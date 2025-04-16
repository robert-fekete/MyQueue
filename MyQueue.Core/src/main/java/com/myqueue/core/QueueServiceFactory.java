package com.myqueue.core;

public class QueueServiceFactory {

    public IQueueService create() {
        return new QueueService();
    }
}
