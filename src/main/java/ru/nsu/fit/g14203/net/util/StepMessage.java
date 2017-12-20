package ru.nsu.fit.g14203.net.util;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import ru.nsu.fit.g14203.engine.api.utils.Color;
import ru.nsu.fit.g14203.engine.api.utils.Way;

public class StepMessage extends Message {

    private final Color color;
    private final Way way;

    @JsonCreator
    public StepMessage(@JsonProperty(value = "type") int type,
                       @JsonProperty(value = "color") Color color,
                       @JsonProperty(value = "way") Way way) {
        super(type);

        this.color = color;
        this.way = way;
    }

    public Color getColor() {
        return color;
    }

    public Way getWay() {
        return way;
    }

    @Override
    public String toString() {
        return "{ " +
               "color : " + color.name() + ", " +
               "way : { " +
                     "start : { " +
                             "x : " + way.start.x + ", " +
                             "y : " + way.start.y + ", " +
                             "z : " + way.start.z + ", " +
                             "}, " +
                     "end : : { " +
                             "x : " + way.end.x + ", " +
                             "y : " + way.end.y + ", " +
                             "z : " + way.end.z + ", " +
                             "} " +
                     "} " +
               "}";
    }
}
