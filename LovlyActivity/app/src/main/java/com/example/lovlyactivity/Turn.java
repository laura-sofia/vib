package com.example.lovlyactivity;

public enum Turn {
    WHITE(Checker.WHITE),
    BLACK(Checker.BLACK);

    public final Checker label;

    Turn(Checker label) {
        this.label = label;
    }

    public static Turn invert(Turn turn) {
        if (turn == WHITE) return BLACK;
        return WHITE;
    }

    public static String turnToString(Turn turn) {
        if (turn == WHITE) return "white";
        return "black";
    }

    public Checker turn() {
        return this.label;
    }


}
