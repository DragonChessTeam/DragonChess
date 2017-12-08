package ru.nsu.fit.g14203.engine.triggers;

import ru.nsu.fit.g14203.engine.utils.Playground;
import ru.nsu.fit.g14203.engine.utils.Way;

public interface Trigger {

    public abstract void apply(Playground boards, Way turn);

}
