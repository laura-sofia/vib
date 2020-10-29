package com.example.lovlyactivity;

import java.util.ArrayList;

public class DecisionClass {

    int lastI = 0;
    int lastJ = 1;
    private ArrayList<int[]> lastPlacesToGo;
    private boolean turn = false;
    private int[][] board;

    public DecisionClass() {
        init();
    }

    public int[][] getBoard() {
        return board;
    }

    public boolean getTurn() {
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

            if (board[i][j] != Checker.NOCHECKER.pos()) {

                decision(i, j);
            }
        }
    }


    public int decision(int i, int j) {

        int clicked = Math.abs(board[i][j]);

        if (clicked == Checker.PLACE_TO_GO.pos()) move(i, j);
        else if (clicked == Checker.CLICKED.pos()) {
            board[i][j] /= Checker.CLICKED.pos();
            clearLastPlacesToGo();
            lastI = 0;
            lastJ = 1;
        } else {
            notMove(i, j);
        }
        return 1;
    }

    public void move(int i, int j) {

        int checker = board[i][j];
        clearLastPlacesToGo();
        board[i][j] = checker / Checker.PLACE_TO_GO.pos();

        board[lastI][lastJ] = Checker.NOCHECKER.pos();
        turn = !turn;
    }

    public int notMove(int i, int j) {

        int clicked = board[i][j];
        if (clicked == Checker.WHITE.pos() && Turn.BLACK.turn() == turn) return 1;
        if (clicked == Checker.BLACK.pos() && Turn.WHITE.turn() == turn) return 1;

        if (board[lastI][lastJ] == Checker.WHITE_CLICKED.pos() || board[lastI][lastJ] == Checker.BLACK_CLICKED.pos()) {
            board[lastI][lastJ] /= Checker.CLICKED.pos();
        }
        lastI = i;
        lastJ = j;

        clearLastPlacesToGo();

        board[i][j] *= Checker.CLICKED.pos();
        if (i == 7 && clicked == Checker.BLACK.pos()) return 1;
        if (i == 0 && clicked == Checker.WHITE.pos()) return 1;
        position(i, j, clicked);

        return 1;
    }

    public void position(int i, int j, int clicked) {
        // int b=a*-1;
        if (j - 1 != -1) {

            if (board[i + clicked][j - 1] == Checker.NOCHECKER.pos()) {
                board[i + clicked][j - 1] = Checker.PLACE_TO_GO.pos() * clicked;
                lastPlacesToGo.add(new int[]{i + clicked, j - 1});
            }
           /* else if(j-2>-1 && i+2*a<8){
                if (board[i+a][j-1]==b && board[i+2*a][j-2]==0){

                }
            }*/
        }

        if (j + 1 != 8) {
            if (board[i + clicked][j + 1] == 0) {
                board[i + clicked][j + 1] = Checker.PLACE_TO_GO.pos() * clicked;
                lastPlacesToGo.add(new int[]{i + clicked, j + 1});
            }
        }
    }

    public void clearLastPlacesToGo() {

        for (int[] pos : lastPlacesToGo) {
            board[pos[0]][pos[1]] = 0;
        }
        lastPlacesToGo.clear();
    }

    public void init() {
        board = new int[8][8];
        for (int i = 0; i < 3; i++) {
            for (int j = (i + 1) % 2; j < 8; j += 2) {
                board[i][j] = Checker.BLACK.pos();
            }
        }

        for (int j = 0; j < 8; j += 2) board[5][j] = Checker.WHITE.pos();
        for (int j = 1; j < 8; j += 2) board[6][j] = Checker.WHITE.pos();
        for (int j = 0; j < 8; j += 2) board[7][j] = Checker.WHITE.pos();
        lastPlacesToGo = new ArrayList<>();
    }

    enum Checker {
        NOCHECKER(0),
        BLACK(1),
        WHITE(-1),
        BLACK_CLICKED(2),
        CLICKED(2),
        WHITE_CLICKED(-2),
        PLACE_TO_GO(3);

        public final int label;

        Checker(int label) {
            this.label = label;
        }

        public int pos() {
            return this.label;
        }
    }


    enum Turn {
        WHITE(false),
        BLACK(true);

        public final boolean label;

        Turn(boolean label) {
            this.label = label;
        }

        public boolean turn() {
            return this.label;
        }

    }

}
