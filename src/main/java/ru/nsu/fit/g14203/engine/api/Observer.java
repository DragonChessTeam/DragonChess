package ru.nsu.fit.g14203.engine.api;

import ru.nsu.fit.g14203.engine.api.utils.UpdateEntry;

import java.util.List;

public interface Observer {

    void update(List<UpdateEntry> boards);

}
