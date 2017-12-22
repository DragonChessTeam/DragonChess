package ru.nsu.fit.g14203.engine.constraints.triggers;

import ru.nsu.fit.g14203.engine.api.Piece;

public interface Trigger {
    void apply(Piece[][][] boards);
}
