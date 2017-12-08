package ru.nsu.fit.g14203.engine.utils;

public class Dot3D {

    public int x;

    public int y;

    public int z;

    public Dot3D() {}

    public Dot3D(int x, int y, int z) {
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

}
