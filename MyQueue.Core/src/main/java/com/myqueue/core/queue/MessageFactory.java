package com.myqueue.core.queue;

import com.myqueue.core.Message;

public class MessageFactory {

    public Message create(byte[] content) {

        return new Message(content);
    }
}
