package ru.nsu.fit.g14203.net.util;

import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class Message {

    public static final int TYPE_CONNECT        = 1;
    public static final int TYPE_ACCEPT         = 2;
    public static final int TYPE_UPDATE         = 3;
    public static final int TYPE_DISCONNECT     = 4;

    private final int type;

    public Message(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    @JsonProperty(value = "content")
    public abstract Object getContent();
}
