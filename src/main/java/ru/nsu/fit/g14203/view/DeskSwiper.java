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
import java.util.List;

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
            }else if(chosenDot != null){ //клик не по нашей фигуре
                newChosenDot = new Dot3D(modelPosition.width, modelPosition.height, currentIndex);
                engine.doCapture(myColor, new Way(chosenDot, newChosenDot));
            }
        }else if(chosenDot != null){ //клик по пустому полю
            newChosenDot = new Dot3D(modelPosition.width, modelPosition.height, currentIndex);
            engine.doMove(myColor, new Way(chosenDot, newChosenDot));
        }
    }

    public void handleMove(int x, int y){
        Dot3D newChosenDot;
        Dimension modelPosition;

        if(chosenDot == null) return;

        modelPosition = getDesk().getBoardModelPosition(x, y);
        if(modelPosition == null) return;

        newChosenDot = new Dot3D(modelPosition.width, modelPosition.height, currentIndex);

        Piece[][][] playground = craft3DimensionArray();

        Piece pieceToCheck = deskList.get(chosenDot.z).getPiece(new Dimension(chosenDot.x,chosenDot.y));


        List<Dot3D> moves = pieceToCheck.getAvailableMoves(chosenDot, playground);
        moves.addAll(pieceToCheck.getAvailableCaptures(chosenDot,playground));

        java.awt.Color releasedColor;

        if(moves.contains(newChosenDot)){
            releasedColor = java.awt.Color.GREEN;
        }else {
            releasedColor = java.awt.Color.RED;
        }

        getDesk().setReleasedField(modelPosition, releasedColor);



    }

    public void clearChosenFields(){
        deskList.forEach(ChessDesk::clearChosenField);
        chosenDot = null;
    }

    private Piece[][][] craft3DimensionArray(){
        Piece[][] firstDesk, secondDesk, thirdDesk;
        firstDesk = (getDesk(0).getBoardModel());
        secondDesk = (getDesk(1).getBoardModel());
        thirdDesk = (getDesk(2).getBoardModel());
        Piece[][][] arr = new Piece[12][8][3];

        for(int i = 0; i < 12; i++){
            for (int j = 0; j < 8; j++){
                arr[i][j][0] = firstDesk[j][i];
                arr[i][j][1] = secondDesk[j][i];
                arr[i][j][2] = thirdDesk[j][i];
            }
        }
        return arr;//new Piece[][][]{transpose(firstDesk), transpose(secondDesk), transpose(thirdDesk)};
    }

    private Piece[][] transpose(Piece[][] piece){

        int width = piece.length;
        int height = piece[0].length;

        Piece[][] array_new = new Piece[height][width];

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                array_new[y][x] = piece[x][y];
            }
        }
        return array_new;
    }
}
