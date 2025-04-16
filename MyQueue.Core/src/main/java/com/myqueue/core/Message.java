package com.myqueue.core;

import java.nio.charset.StandardCharsets;

public record Message(long id, byte[] content) {

    final static Message emptyMessage = new Message(0, new byte[0]);

    public static Message empty() {
        return emptyMessage;
    }

    public boolean isEmpty() {
        return content.length == 0;
    }

    public String getString() {
        return new String(this.content, StandardCharsets.UTF_8);
    }
}
