package ru.nsu.fit.g14203.engine.constraints.triggers;

import ru.nsu.fit.g14203.engine.api.Piece;
import ru.nsu.fit.g14203.engine.api.utils.UpdateEntry;

import java.util.List;

public interface Trigger {
    void apply(Piece[][][] boards, List<UpdateEntry> updates);
}
