package com.example.lovlyactivity;

import com.example.lovlyactivity.enums.Checker;
import com.example.lovlyactivity.interfaces.OnCheckersToEat;
import com.example.lovlyactivity.interfaces.OnPlacesToGo;

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

    public Checker[][] position(int i, int j, Checker[][] board) {

        Checker checker = Checker.undoClicked(board[i][j]);
        for (int direction = 0; direction < 4; direction++) {
            int squares = 1;
            while (true) {
                int indexi = i + igo[direction] * squares;
                int indexj = j + jgo[direction] * squares;
                if (indexi > 7 || indexi < 0 || indexj > 7 || indexj < 0) break;
                if (board[indexi][indexj] == Checker.NOCHECKER) {
                    board[indexi][indexj] = Checker.makePlaceToGo(checker);
                    lastPlacesToGo.add(new Coordinate(indexi, indexj));
                    squares++;
                } else if (board[indexi][indexj] == Checker.invertColor(checker)) {
                    if (board[indexi + igo[direction]][indexj + jgo[direction]] == Checker.NOCHECKER) {
                        board[indexi][indexj] = Checker.willBeEaten(Checker.invertColor(checker));
                        Coordinate checkerWillBeEaten = new Coordinate(indexi, indexj);
                        squares++;
                        indexi = i + igo[direction] * squares;
                        indexj = j + jgo[direction] * squares;

                        while (board[indexi][indexj] == Checker.NOCHECKER) {
                            if (indexi > 7 || indexi < 0 || indexj > 7 || indexj < 0) break;
                            board[indexi][indexj] = Checker.makePlaceToGo(checker);
                            Coordinate coordinate = new Coordinate(indexi, indexj);
                            lastPlacesToGo.add(coordinate);
                            eat.put(coordinate, checkerWillBeEaten);
                        }
                    }
                    break;
                } else {
                    break;
                }
            }
        }
        onPlacesToGo.onPlacesToGo(lastPlacesToGo);
        onCheckersToEat.OnCheckersToEat(eat);
        return board;
    }

    public boolean hasSomethingToEat(int i, int j, Checker[][] board) {
        Checker checker = board[i][j];
        for (int direction = 0; direction < 4; direction++) {
            int squares = 1;
            while (true) {
                int indexi = i + igo[direction] * squares;
                int indexj = j + jgo[direction] * squares;
                if (indexi > 7 || indexi < 0 || indexj > 7 || indexj < 0) break;
                while (board[indexi][indexj] == Checker.NOCHECKER) {
                    indexi += igo[direction];
                    indexj += jgo[direction];
                    if (indexi > 7 || indexi < 0 || indexj > 7 || indexj < 0) {
                        break;
                    }
                    ;
                    squares++;
                }
                if (board[indexi][indexj] == Checker.invertColor(checker)) {
                    if (board[indexi + igo[direction]][indexj + jgo[direction]] == Checker.NOCHECKER) {
                        board[indexi][indexj] = Checker.willBeEaten(Checker.invertColor(checker));
                        Coordinate checkerWillBeEaten = new Coordinate(indexi, indexj);
                        squares++;
                        indexi = i + igo[direction] * squares;
                        indexj = j + jgo[direction] * squares;

                        while (board[indexi][indexj] == Checker.NOCHECKER) {
                            if (indexi > 7 || indexi < 0 || indexj > 7 || indexj < 0) break;
                            board[indexi][indexj] = Checker.makePlaceToGo(checker);
                            Coordinate coordinate = new Coordinate(indexi, indexj);
                            lastPlacesToGo.add(coordinate);
                            eat.put(coordinate, checkerWillBeEaten);
                        }
                    }
                    break;
                } else {
                    break;
                }
            }
        }
        ///////////////////////////////////
        return false;
    }
}
