package ru.nsu.fit.g14203.view;

import ru.nsu.fit.g14203.view.desks.ChessDesk;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Queue;

public class DeskSwiper {

    private LinkedList<ChessDesk> deskList;
    private int currentIndex;

    public DeskSwiper(ChessDesk ... desks){
        deskList = new LinkedList<>();

        for(int i = 0; i < desks.length; i++)
            deskList.add(desks[i]);

        currentIndex = 0;
    }

    public void addDesk(ChessDesk desk){
        deskList.add(desk);
    }

    public ChessDesk getDesk(){
        if(deskList.size() <= currentIndex)
            throw new IndexOutOfBoundsException();
        else return deskList.get(currentIndex);
    }

    public ChessDesk getNextDesk(){
        if(deskList.size() - 1 == currentIndex)
            return deskList.get(currentIndex);
        else
            return deskList.get(++currentIndex);
    }

    public ChessDesk getPreviousDesk(){
        if(currentIndex == 0)
            return deskList.get(currentIndex);
        else
            return deskList.get(--currentIndex);
    }

}
