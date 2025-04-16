package com.myqueue.core;

import com.myqueue.core.configuration.QueueConfig;
import com.myqueue.core.exceptions.QueueNotFoundException;
import com.myqueue.core.queue.Queue;

import java.util.HashMap;
import java.util.Map;

class QueueService implements  IQueueService {

    private final Map<String, Queue> queues = new HashMap<>();

    public QueueService(QueueConfig config) {

        for(var name : config.getQueues()) {
            queues.put(name, new Queue());
        }
    }

    @Override
    public void enqueue(String name, Message message) throws QueueNotFoundException {

        if (!queues.containsKey(name)){
            throw new QueueNotFoundException(String.format("Queue '%s' doesn't exist", name));
        }

        queues.get(name).enqueue(message);
    }

    @Override
    public Message dequeue(String name) throws QueueNotFoundException {
        if (!queues.containsKey(name)){
            throw new QueueNotFoundException(String.format("Queue '%s' doesn't exist", name));
        }

        return queues.get(name).dequeue();
    }
}
