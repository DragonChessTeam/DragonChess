package ru.nsu.fit.g14203.net.util;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AcceptMessage extends Message {

    public static final String WHITE = "white";
    public static final String BLACK = "black";

    private final String clientColor;

    public AcceptMessage(@JsonProperty(value = "content") String clientColor) {
        super(TYPE_ACCEPT);
        this.clientColor = clientColor;
    }

    @JsonIgnore
    public String getClientColor() {
        return clientColor;
    }

    @Override
    public Object getContent() {
        return clientColor;
    }
}
