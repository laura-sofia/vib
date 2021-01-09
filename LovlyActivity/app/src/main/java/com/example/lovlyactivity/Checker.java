package com.example.lovlyactivity;

public enum Checker {
    NOCHECKER(0),
    BLACK(1),
    WHITE(-1),
    BLACK_CLICKED(2),
    WHITE_CLICKED(-2),
    BLACK_PLACE_TO_GO(3),
    WHITE_PLACE_TO_GO(-3),
    BLACK_WILL_BE_EATEN(4),
    WHITE_WILL_BE_EATEN(-4),
    BLACK_DAMA(5),
    WHITE_DAMA(-5),
    BLACK_DAMA_CLICKED(6),
    WHITE_DAMA_CLICKED(-6),
    BLACK_DAMA_PLACE_TO_GO(7),
    WHITE_DAMA_PLACE_TO_GO(-7),
    BLACK_DAMA_WILL_BE_EATEN(8),
    WHITE_DAMA_WILL_BE_EATEN(-8);

    public int label;

    Checker(int label) {
        this.label = label;
    }

    public static boolean isDamaPlaceToGo(Checker checker) {
        if (checker == BLACK_DAMA_PLACE_TO_GO || checker == WHITE_DAMA_PLACE_TO_GO) return true;
        return false;
    }

    public static boolean isPlaceToGo(Checker checker) {
        int u = Math.abs(checker.label);
        if (u == 3 || u == 7) return true;
        return false;
    }

    public static boolean wasClicked(Checker checker) {
        int a = Math.abs(checker.label);
        if (a == 2 || a == 6) return true;
        return false;
    }

    public static Checker makeDama(Checker checker) {
        if (color(checker) == BLACK) return BLACK_DAMA;
        return WHITE_DAMA;
    }

    public static boolean isDama(Checker checker) {
        int u = Math.abs(checker.label);
        if (u == 5 || u == 6) return true;
        return false;
    }

    public static Checker invertColor(Checker checker) {
        if (color(checker) == BLACK) return WHITE;
        return BLACK;
    }

    public static String checkerToString(Checker checker) {
        if (checker == BLACK) return "black";
        return "white";
    }

    public static Checker makePlaceToGo(Checker checker) {
        if (checker == BLACK) return BLACK_PLACE_TO_GO;
        if (checker == BLACK_DAMA) return BLACK_DAMA_PLACE_TO_GO;
        if (checker == WHITE) return WHITE_PLACE_TO_GO;
        return WHITE_DAMA_PLACE_TO_GO;
    }

    public static Checker moveCheckerToPlace(Checker checker) {
        if (checker == BLACK_PLACE_TO_GO) return BLACK;
        return WHITE;
    }

    public static Checker makeClicked(Checker checker) {
        if (checker == BLACK) return BLACK_CLICKED;
        if (checker == BLACK_DAMA) return BLACK_DAMA_CLICKED;
        if (checker == WHITE) return WHITE_CLICKED;
        return WHITE_DAMA_CLICKED;
    }

    public static Checker undoClicked(Checker checker) {
        if (checker == BLACK_CLICKED) return BLACK;
        if (checker == BLACK_DAMA_CLICKED) return BLACK_DAMA;
        if (checker == WHITE_CLICKED) return WHITE;
        return WHITE_DAMA;
    }

    public static Checker willBeEaten(Checker checker) {
        if (checker == BLACK) return BLACK_WILL_BE_EATEN;
        if (checker == BLACK_DAMA) return BLACK_DAMA_WILL_BE_EATEN;
        if (checker == WHITE_DAMA) return WHITE_DAMA_WILL_BE_EATEN;
        return WHITE_WILL_BE_EATEN;
    }

    public static Checker undoWillBeEaten(Checker checker) {
        if (checker == BLACK_WILL_BE_EATEN) return BLACK;
        if (checker == BLACK_DAMA_WILL_BE_EATEN) return BLACK_DAMA;
        if (checker == WHITE_DAMA_WILL_BE_EATEN) return WHITE_DAMA;
        return WHITE;
    }

    public static Checker color(Checker checker) {
        if (checker.label > 0) return BLACK;
        if (checker.label == 0) return NOCHECKER;
        return WHITE;
    }

}
