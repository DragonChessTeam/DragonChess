package ru.nsu.fit.g14203.engine.moveLanguage.constraints;

import ru.nsu.fit.g14203.engine.api.utils.Dot3D;

import java.util.List;

public interface Constraint {

    void changeValidMoves(Dot3D fromThis, List<Dot3D> toThis);

    void changeValidCaptures(Dot3D fromThis, List<Dot3D> toThis);

}
