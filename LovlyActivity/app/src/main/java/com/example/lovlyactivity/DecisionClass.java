package com.example.lovlyactivity;

import java.util.LinkedList;
import java.util.Queue;

public class DecisionClass {

    int lastI=0;
    int lastJ=1;
    private boolean turn=false;
    private int[][]arr;

    public DecisionClass(){
        init();
    }
    public int[][] getArr(){
        return arr;
    }
    public boolean getTurn(){
        return turn;
    }
    public void update(float x, float y, int height, int width){

        float posX=x-20;
        float posY=y-(height-width+20)/2;

        float square=width-40;
        float smallSquare=square/8;

        if (posX>0 && posX<square && posY>0 && posY<square){

            int i= (int) ((int)posY/smallSquare);
            int j= (int) ((int)posX/smallSquare);

            if (arr[i][j]!=0){

                decision(i,j);
            }
        }
    }


    public int decision(int i, int j){

        int a=Math.abs(arr[i][j]);

        if (a==3)move(i,j);
        else if (a==2){
            arr[i][j]/=2;
            for (int g=0;g<8;g++){
                for (int h=0;h<8;h++){
                    if (Math.abs(arr[g][h])==3)arr[g][h]=0;
                }
            }
            lastI=0;
            lastJ=1;
        }
        else{
            notMove(i, j);
        }
        return 1;
    }

    public void move(int i,int j){

        int a=arr[i][j];
        arr[i][j]=a/3;

        for (int g=0;g<8;g++){
            for (int h=0;h<8;h++){
                if (Math.abs(arr[g][h])==3)arr[g][h]=0;
            }
        }

        arr[lastI][lastJ]=0;
        turn=!turn;
    }
    public int  notMove(int i,int j){

        int a=arr[i][j];
        if (a<0 && turn)return 1;
        if (a>0 && !turn) return 1;

        if (Math.abs(arr[lastI][lastJ])==2)arr[lastI][lastJ]/=2;


        lastI=i;
        lastJ=j;

        for (int g=0;g<8;g++){
            for (int h=0;h<8;h++){
                if (Math.abs(arr[g][h])==3)arr[g][h]=0;
            }
        }
        arr[i][j]*=2;
        if (i==7 && a==1)return 1;
        if (i==0 && a==-1) return 1;
        position(i,j,a);

        return 1;
    }
    public void position(int i,int j,int a){
        int b=a*-1;
        if (j-1!=-1){

            if (arr[i+a][j-1]==0)arr[i+a][j-1]=3*a;
            else if(j-2>-1 && i+2<8){
                if (arr[i+a][j-1]==b && arr[i+2*a][j-2]==0){

                }
            }
        }

        if (j+1!=8){
            if (arr[i+a][j+1]==0)arr[i+a][j+1]=3*a;
        }
    }


    public void init(){
        arr=new int[8][8];
        for (int i=0;i<3;i++){
            for (int j=(i+1)%2;j<8;j+=2){
                arr[i][j]=1;
            }
        }

        for (int j=0;j<8;j+=2)arr[5][j]=-1;
        for (int j=1;j<8;j+=2)arr[6][j]=-1;
        for (int j=0;j<8;j+=2)arr[7][j]=-1;

    }

}
