package ru.nsu.fit.g14203.engine.pieces;

public class Unicorn extends BasicPiece {
    public Unicorn(Color c) {
        color = c;
        moves.add(new GLine(2, new DirectLine(2, new Dot3D(0,1,0),1), new DirectLine(2, new Dot3D(1,0,0),2)));
        moves.add(new GLine(2, new DirectLine(2, new Dot3D(0,1,0),1), new DirectLine(2, new Dot3D(-1,0,0),2)));
        moves.add(new GLine(2, new DirectLine(2, new Dot3D(0,1,0),2), new DirectLine(2, new Dot3D(-1,0,0),1)));
        moves.add(new GLine(2, new DirectLine(2, new Dot3D(0,1,0),2), new DirectLine(2, new Dot3D(1,0,0),1)));
        moves.add(new GLine(2, new DirectLine(2, new Dot3D(0,-1,0),1), new DirectLine(2, new Dot3D(1,0,0),2)));
        moves.add(new GLine(2, new DirectLine(2, new Dot3D(0,-1,0),1), new DirectLine(2, new Dot3D(-1,0,0),2)));
        moves.add(new GLine(2, new DirectLine(2, new Dot3D(0,-1,0),2), new DirectLine(2, new Dot3D(-1,0,0),1)));
        moves.add(new GLine(2, new DirectLine(2, new Dot3D(0,-1,0),2), new DirectLine(2, new Dot3D(1,0,0),1)));
        captures = moves;
    }
}
