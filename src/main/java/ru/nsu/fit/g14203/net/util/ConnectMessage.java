package ru.nsu.fit.g14203.net.util;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ConnectMessage extends Message {

    public ConnectMessage() {
        super(TYPE_CONNECT);
    }

    public ConnectMessage(@JsonProperty(value = "content") Object content) {
        this();
    }

    @Override
    public Object getContent() {
        return null;
    }

    @Override
    public String toString() {
        return "{ " +
               "type : connect " +
               "}";
    }
}
