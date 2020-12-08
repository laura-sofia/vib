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
    WHITE_WILL_BE_EATEN(-4);

    public int label;

    Checker(int label) {
        this.label = label;
    }

    public static Checker invertColor(Checker checker) {
        if (checker == BLACK) return WHITE;
        return BLACK;
    }

    public static Checker makePlaceToGo(Checker checker) {
        if (checker == BLACK) return BLACK_PLACE_TO_GO;
        return WHITE_PLACE_TO_GO;
    }

    public static Checker moveCheckerToPlace(Checker checker) {
        if (checker == BLACK_PLACE_TO_GO) return BLACK;
        return WHITE;
    }

    public static Checker makeClicked(Checker checker) {
        if (checker == BLACK) return BLACK_CLICKED;
        return WHITE_CLICKED;
    }

    public static Checker undoClicked(Checker checker) {
        if (checker == BLACK_CLICKED) return BLACK;
        return WHITE;
    }

    public static Checker willBeEaten(Checker checker) {
        if (checker == BLACK) return BLACK_WILL_BE_EATEN;
        return WHITE_WILL_BE_EATEN;
    }

    public static Checker undoWillBeEaten(Checker checker) {
        if (checker == BLACK_WILL_BE_EATEN) return BLACK;
        return WHITE;
    }

    public static Checker color(Checker checker) {
        if (checker.label > 0) return BLACK;
        if (checker.label == 0) return NOCHECKER;
        return WHITE;
    }

}
