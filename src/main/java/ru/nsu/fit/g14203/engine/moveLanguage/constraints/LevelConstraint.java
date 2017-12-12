package ru.nsu.fit.g14203.engine.moveLanguage.constraints;

import ru.nsu.fit.g14203.engine.api.utils.Dot3D;

import java.util.List;

public class LevelConstraint implements Constraint {

    private int[] goodLevels;

    public LevelConstraint() {
        goodLevels = new int[]{1,2,3};

    }
    public LevelConstraint(int... allowedLevels) {
        goodLevels = allowedLevels;
    }

    @Override
    public void changeValidMoves(Dot3D fromThis, List<Dot3D> toThis) {
        boolean allowed = false;
        for (int goodLevel : goodLevels) {
            if (fromThis.z == goodLevel) {
                allowed = true;
                break;
            }
        }
        if (!allowed)
            toThis.clear();
    }

    @Override
    public void changeValidCaptures(Dot3D fromThis, List<Dot3D> toThis) {
        changeValidMoves(fromThis, toThis);
    }
}
