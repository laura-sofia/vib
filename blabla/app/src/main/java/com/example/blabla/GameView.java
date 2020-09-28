package com.example.blabla;

import android.content.Context;
import android.graphics.Canvas;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.view.MotionEventCompat;

public class GameView extends SurfaceView implements SurfaceHolder.Callback{

    private Canvas canvas;
    private CharacterSprite characterSprite;
    private MainThread thread;

    public GameView(Context context) {
        super(context);
        getHolder().addCallback(this);
        characterSprite=new CharacterSprite();
        thread= new MainThread(getHolder(),this);
        setFocusable(true);
    }

    @Override
    public void draw(Canvas canvas) {

        super.draw(canvas);
        characterSprite.draw(canvas);

    }
    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
        thread.setRunning(true);
        thread.start();

    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {



    }


    public void update() {
        characterSprite.update();
    }
}
