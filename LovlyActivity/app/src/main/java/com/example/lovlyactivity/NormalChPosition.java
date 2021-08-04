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
    private int[] ieat = new int[]{2, 2, -2, -2};
    private int[] yeat = new int[]{-2, 2, -2, 2};
    private int[] ii = new int[]{1, 1, -1, -1};
    private int[] yy = new int[]{-1, 1, -1, 1};

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

        if (!hasToEat) {
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
        for (int index = 0; index < 4; index++) {
            int futI = i + ii[index];
            int futJ = j + yy[index];


            if (!outOfBounds(futI, futJ)) {
                Checker next = board[i + ieat[index]][j + yeat[index]];
                if (Checker.color(next) == Checker.invertColor(checker) && board[futI][futJ] == Checker.NOCHECKER) {
                    if (isDamaPlace(futI, j + 1, checker)) {
                        board[futI][futJ] = Checker.makePlaceToGo(Checker.makeDama(checker));
                    } else board[futI][futJ] = Checker.makePlaceToGo(checker);
                    board[i + ieat[index]][j + yeat[index]] = Checker.willBeEaten(next);
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

    public boolean[] hasSomethingToEat(int i, int j, Checker[][] board, boolean onlyEat) {
        this.board = board;///////////////
        Checker checker = board[i][j];
        boolean[] res;
        int futIe, futJe, futI, futJ;
        if (onlyEat) res = new boolean[1];
        else res = new boolean[2];
        for (int index = 0; index < 4; index++) {
            futI = i + ii[index];
            futJ = j + yy[index];
            futIe = i + ieat[index];
            futJe = j + yy[index];
            if (!outOfBounds(futIe, futJe)) {
                if (Checker.color(this.board[futI][futJ]) == Checker.invertColor(Checker.color(checker)) && this.board[futIe][futJe] == Checker.NOCHECKER) {
                    res[0] = true;
                    if (!onlyEat) res[1] = true;
                    return res;
                }
            }
        }
        if (onlyEat) return res;

        int direction = Checker.color(checker).label;
        for (int index = 0; index < 2; index++) {
            futI = i + ii[index] * direction;
            futJ = j + yy[index] * direction;
            if (!outOfBounds(futI, futJ)) {
                if (board[futI][futJ] == Checker.NOCHECKER) {
                    res[1] = true;
                }
            }
        }
        return res;
    }

    public boolean outOfBounds(int i, int j) {
        return (i > 7 || i < 0 || j > 7 || j < 0);
    }

    public boolean isDamaPlace(int i, int j, Checker checker) {
        return (Checker.color(checker) == Checker.BLACK && i == 7) || (Checker.color(checker) == Checker.WHITE && i == 0);
    }

}
