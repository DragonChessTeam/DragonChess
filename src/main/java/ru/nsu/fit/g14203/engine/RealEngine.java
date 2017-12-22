package ru.nsu.fit.g14203.engine;

import ru.nsu.fit.g14203.engine.Initializer.EngineInitializer;
import ru.nsu.fit.g14203.engine.api.Engine;
import ru.nsu.fit.g14203.engine.api.Observer;
import ru.nsu.fit.g14203.engine.api.Piece;
import ru.nsu.fit.g14203.engine.api.utils.*;
import ru.nsu.fit.g14203.engine.constraints.triggers.Trigger;
import ru.nsu.fit.g14203.engine.utils.ChessChecker;

import java.util.ArrayList;
import java.util.List;

import static ru.nsu.fit.g14203.engine.GameState.*;
import static ru.nsu.fit.g14203.engine.api.utils.Color.BLACK;
import static ru.nsu.fit.g14203.engine.api.utils.Color.WHITE;
import static ru.nsu.fit.g14203.engine.api.utils.EngineResponse.*;

public class RealEngine implements Engine {

    private Piece[][][] boards = new Piece[12][8][3]; //X,Y,Z

    private GameState state = WHITE_TURN;

    private List<Observer> observers = new ArrayList<>();
    private List<Trigger> triggers = new ArrayList<>();

    public RealEngine(EngineInitializer init) {
        init.init(boards);
//        triggers.add(new BasiliskTrigger());
    }

    private EngineResponse shiftPiece(Dot3D start, Dot3D end, boolean isCapture, Piece[][][] boards) {
        Piece piece = this.boards[start.x][start.y][start.z];
        List<Dot3D> endPoints = new ArrayList<>();
        if (piece != null)
            if (isCapture) {
                endPoints = piece.getAvailableCaptures(start, boards);
            } else {
                endPoints = piece.getAvailableMoves(start, boards);
            }


        for (Dot3D endD : endPoints) {
            if (end.equals(endD)) {
                boards[end.x][end.y][end.z] = boards[start.x][start.y][start.z];
                boards[start.x][start.y][start.z] = null;

                return OK;
            }
        }

        return WRONG_WAY;

    }

    private EngineResponse doTurn(Color color, Dot3D start, Dot3D end, boolean isCapture) {
        if (state == GAME_OVER) return CHECK_MATE;
        if (!((color == WHITE && state == WHITE_TURN) || (color == BLACK && state == BLACK_TURN))) return OTHER_PLAYER_TURN;

        EngineResponse moveResult = shiftPiece(start, end, isCapture, boards);
        EngineResponse out = OK;
        if (moveResult == WRONG_WAY) {
            return WRONG_WAY;
        }

        Color oppositColor;
        if (color == WHITE) {
            oppositColor = BLACK;
        } else {
            oppositColor = WHITE;
        }
        if (ChessChecker.isCheckFor(oppositColor, boards)) {
            if (ChessChecker.isCheckMateFor(oppositColor, boards)) {
                state = GAME_OVER;
                out = CHECK_MATE;
            } else {
                out = CHECK;
            }
        }

        switch (state) {
            case WHITE_TURN:
                state = BLACK_TURN;
                break;
            case BLACK_TURN:
                state = WHITE_TURN;
                break;
        }

        for (Trigger t : triggers) {
            t.apply(boards);
        }

        List<UpdateEntry> updates = new ArrayList<>();
        updates.add(new UpdateEntry(null, start, end));
        notifyObservers(updates);
        return out;
    }


    @Override
    public EngineResponse doCapture(Color color, Way turn) {
        return doTurn(color, turn.start, turn.end, true);
    }

    @Override
    public EngineResponse doMove(Color color, Way turn) {
        return doTurn(color, turn.start, turn.end, false);
    }

    private void notifyObservers(List<UpdateEntry> updates) {
        for (Observer o : observers)
            o.update(updates);
    }

    @Override
    public void registerObserver(Observer watcher) {
        observers.add(watcher);
        List<UpdateEntry> updates = new ArrayList<>();
        for (int i = 0; i < boards.length; i++)
            for (int j = 0; j < boards[0].length; j++)
                for (int k = 0; k < boards[0][0].length; k++) {
                    if (boards[i][j][k] != null)
                        updates.add(new UpdateEntry(boards[i][j][k], new Dot3D(i,j,k)));
                }
        watcher.update(updates);
    }

    @Override
    public void deleteObserver(Observer watcher) {
        observers.remove(watcher);
    }
}
