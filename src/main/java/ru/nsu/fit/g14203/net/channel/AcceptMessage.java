package ru.nsu.fit.g14203.net.channel;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import ru.nsu.fit.g14203.engine.api.utils.Color;

public class AcceptMessage extends Message {

    private final Color color;

    @JsonCreator
    public AcceptMessage(@JsonProperty(value = "color") Color color) {
        super(TYPE_ACCEPT);
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    @Override
    public String toString() {
        return "{ " +
               "type : accept, " +
               "color : " + color.name() + " " +
               "}";
    }
}
