package com.example.blabla;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import static android.graphics.Color.*;

public class CharacterSprite {

    private int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
    private int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;
    private int left=100;
    private int top=100;
    private int[] touched=new int[2];
    public void draw(Canvas canvas){
        canvas.drawColor(WHITE);
        Paint paint = new Paint();
        paint.setColor(RED);



        int right=screenWidth-left-100;
        int bottom=screenHeight-top-100;
        canvas.drawRect(left, top, 200, 200, paint);

    }
    public void update(float x, float y){


        left=(int)x;
        top=(int)y;


    }
}
