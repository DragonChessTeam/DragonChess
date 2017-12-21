package ru.nsu.fit.g14203.net.channel;

public class ConnectMessage extends Message {

    public ConnectMessage() {
        super(TYPE_CONNECT);
    }

    @Override
    public String toString() {
        return "{ " +
               "type : connect " +
               "}";
    }
}
