package com.myqueue.core.queue;

import com.myqueue.core.Message;
import com.myqueue.core.configuration.QueueConfig;
import com.myqueue.core.logging.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.LinkedList;

public class Queue {

    private final java.util.Queue<MessageDelivery> queue = new LinkedList<>();
    private final Map<Long, MessageDelivery> inFlightMessages = new HashMap<>();
    private final QueueConfig config;
    private final Logger logger;

    public Queue(QueueConfig config, Logger logger) {
        this.config = config;
        this.logger = logger;
    }

    public void enqueue(Message message) {

        logger.log(String.format("Enqueueing message '%d'", message.getId()));
        queue.add(new MessageDelivery(message));
    }

    public Message dequeue() {
        if (queue.isEmpty()) {
            logger.log("Queue is empty, returning empty message");
            return Message.empty();
        }

        final var delivery = queue.poll();
        delivery.addDeliveryAttempts();
        inFlightMessages.put(delivery.getMessageId(), delivery);

        logger.log(String.format("Dequeueing message '%d'", delivery.getMessageId()));
        return delivery.getMessage();
    }

    public void commit(long messageId){
        logger.log(String.format("ACKing message '%d'", messageId));
        inFlightMessages.remove(messageId);
    }

    public void abort(long messageId){

        if (!inFlightMessages.containsKey(messageId)){
            logger.log(String.format("Message '%d' is not in-flight", messageId));
            return;
        }

        final var delivery = inFlightMessages.get(messageId);

        if (delivery.exceedsMaxDeliveryAttempts(config.MAX_DELIVERY_ATTEMPTS)){
            // Drop
            logger.log(String.format("Message '%d' reached max retry attempt. Dropping it", messageId));
            return;
        }

        logger.log(String.format("Re-queueing message '%d' for another attempt", messageId));
        queue.add(delivery);
    }
}
