package ru.nsu.fit.g14203.engine.constraints;

import ru.nsu.fit.g14203.engine.api.utils.Dot3D;
import ru.nsu.fit.g14203.engine.constraints.Constraint;

import java.util.List;

public class StartLevelConstraint implements Constraint {
    private int[] workLevels;

    public StartLevelConstraint(int... levels) {
        workLevels = levels;
    }

    @Override
    public void changeMove(List<Dot3D> ends, Dot3D start) {
        for (int level : workLevels) {
            if (start.z == level) {
                return;
            }
        }
        ends.clear();
    }

    @Override
    public void changeCapture(List<Dot3D> ends, Dot3D start) {
        changeMove(ends,start);
    }
}
