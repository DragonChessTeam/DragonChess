package ru.nsu.fit.g14203.engine;

import ru.nsu.fit.g14203.engine.api.Observer;
import ru.nsu.fit.g14203.engine.api.Piece;
import ru.nsu.fit.g14203.engine.api.utils.Dot3D;
import ru.nsu.fit.g14203.engine.api.utils.UpdateEntry;
import ru.nsu.fit.g14203.engine.pieces.King;

import java.util.List;

import static ru.nsu.fit.g14203.engine.api.utils.Color.WHITE;

public class ObserverMock implements Observer {
    private Piece[][][] boards = new Piece[12][8][3];

    @Override
    public void update(List<UpdateEntry> updates) {

        for (UpdateEntry ue : updates) {
            if (ue.pieceToPlace != null) {
                Dot3D toPlace = ue.fullPath.get(ue.fullPath.size() - 1);
                boards[toPlace.x][toPlace.y][toPlace.z] = ue.pieceToPlace;
            } else {
                Dot3D toPlace = ue.fullPath.get(ue.fullPath.size() - 1);
                Dot3D fromPlace = ue.fullPath.get(0);
                boards[toPlace.x][toPlace.y][toPlace.z] = boards[fromPlace.x][fromPlace.y][fromPlace.z];
                boards[fromPlace.x][fromPlace.y][fromPlace.z] = null;
            }



        }

        drawBoards();
    }

    private void drawBoards() {
        for (int z = 0; z < boards[0][0].length; z++) {
            System.out.println(""+z);
            for (int y = 0; y < boards[0].length; y++) {
                for (int x = 0; x < boards.length; x++) {
                        if (boards[x][y][z] instanceof King) {
                            if (boards[x][y][z].getColor() == WHITE) {
                                System.out.print("*");
                            } else {
                                System.out.print("#");
                            }
                        }
                    else
                        System.out.print("O");
                }
                System.out.println("");
            }
        }

    }
}