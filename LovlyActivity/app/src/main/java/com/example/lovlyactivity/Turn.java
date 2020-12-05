package com.example.lovlyactivity;

public enum Turn {
    WHITE(false),
    BLACK(true);

    public final boolean label;

    Turn(boolean label) {
        this.label = label;
    }

    public static Turn invert(Turn turn) {
        if (turn == WHITE) return BLACK;
        return WHITE;
    }

    public boolean turn() {
        return this.label;
    }


}
