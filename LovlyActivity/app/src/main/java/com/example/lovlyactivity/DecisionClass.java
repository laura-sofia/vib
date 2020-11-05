package com.example.lovlyactivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DecisionClass {
    /// make bith obazatelhno
    //// viigrat

    int lastI = 0;
    int lastJ = 1;
    private ArrayList<int[]> lastPlacesToGo;
    private boolean turn = false;
    //  private int[][] board;
    private Map<Integer, int[]> eat;
    private Checker[][] board;
    ///         place ,,,, oponent

    public DecisionClass() {
        init();
    }

    public Checker[][] getBoard() {
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

            if (board[i][j] != Checker.NOCHECKER) {

                decision(i, j);
            }
        }
    }


    public int decision(int i, int j) {

        Checker clicked = board[i][j];

        if (clicked == Checker.WHITE_PLACE_TO_GO || clicked == Checker.BLACK_PLACE_TO_GO)
            move(i, j);
        else if (clicked == Checker.WHITE_CLICKED || clicked == Checker.BLACK_CLICKED) {
            board[i][j] = Checker.undoClicked(board[i][j]);
            // board[i][j] /= Checker.CLICKED.pos();
            ////
            clearLastPlacesToGo();
            clearNotEatenCheckers();
            lastI = 0;
            lastJ = 1;
        } else {
            notMove(i, j);
        }
        return 1;
    }

    public void move(int i, int j) {

        Checker checker = board[i][j];
        if (eat.containsKey(i * 10 + j)) {
            board[eat.get(i * 10 + j)[0]][eat.get(i * 10 + j)[1]] = Checker.NOCHECKER;
            eat.remove(i * 10 + j);
            clearNotEatenCheckers();
        }
        clearLastPlacesToGo();
        board[i][j] = Checker.moveCheckerToPlace(checker);
        //  board[i][j] = checker / Checker.PLACE_TO_GO.pos();

        board[lastI][lastJ] = Checker.NOCHECKER;
        turn = !turn;
        //// Winning class
        ////


    }

    public int notMove(int i, int j) {

        Checker clicked = board[i][j];
        if (clicked == Checker.WHITE && Turn.BLACK.turn() == turn) return 1;
        if (clicked == Checker.BLACK && Turn.WHITE.turn() == turn) return 1;

        if (board[lastI][lastJ] == Checker.WHITE_CLICKED || board[lastI][lastJ] == Checker.BLACK_CLICKED) {
            board[lastI][lastJ] = Checker.undoClicked(board[lastI][lastJ]);
            //  board[lastI][lastJ] /= Checker.CLICKED.pos();
        }
        lastI = i;
        lastJ = j;

        clearLastPlacesToGo();
        clearNotEatenCheckers();
        board[i][j] = Checker.makeClicked(board[i][j]);
        //   board[i][j] *= Checker.CLICKED.pos();
        if (i == 7 && clicked == Checker.BLACK) return 1;
        if (i == 0 && clicked == Checker.WHITE) return 1;
        position(i, j, clicked);

        return 1;
    }

    public void position(int i, int j, Checker checker) {

        int direction = checker.label;
        int oponent = direction * -1;
        // go right
        if (j - 1 != -1) {
            if (board[i + direction][j - 1] == Checker.NOCHECKER) {
                board[i + direction][j - 1] = Checker.makePlaceToGo(checker);
                //   board[i + direction][j - 1] = Checker.PLACE_TO_GO.pos() * direction;
                lastPlacesToGo.add(new int[]{i + direction, j - 1});
            }
        }
        //go left
        if (j + 1 != 8) {
            if (board[i + direction][j + 1] == Checker.NOCHECKER) {
                board[i + direction][j + 1] = Checker.makePlaceToGo(checker);
                // board[i + direction][j + 1] = Checker.PLACE_TO_GO.pos() * direction;
                lastPlacesToGo.add(new int[]{i + direction, j + 1});
            }
        }
        int[] ii = new int[]{2, 2, -2, -2};
        int[] yy = new int[]{-2, 2, -2, 2};
        int[] ieat = new int[]{1, 1, -1, -1};
        int[] yeat = new int[]{-1, 1, -1, 1};
        for (int index = 0; index < 4; index++) {
            int futI = i + ii[index];
            int futJ = j + yy[index];
            if (futI > -1 && futI < 8 && futJ < 8 && futJ > -1) {
                if (board[i + ieat[index]][j + yeat[index]] == Checker.invertColor(checker) && board[futI][futJ] == Checker.NOCHECKER) {
                    board[futI][futJ] = Checker.makePlaceToGo(checker);
                    // board[futI][futJ] = Checker.PLACE_TO_GO.pos() * direction;
                    board[i + ieat[index]][j + yeat[index]] = Checker.willBeEaten(Checker.invertColor(checker));
                    //  board[i + ieat[index]][j + yeat[index]] = oponent * Checker.WILL_BE_EATEN.pos();
                    eat.put((futI) * 10 + futJ, new int[]{i + ieat[index], j + yeat[index]});
                    lastPlacesToGo.add(new int[]{futI, futJ});

                }
            }
        }
    }

    public void clearLastPlacesToGo() {

        for (int[] pos : lastPlacesToGo) {
            board[pos[0]][pos[1]] = Checker.NOCHECKER;
        }
        lastPlacesToGo.clear();
    }

    public void clearNotEatenCheckers() {
        for (Map.Entry<Integer, int[]> entry : eat.entrySet()) {

            board[entry.getValue()[0]][entry.getValue()[1]] = Checker.undoWillBeEaten(board[entry.getValue()[0]][entry.getValue()[1]]);
            //board[entry.getValue()[0]][entry.getValue()[1]] /= Checker.WILL_BE_EATEN.pos();

        }
        eat.clear();
    }

    public void init() {
        //board = new int[8][8];
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
