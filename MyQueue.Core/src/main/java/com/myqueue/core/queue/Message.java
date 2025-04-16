package com.myqueue.core.queue;

public class Message {

    final String content;

    public Message(String content) {
        this.content = content;
    }

    public String getContent() {
        return this.content;
    }
}
