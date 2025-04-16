package com.myqueue.core.queue;

import com.myqueue.core.Message;

import java.util.LinkedList;

public class Queue {

    private final java.util.Queue<Message> queue = new LinkedList<>();

    public void enqueue(Message message) {
        queue.add(message);
    }

    public Message dequeue() {
        if (queue.isEmpty()) {
            return Message.empty();
        }

        return queue.poll();
    }
}
