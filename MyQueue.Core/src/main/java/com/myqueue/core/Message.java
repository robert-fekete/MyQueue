package com.myqueue.core;

import java.nio.charset.StandardCharsets;

public class Message {

    final static Message emptyMessage = new Message(new byte[0]);
    final byte[] content;

    public Message(String content) {
        this.content = content.getBytes(StandardCharsets.UTF_8);
    }

    public Message(byte[] content) {
        this.content = content;
    }

    public static Message empty() {
        return emptyMessage;
    }

    public boolean isEmpty() {
        return content.length == 0;
    }

    public String getString() {
        return new String(this.content, StandardCharsets.UTF_8);
    }

    public byte[] getContent() {
        return this.content;
    }
}
