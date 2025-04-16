package com.myqueue.core;

import com.myqueue.core.exceptions.QueueNotFoundException;

public interface IQueueService {

    void enqueue(String name, String message) throws QueueNotFoundException;
    void enqueue(String name, byte[] content) throws QueueNotFoundException;
    Message dequeue(String name) throws QueueNotFoundException;
    void commit(String name, long messageId) throws QueueNotFoundException;
    void abort(String name, long messageId) throws QueueNotFoundException;
}
