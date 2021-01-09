package com.example.lovlyactivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DecisionClass implements OnPlacesToGo {

    int lastI = 0;
    int lastJ = 1;
    public Checker won = Checker.NOCHECKER;
    private int nBlack = 12;
    private ArrayList<int[]> lastPlacesToGo;
    private int nWhite = 12;
    private Map<Integer, int[]> eat;
    private Checker[][] board;
    private DamaPosition damaPosition;
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

    public int decision(int i, int j) {

        Checker clicked = board[i][j];

        if (Checker.isPlaceToGo(clicked))
            move(i, j);
        else if (Checker.wasClicked(clicked)) {
            board[i][j] = Checker.undoClicked(board[i][j]);
            clearLastPlacesToGo();
            clearNotEatenCheckers();
            lastI = 0;
            lastJ = 1;
        } else {
            if (!hasToEat || hasSomethingToEat(i, j)) {
                notMove(i, j);
            }
        }
        return 1;
    }

    public void move(int i, int j) {

        Checker checker = board[i][j];
        obligation = false;
        if (eat.containsKey(i * 10 + j)) {
            if (turn == Turn.WHITE) nBlack--;
            else nWhite--;
            board[eat.get(i * 10 + j)[0]][eat.get(i * 10 + j)[1]] = Checker.NOCHECKER;
            eat.remove(i * 10 + j);
            if (hasSomethingToEat(i, j)) {
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

    public void moveCheckerToPlace(int i, int j, Checker checker) {
        if (Checker.isDamaPlaceToGo(checker)) {
            board[i][j] = Checker.makeDama(checker);
        } else board[i][j] = Checker.moveCheckerToPlace(checker);
    }

    public boolean isDamaPlace(int i, int j, Checker checker) {
        if ((Checker.color(checker) == Checker.BLACK && i == 7) || (Checker.color(checker) == Checker.WHITE && i == 0)) {
            return true;
        }
        return false;

    }

    public void invertPlayer() {

        turn = Turn.invert(turn);
        checkEat();
    }

    public boolean checkEat() {
        hasToEat = false;

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == turn.label) {

                    ;
                    if (hasSomethingToEat(i, j)) {
                        hasToEat = true;
                        return true;
                    }

                }
            }
        }
        return false;
    }

    public boolean hasSomethingToEat(int i, int j) {
        Checker checker = board[i][j];
        int[] ii = new int[]{2, 2, -2, -2};
        int[] yy = new int[]{-2, 2, -2, 2};
        int[] ieat = new int[]{1, 1, -1, -1};
        int[] yeat = new int[]{-1, 1, -1, 1};
        for (int index = 0; index < 4; index++) {
            int futI = i + ii[index];
            int futJ = j + yy[index];
            if (futI > -1 && futI < 8 && futJ < 8 && futJ > -1) {
                if (Checker.color(board[i + ieat[index]][j + yeat[index]]) == Checker.invertColor(Checker.color(checker)) && board[futI][futJ] == Checker.NOCHECKER) {

                    return true;
                }
            }
        }

        return false;
    }

    public void checkWin() {
        if (nWhite == 0) won = Checker.BLACK;
        if (nBlack == 0) won = Checker.WHITE;
    }

    public int notMove(int i, int j) {

        Checker clicked = board[i][j];
        if (Checker.color(clicked) == Checker.WHITE && Turn.BLACK == turn) return 1;
        if (Checker.color(clicked) == Checker.BLACK && Turn.WHITE == turn) return 1;

        if (Checker.wasClicked(board[lastI][lastJ])) {
            board[lastI][lastJ] = Checker.undoClicked(board[lastI][lastJ]);
        }
        lastI = i;
        lastJ = j;

        clearLastPlacesToGo();
        clearNotEatenCheckers();
        board[i][j] = Checker.makeClicked(board[i][j]);
        whereToGo(i, j, clicked);
        position(i, j, clicked);

        return 1;
    }

    public void whereToGo(int i, int j, Checker checker) {
        if (Checker.isDama(checker)) board = damaPosition.position(i, j, board);
        else position(i, j, checker);
    }

    public void position(int i, int j, Checker checker) {

        int direction = Checker.color(checker).label;

        if (!hasToEat && ((i != 7 && Checker.color(checker) == Checker.BLACK) || (i != 0 && Checker.color(checker) == Checker.WHITE))) {
            // go right
            if (j - 1 != -1) {

                if (board[i + direction][j - 1] == Checker.NOCHECKER) {
                    if (isDamaPlace(i + direction, j - 1, checker)) {
                        board[i + direction][j - 1] = Checker.makePlaceToGo(Checker.makeDama(checker));
                    } else board[i + direction][j - 1] = Checker.makePlaceToGo(checker);

                    lastPlacesToGo.add(new int[]{i + direction, j - 1});
                }
            }

            //go left
            if (j + 1 != 8) {
                if (board[i + direction][j + 1] == Checker.NOCHECKER) {
                    if (isDamaPlace(i + direction, j + 1, checker)) {
                        board[i + direction][j + 1] = Checker.makePlaceToGo(Checker.makeDama(checker));
                    } else board[i + direction][j + 1] = Checker.makePlaceToGo(checker);


                    lastPlacesToGo.add(new int[]{i + direction, j + 1});
                }
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
                if (Checker.color(board[i + ieat[index]][j + yeat[index]]) == Checker.invertColor(checker) && board[futI][futJ] == Checker.NOCHECKER) {
                    if (isDamaPlace(futI, j + 1, checker)) {
                        board[futI][futJ] = Checker.makePlaceToGo(Checker.makeDama(checker));
                    } else board[futI][futJ] = Checker.makePlaceToGo(checker);
                    board[i + ieat[index]][j + yeat[index]] = Checker.willBeEaten(Checker.invertColor(checker));
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

        }
        eat.clear();
    }


    public void init() {

        damaPosition = new DamaPosition();
        damaPosition.setOnPlacesToGo(this);
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

    public void onPlacesToGo(ArrayList<int[]> lastPlacesToGo) {

        this.lastPlacesToGo = lastPlacesToGo;

    }
}
