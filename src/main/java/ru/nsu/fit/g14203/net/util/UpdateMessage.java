package ru.nsu.fit.g14203.net.util;

import com.fasterxml.jackson.annotation.JsonProperty;
import ru.nsu.fit.g14203.engine.api.Piece;
import ru.nsu.fit.g14203.engine.api.utils.Color;
import ru.nsu.fit.g14203.engine.api.utils.Dot3D;
import ru.nsu.fit.g14203.engine.api.utils.UpdateEntry;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UpdateMessage extends Message {

    private final UpdateEntry updateEntry;

    public UpdateMessage(@JsonProperty(value = "content") Map<String, Object> content) {
        super(TYPE_UPDATE);

        updateEntry = new UpdateEntry();
        updateEntry.fullPath = (List<Dot3D>) content.get("path");

        final String className = (String) content.get("piece");
        final String colorName = (String) content.get("color");

        final Color color = Color.valueOf(colorName);
        try {
            final Class<? extends Piece> aClass =
                    (Class<? extends Piece>) Class.forName(className);
            final Constructor<? extends Piece> constructor = aClass.getConstructor(Color.class);
            updateEntry.pieceToPlace = constructor.newInstance(color);
        } catch (ClassNotFoundException | NoSuchMethodException |
                IllegalAccessException | InstantiationException |
                InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    public UpdateMessage(UpdateEntry updateEntry) {
        super(TYPE_UPDATE);

        this.updateEntry = updateEntry;
    }

    @Override
    public Object getContent() {
        final Map<String, Object> content = new HashMap<>();
        content.put("path", updateEntry.fullPath);
        content.put("piece", updateEntry.pieceToPlace.getClass().getName());
        content.put("color", updateEntry.pieceToPlace.getColor().name());

        return content;
    }
}
