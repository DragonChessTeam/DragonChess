package ru.nsu.fit.g14203.view;

import ru.nsu.fit.g14203.view.desks.ChessDesk;

import java.util.LinkedList;
import java.util.Queue;

public class DeskSwiper {

    private Queue<ChessDesk> deskList;

    public DeskSwiper(ChessDesk ... desks){
        deskList = new LinkedList<>();

        for(int i = 0; i < desks.length; i++)
            deskList.add(desks[i]);
    }

    public void addDesk(ChessDesk desk){
        deskList.add(desk);
    }
}
