package ru.nsu.fit.g14203.engine;

import ru.nsu.fit.g14203.engine.api.Observer;
import ru.nsu.fit.g14203.engine.api.utils.Dot3D;
import ru.nsu.fit.g14203.engine.api.utils.UpdateEntry;

import java.util.List;

public class ObserverMock implements Observer {
    private UpdateEntry ue;

    @Override
    public void update(List<UpdateEntry> boards) {
        for (UpdateEntry ue : boards) {
            System.out.println("Move: ");
            System.out.println("    piece: "+ue.pieceToPlace);
            if (ue.pieceToPlace != null)
            System.out.println("    piece color: "+ue.pieceToPlace.getColor());
            System.out.print("    ");
            for (Dot3D dot : ue.fullPath)
                System.out.print(dot.toString()+" ");
            System.out.println("");
        }
    }
}
