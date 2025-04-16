package com.myqueue.core;

import com.myqueue.core.configuration.QueueConfig;
import com.myqueue.core.exceptions.QueueNotFoundException;
import com.myqueue.core.queue.MessageFactory;
import com.myqueue.core.queue.Queue;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

class QueueService implements  IQueueService {

    private final MessageFactory messageFactory = new MessageFactory();
    private final Map<String, Queue> queues = new HashMap<>();

    public QueueService(QueueConfig config) {

        for(var name : config.getQueues()) {
            queues.put(name, new Queue());
        }
    }

    @Override
    public void enqueue(String name, String message) throws QueueNotFoundException {

        enqueue(name, message.getBytes(StandardCharsets.UTF_8));
    }

    @Override
    public void enqueue(String name, byte[] content) throws QueueNotFoundException {

        if (!queues.containsKey(name)){
            throw new QueueNotFoundException(String.format("Queue '%s' doesn't exist", name));
        }

        final var message = messageFactory.create(content);
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
