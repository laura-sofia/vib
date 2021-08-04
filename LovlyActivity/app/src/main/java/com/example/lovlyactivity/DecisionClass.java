package com.example.lovlyactivity;

import com.example.lovlyactivity.enums.Checker;
import com.example.lovlyactivity.enums.Turn;
import com.example.lovlyactivity.interfaces.OnCheckersToEat;
import com.example.lovlyactivity.interfaces.OnPlacesToGo;
import com.example.lovlyactivity.structure.Coordinate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DecisionClass implements OnPlacesToGo, OnCheckersToEat {

    int lastI = 0;
    int lastJ = 1;
    public Checker won = Checker.NOCHECKER;
    private int nBlack = 12;
    private ArrayList<Coordinate> lastPlacesToGo;
    private int nWhite = 12;
    private Map<Coordinate, Coordinate> eat;
    private Checker[][] board;
    private DamaPosition damaPosition;
    private NormalChPosition normalPosition;
    ///         place ,,,, oponent
    private Turn turn = Turn.WHITE;

    public DecisionClass() {
        init();
    }

    public Checker[][] getBoard() {
        return board;
    }

    public Turn getTurn() {
        return turn;
    }

    public void update(float x, float y, int height, int width) {

        float posX = x - 20;
        float posY = y - (float) (height - width + 20) / 2;

        float square = width - 40;
        float smallSquare = square / 8;

        if (posX > 0 && posX < square && posY > 0 && posY < square) {

            int i = (int) ((int) posY / smallSquare);
            int j = (int) ((int) posX / smallSquare);

            if (board[i][j] != Checker.NOCHECKER) {

                decision(i, j);
            }
        }
    }


    private boolean obligation = false;
    private boolean hasToEat = false;
    private ArrayList<Coordinate> listOfCheckersWillEat;

    public boolean canMove() {
        ///make list or array[][] of checkers that will eat
        /// check if can move
        listOfCheckersWillEat = new ArrayList<>();
        boolean canMove = false;

        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++) {
                if (Checker.color(board[i][j]) == turn.label) {
                    boolean[] temp = hasSomethingToEat(i, j, false);
                    if (temp[0]) {
                        hasToEat = true;
                        listOfCheckersWillEat.add(new Coordinate(i, j));
                    }
                    if (temp[1]) canMove = true;
                }
            }
        ///////////////////////////////////////////////////////////////////////make list of checker that have to eat
        return canMove;

    }

    public boolean checkEat() {
        hasToEat = false;

        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++) {
                if (Checker.color(board[i][j]) == turn.label) {
                    if (hasSomethingToEat(i, j, true)[0]) {
                        hasToEat = true;
                        return true;
                    }
                }
            }
        ///////////////////////////////////////////////////////////////////////make list of checker that have to eat
        return false;
    }
    public int decision(int i, int j) {

        Checker clicked = board[i][j];
        /// & !obligation
        if (Checker.isPlaceToGo(clicked))
            move(i, j);
        else if (Checker.wasClicked(clicked)) {
            board[i][j] = Checker.undoClicked(clicked);
            clearLastPlacesToGo();
            clearNotEatenCheckers();
            lastI = 0;
            lastJ = 1;
        } else if (Checker.color(clicked) != turn.label) {
            return 1;
        } else {
            if (!hasToEat || hasSomethingToEat(i, j, true)[0]) {
                notMove(i, j);
            }
        }
        return 1;
    }

    public void moveCheckerToPlace(int i, int j, Checker checker) {
        if (Checker.isDamaPlaceToGo(checker)) {
            board[i][j] = Checker.makeDama(checker);
        } else board[i][j] = Checker.moveCheckerToPlace(checker);
    }
    public void invertPlayer() {

        turn = Turn.invert(turn);
        // checjhkhkEat();
        //canhfhgfMove();
    }

    public void move(int i, int j) {

        Checker checker = board[i][j];
        obligation = false;
        Coordinate c = new Coordinate(i, j);
        Coordinate toEat = contains(c);
        if (toEat != null) {
            if (turn == Turn.WHITE) nBlack--;
            else nWhite--;

            board[toEat.i][toEat.j] = Checker.NOCHECKER;
            if (hasSomethingToEat(i, j, true)[0]) {
                obligation = true;
            }
        }
        board[lastI][lastJ] = Checker.NOCHECKER;
        clearNotEatenCheckers();
        clearLastPlacesToGo();
        moveCheckerToPlace(i, j, checker);
        if (!obligation) invertPlayer();
        else {
            notMove(i, j);
        }

        checkWin();
        //// Winning class
        ////
    }


    public boolean[] hasSomethingToEat(int i, int j, boolean onlyEat) {
        Checker checker = board[i][j];
        if (Checker.isDama(checker)) return damaPosition.hasSomethingToEat(i, j, board, onlyEat);
        return normalPosition.hasSomethingToEat(i, j, board, onlyEat);
    }

    public Coordinate contains(Coordinate c) {
        for (Map.Entry<Coordinate, Coordinate> entry : eat.entrySet()) {
            if (c.equals(entry.getKey())) return entry.getValue();
        }
        return null;
    }
    public void checkWin() {
        if (nWhite == 0) won = Checker.WHITE;
        if (nBlack == 0) won = Checker.BLACK;
        if (!canMove()) {
            won = Turn.invert(turn).label;
        }
    }

    public void notMove(int i, int j) {

        Checker clicked = board[i][j];
        if (Checker.color(clicked) == Checker.WHITE && Turn.BLACK == turn) return;
        if (Checker.color(clicked) == Checker.BLACK && Turn.WHITE == turn) return;

        if (Checker.wasClicked(board[lastI][lastJ])) {
            board[lastI][lastJ] = Checker.undoClicked(board[lastI][lastJ]);
        }
        lastI = i;
        lastJ = j;

        clearLastPlacesToGo();
        clearNotEatenCheckers();
        board[i][j] = Checker.makeClicked(board[i][j]);
        whereToGo(i, j, clicked);
    }

    public void whereToGo(int i, int j, Checker checker) {
        if (Checker.isDama(checker)) board = damaPosition.position(i, j, board, hasToEat);
        else board = normalPosition.position(i, j, checker, board, hasToEat);
    }

    public void clearLastPlacesToGo() {

        for (Coordinate c : lastPlacesToGo) {
            board[c.i][c.j] = Checker.NOCHECKER;
        }
        lastPlacesToGo.clear();
    }

    public void clearNotEatenCheckers() {
        for (Map.Entry<Coordinate, Coordinate> entry : eat.entrySet()) {
            board[entry.getValue().i][entry.getValue().j] = Checker.undoWillBeEaten(board[entry.getValue().i][entry.getValue().j]);
        }
        eat.clear();
    }

    public void init() {

        damaPosition = new DamaPosition();
        damaPosition.setOnPlacesToGo(this);
        damaPosition.setOnCheckersToEat(this);
        ////////////
        normalPosition = new NormalChPosition();
        normalPosition.setOnPlacesToGo(this);
        normalPosition.setEat(this);
        ////////////
        int dir = 1;
        board = new Checker[8][8];
        for (int i = 0; i < 3; i++) {
            for (int j = (i + 1) % 2; j < 8; j += 2) {
                board[i][j] = Checker.BLACK;
            }
        }
        int beg = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = beg; j < 8; j += 2) {
                board[i][j] = Checker.NOCHECKER;
            }
            beg += dir;
            dir = dir * -1;
        }
        for (int i = 3; i < 6; i++) for (int j = 0; j < 8; j++) board[i][j] = Checker.NOCHECKER;

        for (int j = 0; j < 8; j += 2) board[5][j] = Checker.WHITE;
        for (int j = 1; j < 8; j += 2) board[6][j] = Checker.WHITE;
        for (int j = 0; j < 8; j += 2) board[7][j] = Checker.WHITE;
        lastPlacesToGo = new ArrayList<>();
        eat = new HashMap<>();


    }

    public void onPlacesToGo(ArrayList<Coordinate> lastPlacesToGo) {

        this.lastPlacesToGo = lastPlacesToGo;

    }

    @Override
    public void OnCheckersToEat(Map<Coordinate, Coordinate> eat) {
        this.eat = eat;
    }
}
