package ru.nsu.fit.g14203.net.util;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Base Message class for MessageChannel.
 */
public abstract class Message {

    public static final int TYPE_CONNECT        = 1;
    public static final int TYPE_ACCEPT         = 2;
    public static final int TYPE_DISCONNECT     = 3;
    public static final int TYPE_MOVE           = 4;
    public static final int TYPE_CAPTURE        = 5;

    private final int type;

    @JsonCreator
    public Message(@JsonProperty(value = "type") int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    @Override
    public abstract String toString();
}
