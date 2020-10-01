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
    private Paint blueCircle=new Paint();

    private boolean yes;
    private int[][] arr;

    int lastI=0;
    int lastJ=1;

    private int wwidth;
    private int hheight;

    private boolean move=false;

    public drawingClass(){

        yes=false;

        arr=new int[8][8];//{{0,1,1,1,1,1,1,1},{1,1,1,1,1,1,1,1},{1,1,1,1,1,1,1,1},{1,1,1,1,1,1,1,1},{1,1,1,1,1,1,1,1},{1,1,1,1,1,1,1,1},{1,1,1,1,1,1,1,1},{1,1,1,1,1,1,1,1}};
        init();
    }
    public void update(float x, float y){
        //yes=!yes;
        float posX=x-20;
        float posY=y-(hheight-wwidth+20)/2;

        float square=wwidth-40;
        float smallSquare=square/8;

        if (posX>0 && posX<square){
            if (posY>0 && posY<square){


                int i= (int) ((int)posY/smallSquare);
                int j= (int) ((int)posX/smallSquare);

                if (arr[i][j]!=0){

                    if (move)move(i,j);
                    else notMove(i,j);

                }
            }
        }

    }
    public void move(int i,int j){

        int a=arr[i][j];

        if (Math.abs(a)==3){
            arr[i][j]=a/3;
            for (int g=0;g<8;g++){
                for (int h=0;h<8;h++){
                    if (Math.abs(arr[g][h])==3)arr[g][h]=0;
                }
            }

            arr[lastI][lastJ]=0;
            move=false;
        }
        else notMove(i,j);


    }
    public void  notMove(int i,int j){

        int a=arr[i][j];
        if (arr[i][j]==3 || arr[i][j]==-3)move(i, j);

        if (lastI<4)arr[lastI][lastJ]=1;
        else arr[lastI][lastJ]=-1;


        lastI=i;
        lastJ=j;

        for (int g=0;g<8;g++){
            for (int h=0;h<8;h++){
                if (Math.abs(arr[g][h])==3 || Math.abs(arr[g][h])==-3)arr[g][h]=0;
            }
        }



        if (j-1!=-1){
            if (arr[i+a][j-1]==0)arr[i+a][j-1]=3*a;
        }
        if (j+1!=8){
            if (arr[i+a][j+1]==0)arr[i+a][j+1]=3*a;
        }
        arr[i][j]=2;

        move=true;
    }


    public void drawSquare(Canvas canvas, int width,int height){
        wwidth=width;
        hheight=height;

        canvas.drawColor(Color.GRAY);

        int top=(height-width+20)/2;

     //   canvas.drawRect(20,(height-width+20)/2,width-20,(height+width+20)/2,square);

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
                if (a>0){
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

        //canvas.drawText(Integer.toString(width)+" "+Integer.toString(height),300,300,textColor);

        if (yes){
            canvas .drawCircle(width/2,height/2,400,whiteCircle);
        }
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

        blueCircle.setColor(Color.BLUE);
        blueCircle.setStyle(Paint.Style.FILL_AND_STROKE);
    }
}
