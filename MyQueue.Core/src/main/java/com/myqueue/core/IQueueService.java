package com.myqueue.core;

import com.myqueue.core.exceptions.QueueNotFoundException;

public interface IQueueService {

    void enqueue(String name, Message message) throws QueueNotFoundException;
    Message dequeue(String name) throws QueueNotFoundException;
}
