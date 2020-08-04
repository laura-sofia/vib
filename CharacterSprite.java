package com.example.vrv3;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;

public class CharacterSprite {

    private Bitmap image;
    private int x,y;
    private int xVel=10;
    private int yVel=5;
    private int screenWidth= Resources.getSystem().getDisplayMetrics().widthPixels;
    private int screenHeight= Resources.getSystem().getDisplayMetrics().heightPixels;

    public CharacterSprite(Bitmap image){
        this.image=image;
        x=0;
        y=0;
    }
    public void draw(Canvas canvas){

        canvas.drawBitmap(image, x, y,null);
    }

    public void update() {

        x+=xVel;
        y+=yVel;

        if(x>screenWidth-image.getWidth() || x<0){
            xVel=xVel*-1;
        }
        if(y>screenHeight-image.getHeight() || y<0){
            yVel=yVel*-1;
        }
    }
}
