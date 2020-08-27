package com.example.vrv1a;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class CharacterSprite {

    private Paint paint;
    public CharacterSprite(){

        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.RED);

    }

    public void update(float x, float y) {
    }

    public void draw(Canvas canvas) {



        canvas.drawRect(20,20,20,20,paint);
    }
}
