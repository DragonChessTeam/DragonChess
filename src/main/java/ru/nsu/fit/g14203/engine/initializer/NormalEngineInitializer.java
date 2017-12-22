package ru.nsu.fit.g14203.engine.initializer;

import ru.nsu.fit.g14203.engine.api.Piece;
import ru.nsu.fit.g14203.engine.pieces.*;

import static ru.nsu.fit.g14203.engine.api.utils.Color.BLACK;
import static ru.nsu.fit.g14203.engine.api.utils.Color.WHITE;

public class NormalEngineInitializer implements EngineInitializer {
    @Override
    public void init(Piece[][][] boards) {
        //upper board
        boards[2][0][2] = new Griffon(BLACK);
        boards[6][0][2] = new Dragon(BLACK);
        boards[10][0][2] = new Griffon(BLACK);
        for (int i = 0; i < 12; i += 2) {
            boards[i][1][2] = new Sylph(BLACK);
        }

        boards[2][7][2] = new Griffon(WHITE);
        boards[6][7][2] = new Dragon(WHITE);
        boards[10][7][2] = new Griffon(WHITE);
        for (int i = 0; i < 12; i += 2) {
            boards[i][6][2] = new Sylph(WHITE);
        }

        //Middle board

        boards[0][0][1] = new Oliphant(BLACK);
        boards[11][0][1] = new Oliphant(BLACK);
        boards[1][0][1] = new Unicorn(BLACK);
        boards[10][0][1] = new Unicorn(BLACK);
        boards[2][0][1] = new Hero(BLACK);
        boards[9][0][1] = new Hero(BLACK);
        boards[3][0][1] = new Thief(BLACK);
        boards[8][0][1] = new Thief(BLACK);
        boards[4][0][1] = new Cleric(BLACK);
        boards[7][0][1] = new Paladin(BLACK);
        boards[5][0][1] = new Mage(BLACK);
        boards[6][0][1] = new King(BLACK);
        for (int i = 0; i < 12; i ++) {
            boards[i][1][1] = new Warrior(BLACK);
        }

        boards[0][7][1] = new Oliphant(WHITE);
        boards[11][7][1] = new Oliphant(WHITE);
        boards[1][7][1] = new Unicorn(WHITE);
        boards[10][7][1] = new Unicorn(WHITE);
        boards[2][7][1] = new Hero(WHITE);
        boards[9][7][1] = new Hero(WHITE);
        boards[3][7][1] = new Thief(WHITE);
        boards[8][7][1] = new Thief(WHITE);
        boards[4][7][1] = new Cleric(WHITE);
        boards[7][7][1] = new Paladin(WHITE);
        boards[5][7][1] = new Mage(WHITE);
        boards[6][7][1] = new King(WHITE);
        for (int i = 0; i < 12; i ++) {
            boards[i][6][1] = new Warrior(WHITE);
        }

        //Bottom board
        boards[2][0][0] = new Basilisk(BLACK);
        boards[6][0][0] = new Elemental(BLACK);
        boards[10][0][0] = new Basilisk(BLACK);
        for (int i = 1; i < 12; i += 2) {
            boards[i][1][0] = new Dwarf(BLACK);
        }

        boards[2][7][0] = new Basilisk(WHITE);
        boards[6][7][0] = new Elemental(WHITE);
        boards[10][7][0] = new Basilisk(WHITE);
        for (int i = 1; i < 12; i += 2) {
            boards[i][6][0] = new Dwarf(WHITE);
        }
    }
}
