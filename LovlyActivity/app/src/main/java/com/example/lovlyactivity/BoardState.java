package com.example.lovlyactivity;

import android.graphics.Canvas;

public class BoardState {

    private DecisionClass decisionClass;
    private DrawingClass drawingClass;

    public BoardState() {
        decisionClass = new DecisionClass();
        drawingClass = new DrawingClass();
    }

    public int[][] getBoard() {
        return decisionClass.getBoard();
    }

    public boolean getTurn() {
        return decisionClass.getTurn();
    }

    public void drawSquare(Canvas canvas, int width, int height) {
        drawingClass.drawSquare(canvas, width, height, getBoard(), getTurn());
    }

    public void update(float x, float y, int height, int width) {
        decisionClass.update(x, y, height, width);
    }
}
