package ru.nsu.fit.g14203.engine.Initializer;

import ru.nsu.fit.g14203.engine.api.Piece;
import ru.nsu.fit.g14203.engine.pieces.King;
import ru.nsu.fit.g14203.engine.pieces.TestPiece;

import static ru.nsu.fit.g14203.engine.api.utils.Color.BLACK;
import static ru.nsu.fit.g14203.engine.api.utils.Color.WHITE;

public class TestEngineInitializer implements EngineInitializer {
    @Override
    public void init(Piece[][][] boards) {

        for (int x = 0; x < boards.length; x++)
            for (int y = 0; y < boards[0].length; y++)
                for (int z = 0; z < boards[0][0].length; z++) {
                    if (x < 4) boards[x][y][z] = new TestPiece(WHITE);
                    if (x >= 6) boards[x][y][z] = new TestPiece(BLACK);
                }

        boards[0][0][1] = new King(WHITE);
        boards[6][0][1] = new King(BLACK);
    }
}
