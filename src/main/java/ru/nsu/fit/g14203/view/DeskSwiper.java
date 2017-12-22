package ru.nsu.fit.g14203.view;

import ru.nsu.fit.g14203.engine.api.Engine;
import ru.nsu.fit.g14203.engine.api.Piece;
import ru.nsu.fit.g14203.engine.api.utils.Color;
import ru.nsu.fit.g14203.engine.api.utils.Dot3D;
import ru.nsu.fit.g14203.engine.api.utils.Way;
import ru.nsu.fit.g14203.view.desks.ChessDesk;

import java.awt.*;
import java.util.Collections;
import java.util.LinkedList;

public class DeskSwiper {

    private LinkedList<ChessDesk> deskList;
    private int currentIndex;
    private Dot3D chosenDot;
    private static Color myColor = Color.WHITE; //TODO: set real color


    DeskSwiper(ChessDesk ... desks){
        deskList = new LinkedList<>();

        Collections.addAll(deskList, desks);

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

    public ChessDesk incrementNGetDesk(){
        if(deskList.size() - 1 == currentIndex)
            return deskList.get(currentIndex);
        else
            return deskList.get(++currentIndex);
    }

    public ChessDesk decrementNGetsDesk(){
        if(currentIndex == 0)
            return deskList.get(currentIndex);
        else
            return deskList.get(--currentIndex);
    }

    public void incrementIndex(){
        if(deskList.size() - 1 > currentIndex)
            currentIndex++;
    }

    public void decrementIndex(){
        if(currentIndex > 0)
            currentIndex--;
    }

    public ChessDesk getNextDesk(){
        if(deskList.size() > currentIndex + 1)
            return deskList.get(currentIndex + 1);
        else return null;
    }

    public ChessDesk getPreviousDesk(){
        if(currentIndex - 1 >= 0)
            return deskList.get(currentIndex - 1);
        else return null;
    }

    public ChessDesk getDesk(int index){
        return deskList.get(index);
    }

    public void handleClick(int x, int y, Engine engine){
        Dimension modelPosition;
        Piece chosenPiece;
        Dot3D newChosenDot;

        modelPosition = getDesk().getBoardModelPosition(x, y);
        if(modelPosition == null) return;

        chosenPiece = getDesk().getPiece(modelPosition);

        if(chosenPiece != null){
            if(chosenPiece.getColor() == myColor) { //выделение своей фигуры
                clearChosenFields();
                getDesk().setChosenField(modelPosition);
                chosenDot = new Dot3D(modelPosition.width, modelPosition.height, currentIndex);
            }else{ //клик не по нашей фигуре
                newChosenDot = new Dot3D(modelPosition.width, modelPosition.height, currentIndex);
                engine.doCapture(myColor, new Way(chosenDot, newChosenDot));
            }
        }else{ //клик по пустому полю
            newChosenDot = new Dot3D(modelPosition.width, modelPosition.height, currentIndex);
            engine.doMove(myColor, new Way(chosenDot, newChosenDot));
        }


    }

    public void clearChosenFields(){
        deskList.forEach(ChessDesk::clearChosenField);
        chosenDot = null;
    }
}
