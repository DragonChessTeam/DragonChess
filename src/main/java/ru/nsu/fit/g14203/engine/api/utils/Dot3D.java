package ru.nsu.fit.g14203.engine.api.utils;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class Dot3D {

    public int x;

    public int y;

    public int z;

    public Dot3D() {}

    @JsonCreator
    public Dot3D(@JsonProperty(value = "x") int x,
                 @JsonProperty(value = "y") int y,
                 @JsonProperty(value = "z") int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Dot3D sum(Dot3D other) {
        return new Dot3D(this.x+other.x, this.y+other.y, this.z+other.z);
    }

    public Dot3D sub(Dot3D other) {
        return new Dot3D(this.x-other.x, this.y-other.y, this.z-other.z);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        final Dot3D dot3D = (Dot3D) o;
        return x == dot3D.x &&
                y == dot3D.y &&
                z == dot3D.z;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
    }
}
