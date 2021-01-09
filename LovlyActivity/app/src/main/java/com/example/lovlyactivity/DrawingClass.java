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
    private Paint yellow = new Paint();

    public DrawingClass() {

        init();
    }

    public void drawSquare(Canvas canvas, int width, int height, Checker[][] board, Turn turn) {

        canvas.drawColor(Color.GRAY);
        canvas.drawText("It's " + turn.toString() + "'s turn.", 40, 120, TextColor);

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
                if (Checker.isPlaceToGo(checker)) {
                    canvas.drawCircle(x, y, (float) smallSquare / 2 - 5, blueCircle);
                } else if (checker.label > 0) {
                    if (Checker.isDama(checker))
                        canvas.drawCircle(x, y, smallSquare / 2, yellow);

                    canvas.drawCircle(x, y, (float) smallSquare / 2 - 5, blackCircle);
                    if (Checker.wasClicked(checker))
                        canvas.drawCircle(x, y, smallSquare / 2 - 10, blueCircle);
                    if (checker == Checker.BLACK_WILL_BE_EATEN)
                        canvas.drawCircle(x, y, smallSquare / 2 - 10, eatenColor);

                } else if (checker.label < 0) {
                    if (Checker.isDama(checker))
                        canvas.drawCircle(x, y, smallSquare / 2, yellow);
                    canvas.drawCircle(x, y, smallSquare / 2 - 5, whiteCircle);
                    if (Checker.wasClicked(checker))
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

        yellow.setStyle(Paint.Style.FILL);
        yellow.setColor(Color.YELLOW);
    }
}
