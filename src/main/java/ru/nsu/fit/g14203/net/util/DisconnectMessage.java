package ru.nsu.fit.g14203.net.util;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DisconnectMessage extends Message {

    public DisconnectMessage() {
        super(TYPE_DISCONNECT);
    }

    public DisconnectMessage(@JsonProperty(value = "content") Object content) {
        this();
    }

    @Override
    public Object getContent() {
        return null;
    }

    @Override
    public String toString() {
        return "{ " +
               "type : disconnect " +
               "}";
    }
}
