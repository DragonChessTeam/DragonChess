package ru.nsu.fit.g14203.engine.pieces;

public class Griffon extends BasicPiece {


    public Griffon(Color c) {
        color = c;
        //magic on level 3
        moves.add(new GLine(3,  new DirectLine(3, new Dot3D(1,1,0), 2), new DirectLine(3, new Dot3D(1,0,0),1 )));
        moves.add(new GLine(3,  new DirectLine(3, new Dot3D(1,1,0), 2), new DirectLine(3, new Dot3D(0,1,0),1 )));
        moves.add(new GLine(3,  new DirectLine(3, new Dot3D(-1,1,0), 2), new DirectLine(3, new Dot3D(-1,0,0),1 )));
        moves.add(new GLine(3,  new DirectLine(3, new Dot3D(-1,1,0), 2), new DirectLine(3, new Dot3D(0,1,0),1 )));
        moves.add(new GLine(3,  new DirectLine(3, new Dot3D(-1,-1,0), 2), new DirectLine(3, new Dot3D(-1,0,0),1 )));
        moves.add(new GLine(3,  new DirectLine(3, new Dot3D(-1,-1,0), 2), new DirectLine(3, new Dot3D(0,-1,0),1 )));
        moves.add(new GLine(3,  new DirectLine(3, new Dot3D(1,-1,0), 2), new DirectLine(3, new Dot3D(1,0,0),1 )));
        moves.add(new GLine(3,  new DirectLine(3, new Dot3D(1,-1,0), 2), new DirectLine(3, new Dot3D(0,-1,0),1 )));
        //triagonal down
        moves.add(new DirectLine(3, new Dot3D(1,1,-1), 1));
        moves.add(new DirectLine(3, new Dot3D(1,-1,-1), 1));
        moves.add(new DirectLine(3, new Dot3D(-1,-1,-1), 1));
        moves.add(new DirectLine(3, new Dot3D(-1,1,-1), 1));
        //triagonal up
        moves.add(new DirectLine(2, new Dot3D(1,1,1), 1));
        moves.add(new DirectLine(2, new Dot3D(1,-1,1), 1));
        moves.add(new DirectLine(2, new Dot3D(-1,-1,1), 1));
        moves.add(new DirectLine(2, new Dot3D(-1,1,1), 1));
        //DiagonalLine on level 2
        moves.add(new DirectLine(2, new Dot3D(1,1,0), 1));
        moves.add(new DirectLine(2, new Dot3D(1,-1,0), 1));
        moves.add(new DirectLine(2, new Dot3D(-1,-1,0), 1));
        moves.add(new DirectLine(2, new Dot3D(-1,1,0), 1));


        captures.addAll(moves);

    }
}
