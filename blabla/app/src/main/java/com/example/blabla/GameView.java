package com.example.blabla;

import android.content.Context;
import android.graphics.Canvas;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.view.MotionEventCompat;

public class GameView extends View {

    private MainThread thread;
    private CharacterSprite characterSprite;

    //private TouchEvent touchEvent;

    private float X;
    private float Y;


    public GameView(Context context) {
        super(context);

        thread = new MainThread( this, this);
       // touchEvent = new TouchEvent(context,getHolder());
        setFocusable(true);

    }


    public void update() {



    }
    @Override
    public void draw(Canvas canvas) {

        super.draw(canvas);
        if (canvas!=null)characterSprite.draw(canvas);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event){


        return false;
    }


}
