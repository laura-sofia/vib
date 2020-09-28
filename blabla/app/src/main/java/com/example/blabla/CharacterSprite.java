package com.example.blabla;

import android.bluetooth.BluetoothA2dp;
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

    private int[] colors= new int[]{GRAY,GREEN, BLUE,RED};

    private int a=0;

    public void draw(Canvas canvas){

        int now=colors[a];

        canvas.drawColor(now);
        Paint paint = new Paint();
        paint.setColor(RED);

        canvas.drawRect(left, top, 200, 200, paint);

    }
    public void update(){


        a++;

    }
}
