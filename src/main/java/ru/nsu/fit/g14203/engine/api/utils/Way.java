package ru.nsu.fit.g14203.engine.api.utils;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Way {

    public Dot3D end;

    public Dot3D start;

    @JsonCreator
    public Way(@JsonProperty(value = "start") Dot3D pos,
               @JsonProperty(value = "end") Dot3D endPoint) {

        end = endPoint;
        start = pos;
    }
}
