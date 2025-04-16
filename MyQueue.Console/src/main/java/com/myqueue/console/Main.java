package com.myqueue.console;

import com.myqueue.core.QueueServiceFactory;

public class Main {
    public static void main(String[] args) {

        final var factory = new QueueServiceFactory();
        final var queueService = factory.create();
        queueService.test();
    }
}