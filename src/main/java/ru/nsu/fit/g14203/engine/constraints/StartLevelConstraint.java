package ru.nsu.fit.g14203.engine.constraints;

import ru.nsu.fit.g14203.engine.api.Piece;
import ru.nsu.fit.g14203.engine.api.utils.Dot3D;

import java.util.List;

public class StartLevelConstraint implements Constraint {
    private int[] workLevels;

    public StartLevelConstraint(int... levels) {
        workLevels = levels;
        for (int i = 0; i < workLevels.length; i++){
            workLevels[i]--;
        }
    }

    @Override
    public void changeMove(List<Dot3D> ends, Dot3D start, Piece[][][] boards) {
        for (int level : workLevels) {
            if (start.z == level) {
                return;
            }
        }
        ends.clear();
    }

}
