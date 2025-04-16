package com.myqueue.core.queue;

import com.myqueue.core.Message;
import com.myqueue.core.configuration.QueueConfig;

import java.util.HashMap;
import java.util.Map;
import java.util.LinkedList;

public class Queue {

    private final java.util.Queue<MessageDelivery> queue = new LinkedList<>();
    private final Map<Long, MessageDelivery> inFlightMessages = new HashMap<>();
    private final QueueConfig config;

    public Queue(QueueConfig config) {
        this.config = config;
    }

    public void enqueue(Message message) {
        queue.add(new MessageDelivery(message));
    }

    public Message dequeue() {
        if (queue.isEmpty()) {
            return Message.empty();
        }

        final var delivery = queue.poll();
        delivery.addDeliveryAttempts();
        inFlightMessages.put(delivery.getMessageId(), delivery);

        return delivery.getMessage();
    }

    public void commit(long messageId){
        inFlightMessages.remove(messageId);
    }

    public void abort(long messageId){

        if (!inFlightMessages.containsKey(messageId)){
            return;
        }

        final var delivery = inFlightMessages.get(messageId);

        if (delivery.exceedsMaxDeliveryAttempts(config.MAX_DELIVERY_ATTEMPTS)){
            // Drop
            return;
        }

        queue.add(delivery);
    }
}
