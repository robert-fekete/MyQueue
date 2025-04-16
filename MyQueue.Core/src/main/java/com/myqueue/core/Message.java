package com.myqueue.core;

import java.nio.charset.StandardCharsets;

public class Message {

    final byte[] content;

    public Message(String content) {
        this.content = content.getBytes(StandardCharsets.UTF_8);
    }

    public Message(byte[] content) {
        this.content = content;
    }

    public String getString() {
        return new String(this.content, StandardCharsets.UTF_8);
    }

    public byte[] getContent() {
        return this.content;
    }
}
