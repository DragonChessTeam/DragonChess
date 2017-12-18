package ru.nsu.fit.g14203.net.util;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Base Message class for MessageChannel.
 */
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

    /**
     * @return JSON serialisable message content
     */
    @JsonProperty(value = "content")
    public abstract Object getContent();

    @Override
    public abstract String toString();
}
