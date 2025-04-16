package com.myqueue.console;

import com.myqueue.core.QueueServiceFactory;
import com.myqueue.core.exceptions.QueueNotFoundException;
import com.myqueue.core.Message;

public class Main {
    public static void main(String[] args) {

        final var factory = new QueueServiceFactory();
        final var queueService = factory.create();
        try {
            queueService.enqueue("test", new Message("Hello"));
            queueService.enqueue("test", new Message("World"));
            System.out.println(queueService.dequeue("test").getString());
            queueService.enqueue("test", new Message("!"));
            System.out.println(queueService.dequeue("test").getString());
            System.out.println(queueService.dequeue("test").getString());
        } catch (QueueNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}