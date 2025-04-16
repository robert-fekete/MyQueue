package com.myqueue.core.queue;

import com.myqueue.core.Message;

public class MessageFactory {

    public long currentId = 1;
    public Message create(byte[] content) {

        currentId++;
        return new Message(currentId, content);
    }
}
