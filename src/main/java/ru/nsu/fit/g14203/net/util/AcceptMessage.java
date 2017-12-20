package ru.nsu.fit.g14203.net.util;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import ru.nsu.fit.g14203.engine.api.utils.Color;

public class AcceptMessage extends Message {

    public static final String WHITE = "white";
    public static final String BLACK = "black";

    private final Color clientColor;

    public AcceptMessage(Color clientColor) {
        super(TYPE_ACCEPT);
        this.clientColor = clientColor;
    }

    public AcceptMessage(@JsonProperty(value = "content") String content) {
        super(TYPE_ACCEPT);
        this.clientColor = Color.valueOf(content);
    }

    @JsonIgnore
    public Color getClientColor() {
        return clientColor;
    }

    @Override
    public Object getContent() {
        return clientColor.name();
    }

    @Override
    public String toString() {
        return "{ " +
               "type : accept, " +
               "color : " + clientColor.name() + " " +
               "}";
    }
}
