package ru.nsu.fit.g14203.engine.pieces;

import ru.nsu.fit.g14203.engine.api.utils.Color;
import ru.nsu.fit.g14203.engine.moveLanguage.moves.RoundMove;

public class King extends BasicPiece {

    public King(Color c) {
        color = c;

        move = new RoundMove();
        capture = move;
    }

}
