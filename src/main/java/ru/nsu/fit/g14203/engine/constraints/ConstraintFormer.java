package ru.nsu.fit.g14203.engine.constraints;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConstraintFormer {
    private ConstraintFormer(){}

    public static Constraint[] formMoveConstraint(Constraint... additionConstrs){
        List<Constraint> out = new ArrayList<>();
        out.add(new BoardBorderConstraint());
        out.addAll(Arrays.asList(additionConstrs));
        out.add(new BlockConstraint());
        Constraint[] stockArr = new Constraint[out.size()];
        out.toArray(stockArr);
        return stockArr;
    }

    public static Constraint[] formCaptureConstraint(Constraint... additionConstrs){
        List<Constraint> out = new ArrayList<>();
        out.add(new BoardBorderConstraint());
        out.addAll(Arrays.asList(additionConstrs));
        out.add(new BlockAllyAndVoidCaptureConstraint());
        Constraint[] stockArr = new Constraint[out.size()];
        out.toArray(stockArr);
        return stockArr;
    }
}
