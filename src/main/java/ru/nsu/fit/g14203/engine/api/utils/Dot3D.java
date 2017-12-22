package ru.nsu.fit.g14203.engine.api.utils;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;
public class Dot3D {

    public int x;

    public int y;

    public int z;

    public Dot3D() {}


    @Override
    public String toString() {
        return "["+x+","+y+","+z+"]";
    }

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



    public static final Dot3D UP = new Dot3D(0,0,1);
    public static final Dot3D DOWN = new Dot3D(0,0,-1);
    public static final Dot3D LEFT = new Dot3D(-1,0,0);
    public static final Dot3D RIGHT = new Dot3D(1,0,0);
    public static final Dot3D FORWARD = new Dot3D(0,-1,0);
    public static final Dot3D BACKWARD = new Dot3D(0,1,0);
    public static final Dot3D NONE = new Dot3D(0,0,0);
}
