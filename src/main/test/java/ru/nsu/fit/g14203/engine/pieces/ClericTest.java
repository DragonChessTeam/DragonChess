package ru.nsu.fit.g14203.engine.pieces;

import junit.framework.TestCase;
import ru.nsu.fit.g14203.engine.utils.Color;
import ru.nsu.fit.g14203.engine.utils.Dot3D;
import ru.nsu.fit.g14203.engine.utils.Playground;
import ru.nsu.fit.g14203.engine.utils.Way;

public class ClericTest extends TestCase {
    Cleric c = new Cleric(Color.WHITE);
    Playground board = new Playground();

    public void testIsValidMove() throws Exception {
        Dot3D pos = new Dot3D(5,5,1);
        board.ground[5][5][1] = c;
        for (int offsetX = -1; offsetX <=1; offsetX++) {
            for (int offsetY = -1; offsetY <= 1; offsetY++) {
                if (offsetX == 0 && offsetY == 0) {
                    assertFalse(c.isValidMove(new Way(pos, pos.sum(new Dot3D(offsetX, offsetY, 0))), board));
                    continue;
                }
                assertTrue(c.isValidMove(new Way(pos, pos.sum(new Dot3D(offsetX, offsetY, 0))), board));
            }
        }

        for (int offsetZ = -1; offsetZ <=1; offsetZ++) {
            if (offsetZ == 0 ) {
                assertFalse(c.isValidMove(new Way(pos, pos.sum(new Dot3D(0, 0, offsetZ))), board));
                continue;
            }
            assertTrue(c.isValidMove(new Way(pos, pos.sum(new Dot3D(0, 0, offsetZ))), board));
        }
    }

    public void testGetAvailableMoves() throws Exception {
    }

}