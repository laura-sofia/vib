package com.example.lovlyactivity;

import android.graphics.Canvas;

public class BoardState extends BoardActivity {

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
        Checker a = decisionClass.won;
        if (a != Checker.NOCHECKER) {
            win = true;
            // Intent intent=new Intent(this,Win.class);
            // startActivity(intent);
            super.win();
            // MainActivity.win(decisionClass.won);
            // drawingClass.drawWin(canvas, width, height, decisionClass.won);

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
