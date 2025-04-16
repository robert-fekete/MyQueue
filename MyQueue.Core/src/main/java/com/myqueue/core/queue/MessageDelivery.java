package com.myqueue.core.queue;

import com.myqueue.core.Message;

public class MessageDelivery {

    private final Message message;
    private int deliveryAttempts = 0;

    public MessageDelivery(Message message) {
        this.message = message;
    }

    public boolean exceedsMaxDeliveryAttempts(int maxDeliveryAttempts) {
        return deliveryAttempts >= maxDeliveryAttempts; // If we reached the max-th attempt we don't put it back on the queue
    }

    public void addDeliveryAttempts() {
        deliveryAttempts++;
    }

    public Message getMessage() {
        return message;
    }

    public long getMessageId() {
        return message.getId();
    }
}
