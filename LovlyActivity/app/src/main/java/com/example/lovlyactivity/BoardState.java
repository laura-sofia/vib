package com.example.lovlyactivity;

import android.graphics.Canvas;

public class BoardState {

    private DecisionClass decisionClass;
    private DrawingClass drawingClass;
    private boolean win = false;

    public BoardState() {
        init();
    }

    public Checker[][] getBoard() {
        return decisionClass.getBoard();
    }

    public Turn getTurn() {
        return decisionClass.getTurn();
    }

    public void drawSquare(Canvas canvas, int width, int height) {
        if (decisionClass.won != Checker.NOCHECKER) {
            drawingClass.drawWin(canvas, width, height, decisionClass.won);
            win = true;
        } else {
            drawingClass.drawSquare(canvas, width, height, getBoard(), getTurn());
        }

    }

    public void update(float x, float y, int height, int width) {
        if (win) {
            init();
            win = false;
        }
        decisionClass.update(x, y, height, width);

    }

    private void init() {
        decisionClass = new DecisionClass();
        drawingClass = new DrawingClass();
    }
}
