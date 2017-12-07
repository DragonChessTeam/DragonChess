package ru.nsu.fit.g14203.engine;

import java.util.List;

public interface Observer {

    public void update(List<UpdateEntry> boards);

}
