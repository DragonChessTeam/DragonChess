package ru.nsu.fit.g14203.net.util;

public class DisconnectMessage extends Message {

    public DisconnectMessage() {
        super(TYPE_DISCONNECT);
    }

    @Override
    public String toString() {
        return "{ " +
               "type : disconnect " +
               "}";
    }
}
