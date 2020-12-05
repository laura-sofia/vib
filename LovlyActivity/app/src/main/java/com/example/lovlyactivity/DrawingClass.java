package com.example.lovlyactivity;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class DrawingClass {

    private Paint square=new Paint();
    private Paint blackSquare = new Paint();
    private Paint whiteCircle = new Paint();
    private Paint blackCircle = new Paint();
    private Paint blueCircle = new Paint();
    private Paint TextColor = new Paint();
    private Paint eatenColor = new Paint();
    private Paint hasToEat = new Paint();
    private String black, white;

   // private int[][] board;
    private boolean turn;

    //  private DecisionClass decisionClass;
    //   private BoardState boardState;

    public DrawingClass() {

        // board = new int[8][8];
        init();
    }

    public void drawWin(Canvas canvas, int width, int height, Checker whoWon) {
        String s;
        if (whoWon == Checker.BLACK) s = black;
        else s = white;
        canvas.drawText(s + " won.", 100, width / 2, TextColor);


    }

    public void drawSquare(Canvas canvas, int width, int height, Checker[][] board, Turn turn) {
        // this.board=board;
        //  this.turn=turn;
        //   board=boardState.getBoard();
        //  turn =boardState.getTurn();
        String now;
        if (turn == Turn.WHITE) now = white;
        else now = black;
        canvas.drawColor(Color.GRAY);
        canvas.drawText("It's " + now + "'s turn.", 40, 120, TextColor);

        int top = (height - width + 20) / 2;
        int smallSquare = width / 8 - 5;
        int x = 20 + smallSquare / 2;
        int y = top + smallSquare / 2;

        int xS=20;
        int yS=top;
        canvas.drawRect(20,top,20+8*smallSquare,top+8*smallSquare,square);

        for (int i=0;i<8;i++){
            for (int j=0;j<8;j++) {
                Checker checker = board[i][j];
                if ((i + j) % 2 > 0) {
                    canvas.drawRect(xS, yS, xS + smallSquare, yS + smallSquare, blackSquare);
                }
                if (checker == Checker.BLACK_PLACE_TO_GO || checker == Checker.WHITE_PLACE_TO_GO) {
                    canvas.drawCircle(x, y, smallSquare / 2 - 5, blueCircle);
                } else if (checker.label > 0) {
                    canvas.drawCircle(x, y, smallSquare / 2 - 5, blackCircle);
                    if (checker == Checker.BLACK_CLICKED)
                        canvas.drawCircle(x, y, smallSquare / 2 - 10, blueCircle);
                    if (checker == Checker.BLACK_WILL_BE_EATEN)
                        canvas.drawCircle(x, y, smallSquare / 2 - 10, eatenColor);
                } else if (checker.label < 0) {
                    canvas.drawCircle(x, y, smallSquare / 2 - 5, whiteCircle);
                    if (checker == Checker.WHITE_CLICKED)
                        canvas.drawCircle(x, y, smallSquare / 2 - 10, blueCircle);
                    if (checker == Checker.WHITE_WILL_BE_EATEN)
                        canvas.drawCircle(x, y, smallSquare / 2 - 10, eatenColor);
                }

                x += smallSquare;
                xS += smallSquare;
            }
            y+=smallSquare;
            yS+=smallSquare;
            x=20+smallSquare/2;
            xS=20;
       }

     }


    public void init() {

        // decisionClass=new DecisionClass();
        //   boardState=new BoardState();

        blackSquare.setColor(Color.BLACK);
        blackSquare.setStyle(Paint.Style.FILL);

        whiteCircle.setColor(Color.WHITE);
        whiteCircle.setStyle(Paint.Style.FILL);

        blackCircle.setColor(Color.GREEN);
        blackCircle.setStyle(Paint.Style.FILL);

        square.setColor(Color.WHITE);
        square.setStyle(Paint.Style.FILL);

        blueCircle.setColor(Color.BLUE);
        blueCircle.setStyle(Paint.Style.FILL_AND_STROKE);

        TextColor.setStyle(Paint.Style.FILL);
        TextColor.setTextSize(100);
        TextColor.setColor(Color.YELLOW);

        eatenColor.setStyle(Paint.Style.FILL);
        eatenColor.setColor(Color.RED);

        hasToEat.setStyle(Paint.Style.FILL);
        hasToEat.setColor(Color.CYAN);

        black = "black";
        white = "white";


    }
}
