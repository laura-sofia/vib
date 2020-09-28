package com.example.lovlyactivity;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.TextPaint;

public class drawingClass {

    private Paint square=new Paint();
    private Paint blackSquare=new Paint();
    private Paint whiteCircle=new Paint();
    private Paint blackCircle= new Paint();

    private boolean yes;
    private int[][] arr;

    public drawingClass(){

        yes=false;

        arr=new int[8][8];//{{0,1,1,1,1,1,1,1},{1,1,1,1,1,1,1,1},{1,1,1,1,1,1,1,1},{1,1,1,1,1,1,1,1},{1,1,1,1,1,1,1,1},{1,1,1,1,1,1,1,1},{1,1,1,1,1,1,1,1},{1,1,1,1,1,1,1,1}};
        init();
    }


    public void drawSquare(Canvas canvas, int width,int height){

        canvas.drawColor(Color.GRAY);

        canvas.drawRect(20,(height-width+20)/2,width-20,(height+width+20)/2,square);

        int smallSquare=width/8-5;
        int x=20+smallSquare/2;
        int y=(height-width+20+smallSquare)/2;

        int xS=20;
        int yS=(height-width+20)/2;
        for (int i=0;i<8;i++){
            for (int j=0;j<8;j++){
                if ((i+j)%2>0){
                canvas.drawRect(xS,yS,xS+smallSquare,yS+smallSquare,blackSquare);
            }
                if (arr[i][j]==1){
                    canvas.drawCircle(x,y,smallSquare/2-5,blackCircle);
                }
                else if(arr[i][j]==-1){
                    canvas.drawCircle(x,y,smallSquare/2-5,whiteCircle);
                }

                x+=smallSquare;
                xS+=smallSquare;
            }
            y+=smallSquare;
            yS+=smallSquare;
            x=20+smallSquare/2;
            xS=20;
        }

        //canvas.drawText(Integer.toString(width)+" "+Integer.toString(height),300,300,textColor);

        if (yes){
            canvas .drawCircle(width/2,height/2,400,whiteCircle);
        }
    }

    public void update(float x, float y){
        yes=!yes;

    }

    public void init(){
        for (int i=0;i<3;i++){
            for (int j=(i+1)%2;j<8;j+=2){
                arr[i][j]=1;
            }
        }

        for (int j=0;j<8;j+=2)arr[5][j]=-1;
        for (int j=1;j<8;j+=2)arr[6][j]=-1;
        for (int j=0;j<8;j+=2)arr[7][j]=-1;

        blackSquare.setColor(Color.BLACK);
        blackSquare.setStyle(Paint.Style.FILL);

        whiteCircle.setColor(Color.WHITE);
        whiteCircle.setStyle(Paint.Style.FILL);

        blackCircle.setColor(Color.GREEN);
        blackCircle.setStyle(Paint.Style.FILL);

        square.setColor(Color.WHITE);
        square.setStyle(Paint.Style.FILL);
    }
}
