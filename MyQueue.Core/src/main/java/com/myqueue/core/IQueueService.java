package com.myqueue.core;

import com.myqueue.core.exceptions.QueueNotFoundException;
import com.myqueue.core.queue.Message;

public interface IQueueService {

    void enqueue(String name, Message message) throws QueueNotFoundException;
    Message dequeue(String name) throws QueueNotFoundException;
}
