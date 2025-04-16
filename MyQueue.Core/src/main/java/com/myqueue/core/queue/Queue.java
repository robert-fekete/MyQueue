package com.myqueue.core.queue;

import java.util.LinkedList;

public class Queue {

    private final java.util.Queue<Message> queue = new LinkedList<>();

    public void enqueue(Message message) {
        queue.add(message);
    }

    public Message dequeue() {
        return queue.poll();
    }
}
