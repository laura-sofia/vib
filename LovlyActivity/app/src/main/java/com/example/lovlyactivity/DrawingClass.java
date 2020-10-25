package com.example.lovlyactivity;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class DrawingClass {

    private Paint square=new Paint();
    private Paint blackSquare=new Paint();
    private Paint whiteCircle=new Paint();
    private Paint blackCircle= new Paint();
    private Paint blueCircle=new Paint();
    private Paint TextColor=new Paint();
    private String black,white;

    private int[][] arr;
    private boolean turn;

    private DecisionClass decisionClass;

    public DrawingClass(){

        arr=new int[8][8];
        init();
    }
    public void update(float x, float y, int width, int height){
        decisionClass.update(x, y, width, height);
    }

    public void drawSquare(Canvas canvas, int width,int height){

        arr=decisionClass.getArr();
        turn =decisionClass.getTurn();
        String now;
        if (!turn)now=white;
        else now=black;
        canvas.drawColor(Color.GRAY);
        canvas.drawText("It's "+now+ " turn.",40,120, TextColor);

        int top=(height-width+20)/2;
        int smallSquare=width/8-5;
        int x=20+smallSquare/2;
        int y=top+smallSquare/2;

        int xS=20;
        int yS=top;
        canvas.drawRect(20,top,20+8*smallSquare,top+8*smallSquare,square);

        for (int i=0;i<8;i++){
            for (int j=0;j<8;j++){
                int a=arr[i][j];
                if ((i+j)%2>0){
                canvas.drawRect(xS,yS,xS+smallSquare,yS+smallSquare,blackSquare);
                 }
                if (Math.abs(a)==3){
                    canvas.drawCircle(x,y,smallSquare/2-5,blueCircle);
                }
                else if (a>0){
                    canvas.drawCircle(x,y,smallSquare/2-5,blackCircle);
                    if (a==2)canvas.drawCircle(x,y,smallSquare/2-10,blueCircle);
                }
                else if(a<0){
                    canvas.drawCircle(x,y,smallSquare/2-5,whiteCircle);
                    if (a==-2)canvas.drawCircle(x,y,smallSquare/2-10,blueCircle);
                }

                x+=smallSquare;
                xS+=smallSquare;
            }
            y+=smallSquare;
            yS+=smallSquare;
            x=20+smallSquare/2;
            xS=20;
        }


    }


    public void init(){

        decisionClass=new DecisionClass();

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

        black="black";
        white="white";


    }
}
