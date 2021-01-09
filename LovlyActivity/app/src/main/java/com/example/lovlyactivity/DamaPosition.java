package com.example.lovlyactivity;

import java.util.ArrayList;

public class DamaPosition {

    private OnPlacesToGo onPlacesToGo = null;

    public DamaPosition() {

    }

    public void setOnPlacesToGo(OnPlacesToGo onClearPlacesToGo) {
        this.onPlacesToGo = onClearPlacesToGo;
    }

    public Checker[][] position(int i, int j, Checker[][] board) {

        Checker checker = Checker.undoClicked(board[i][j]);

        int[] igo = new int[]{-1, -1, 1, 1};
        int[] jgo = new int[]{-1, 1, -1, 1};
        ArrayList<int[]> arr = new ArrayList<>();

        for (int direction = 0; direction < 4; direction++) {
            int squares = 1;
            while (true) {
                int indexi = i + igo[direction] * squares;
                int indexj = j + jgo[direction] * squares;
                if (indexi > 7 || indexi < 0 || indexj > 7 || indexj < 0) break;
                if (board[indexi][indexj] == Checker.NOCHECKER) {
                    board[indexi][indexj] = Checker.makePlaceToGo(checker);
                    arr.add(new int[]{indexi, indexj});
                    squares++;
                } else break;
            }
        }
        onPlacesToGo.onPlacesToGo(arr);
        return board;
    }

}
