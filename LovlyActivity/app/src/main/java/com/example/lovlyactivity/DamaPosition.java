package com.example.lovlyactivity;

import com.example.lovlyactivity.enums.Checker;
import com.example.lovlyactivity.interfaces.OnCheckersToEat;
import com.example.lovlyactivity.interfaces.OnPlacesToGo;
import com.example.lovlyactivity.structure.Coordinate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DamaPosition {

    private OnPlacesToGo onPlacesToGo = null;
    private OnCheckersToEat onCheckersToEat = null;
    private Map<Coordinate, Coordinate> eat;
    private ArrayList<Coordinate> lastPlacesToGo;
    private int[] igo = new int[]{-1, -1, 1, 1};
    private int[] jgo = new int[]{-1, 1, -1, 1};
    private boolean[] goToEat = new boolean[4];

    public DamaPosition() {
        eat = new HashMap<>();
        lastPlacesToGo = new ArrayList<>();

    }

    public void setOnCheckersToEat(OnCheckersToEat onCheckersToEat) {
        this.onCheckersToEat = onCheckersToEat;
    }

    public void setOnPlacesToGo(OnPlacesToGo onClearPlacesToGo) {
        this.onPlacesToGo = onClearPlacesToGo;
    }

    public Checker[][] position(int i, int j, Checker[][] board, boolean hasToEat) {

        Checker checker = Checker.undoClicked(board[i][j]);
        boolean obligation = hasSomethingToEat(i, j, board);

        for (int direction = 0; direction < 4; direction++) {

            if (obligation && !goToEat[direction]) continue;

            int indexi = i + igo[direction];
            int indexj = j + jgo[direction];
            if (checkBounds(indexi, indexj)) continue;
            while (board[indexi][indexj] == Checker.NOCHECKER) {

                if (!goToEat[direction]) {
                    board[indexi][indexj] = Checker.makePlaceToGo(checker);
                    lastPlacesToGo.add(new Coordinate(indexi, indexj));
                }
                indexi += igo[direction];
                indexj += jgo[direction];
                if (checkBounds(indexi, indexj)) break;
            }
            if (!goToEat[direction]) continue;
            board[indexi][indexj] = Checker.willBeEaten(board[indexi][indexj]);
            Coordinate checkerWillBeEaten = new Coordinate(indexi, indexj);
            indexi += igo[direction];
            indexj += jgo[direction];

            while (board[indexi][indexj] == Checker.NOCHECKER) {

                board[indexi][indexj] = Checker.makePlaceToGo(checker);
                Coordinate coordinate = new Coordinate(indexi, indexj);
                lastPlacesToGo.add(coordinate);
                eat.put(coordinate, checkerWillBeEaten);
                indexi += igo[direction];
                indexj += jgo[direction];
                if (checkBounds(indexi, indexj)) break;
            }

        }
        onPlacesToGo.onPlacesToGo(lastPlacesToGo);
        onCheckersToEat.OnCheckersToEat(eat);
        return board;
    }

    public boolean hasSomethingToEat(int i, int j, Checker[][] board) {
        Checker checker = board[i][j];
        goToEat = new boolean[4];
        boolean res = false;
        for (int direction = 0; direction < 4; direction++) {
            int indexi = i + igo[direction];
            int indexj = j + jgo[direction];
            if (checkBounds(indexi, indexj)) continue;

            while (board[indexi][indexj] == Checker.NOCHECKER) {
                indexi += igo[direction];
                indexj += jgo[direction];
                if (checkBounds(indexi, indexj)) break;
            }
            if (checkBounds(indexi, indexj)) continue;

            if (Checker.color(board[indexi][indexj]) == Checker.invertColor(checker)) {
                int tempi = indexi + igo[direction];
                int tempj = indexj + jgo[direction];
                if (checkBounds(tempi, tempj)) continue;
                if (board[tempi][tempj] == Checker.NOCHECKER) {
                    goToEat[direction] = true;
                    res = true;
                }
            }
        }
        ///////////////////////////////////
        return res;
    }

    public boolean checkBounds(int indexi, int indexj) {
        return (indexi > 7 || indexi < 0 || indexj > 7 || indexj < 0);
    }
}
