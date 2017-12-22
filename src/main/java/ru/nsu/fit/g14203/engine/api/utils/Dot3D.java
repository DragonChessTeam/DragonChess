package ru.nsu.fit.g14203.engine.api.utils;

public class Dot3D {

    public int x;

    public int y;

    public int z;

    public Dot3D() {}

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof Dot3D)) return false;
        Dot3D other = (Dot3D)o;
        return x == other.x && y == other.y && z == other.z;
    }

    @Override
    public String toString() {
        return "["+x+","+y+","+z+"]";
    }

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



    public static final Dot3D UP = new Dot3D(0,0,1);
    public static final Dot3D DOWN = new Dot3D(0,0,-1);
    public static final Dot3D LEFT = new Dot3D(-1,0,0);
    public static final Dot3D RIGHT = new Dot3D(1,0,0);
    public static final Dot3D FORWARD = new Dot3D(0,1,0);
    public static final Dot3D BACKWARD = new Dot3D(0,-1,0);
    public static final Dot3D NONE = new Dot3D(0,0,0);
}
