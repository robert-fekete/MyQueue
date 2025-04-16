package com.myqueue.core.queue;

import com.myqueue.core.Message;

import java.util.LinkedList;

public class Queue {

    private final java.util.Queue<MessageDelivery> queue = new LinkedList<>();

    public void enqueue(Message message) {
        queue.add(new MessageDelivery(message));
    }

    public Message dequeue() {
        if (queue.isEmpty()) {
            return Message.empty();
        }

        return queue.poll().getMessage();
    }
}
