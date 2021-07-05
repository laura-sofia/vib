package com.example.lovlyactivity;

import com.example.lovlyactivity.enums.Checker;
import com.example.lovlyactivity.interfaces.OnCheckersToEat;
import com.example.lovlyactivity.interfaces.OnPlacesToGo;
import com.example.lovlyactivity.structure.Coordinate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class NormalChPosition {
    private Map<Coordinate, Coordinate> eat;
    private Checker[][] board;
    private OnPlacesToGo onPlacesToGo = null;
    private OnCheckersToEat onCheckersToEat = null;
    private ArrayList<Coordinate> lastPlacesToGo;

    public NormalChPosition() {
        eat = new HashMap<>();
        lastPlacesToGo = new ArrayList<>();
    }

    public void setOnPlacesToGo(OnPlacesToGo onClearPlacesToGo) {
        this.onPlacesToGo = onClearPlacesToGo;
    }

    public void setEat(OnCheckersToEat onCheckersToEat) {
        this.onCheckersToEat = onCheckersToEat;
    }

    public Checker[][] position(int i, int j, Checker checker, Checker[][] board, boolean hasToEat) {

        this.board = board;

        int direction = Checker.color(checker).label;

        if (!hasToEat && ((i != 7 && Checker.color(checker) == Checker.BLACK) || (i != 0 && Checker.color(checker) == Checker.WHITE))) {
            // go right
            if (j - 1 != -1) {
                if (board[i + direction][j - 1] == Checker.NOCHECKER) {
                    if (isDamaPlace(i + direction, j - 1, checker)) {
                        board[i + direction][j - 1] = Checker.makePlaceToGo(Checker.makeDama(checker));
                    } else board[i + direction][j - 1] = Checker.makePlaceToGo(checker);
                    Coordinate c = new Coordinate(i + direction, j - 1);
                    lastPlacesToGo.add(c);
                }
            }

            //go left
            if (j + 1 != 8) {
                if (board[i + direction][j + 1] == Checker.NOCHECKER) {
                    if (isDamaPlace(i + direction, j + 1, checker)) {
                        board[i + direction][j + 1] = Checker.makePlaceToGo(Checker.makeDama(checker));
                    } else board[i + direction][j + 1] = Checker.makePlaceToGo(checker);

                    lastPlacesToGo.add(new Coordinate(i + direction, j + 1));
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
                    Coordinate c1 = new Coordinate(futI, futJ);
                    Coordinate c2 = new Coordinate(i + ieat[index], j + yeat[index]);
                    eat.put(c1, c2);
                    lastPlacesToGo.add(c1);

                }
            }
        }
        onPlacesToGo.onPlacesToGo(lastPlacesToGo);
        onCheckersToEat.OnCheckersToEat(eat);
        return board;
    }

    public boolean hasSomethingToEat(int i, int j, Checker[][] board) {
        Checker checker = this.board[i][j];
        int[] ii = new int[]{2, 2, -2, -2};
        int[] yy = new int[]{-2, 2, -2, 2};
        int[] ieat = new int[]{1, 1, -1, -1};
        int[] yeat = new int[]{-1, 1, -1, 1};
        for (int index = 0; index < 4; index++) {
            int futI = i + ii[index];
            int futJ = j + yy[index];
            if (futI > -1 && futI < 8 && futJ < 8 && futJ > -1) {
                if (Checker.color(this.board[i + ieat[index]][j + yeat[index]]) == Checker.invertColor(Checker.color(checker)) && this.board[futI][futJ] == Checker.NOCHECKER) {

                    return true;
                }
            }
        }

        return false;
    }

    public boolean isDamaPlace(int i, int j, Checker checker) {
        return (Checker.color(checker) == Checker.BLACK && i == 7) || (Checker.color(checker) == Checker.WHITE && i == 0);
    }

}
