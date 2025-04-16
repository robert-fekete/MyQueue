package com.myqueue.core.queue;

import com.myqueue.core.Message;

import java.util.HashMap;
import java.util.Map;
import java.util.LinkedList;

public class Queue {

    private final java.util.Queue<MessageDelivery> queue = new LinkedList<>();
    private final Map<Long, MessageDelivery> inFlightMessages = new HashMap<>();



    public void enqueue(Message message) {
        queue.add(new MessageDelivery(message));
    }

    public Message dequeue() {
        if (queue.isEmpty()) {
            return Message.empty();
        }

        final var delivery = queue.poll();
        inFlightMessages.put(delivery.getMessageId(), delivery);

        return delivery.getMessage();
    }

    public void commit(long messageId){
        inFlightMessages.remove(messageId);
    }
}
