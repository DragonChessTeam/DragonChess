package ru.nsu.fit.g14203.engine.constraints.triggers;

import ru.nsu.fit.g14203.engine.api.Piece;
import ru.nsu.fit.g14203.engine.api.utils.Dot3D;
import ru.nsu.fit.g14203.engine.api.utils.UpdateEntry;
import ru.nsu.fit.g14203.engine.pieces.Dragon;
import ru.nsu.fit.g14203.engine.utils.PieceFinder;

import java.util.List;

import static ru.nsu.fit.g14203.engine.api.utils.Color.BLACK;
import static ru.nsu.fit.g14203.engine.api.utils.Color.WHITE;

public class DragonTrigger implements Trigger{
    private Dot3D whitePos;
    private Dot3D blackPos;

    @Override
    public void apply(Piece[][][] boards, List<UpdateEntry> updates) {
        Dot3D newWhitePos = PieceFinder.findPiece(boards, Dragon.class, WHITE).get(0);
        Dot3D newBlackPos = PieceFinder.findPiece(boards, Dragon.class, BLACK).get(0);
        if (newWhitePos.z == 1) {
            boards[whitePos.x][whitePos.y][whitePos.z] = boards[newWhitePos.x][newWhitePos.y][newWhitePos.z];
            boards[newWhitePos.x][newWhitePos.y][newWhitePos.z] = null;
            updates.add(new UpdateEntry(null, newWhitePos, whitePos));
        } else {
            whitePos = newWhitePos;
        }
        if (newBlackPos.z == 1) {
            boards[blackPos.x][blackPos.y][blackPos.z] = boards[newBlackPos.x][newBlackPos.y][newBlackPos.z];
            boards[newBlackPos.x][newBlackPos.y][newBlackPos.z] = null;
            updates.add(new UpdateEntry(null, newBlackPos, blackPos));
        }else {
            blackPos = newBlackPos;
        }
    }
}
