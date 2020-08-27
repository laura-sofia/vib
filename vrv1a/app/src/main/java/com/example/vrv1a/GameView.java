package com.example.vrv1a;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;


import androidx.annotation.NonNull;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {

    private MainThread thread;
    private CharacterSprite characterSprite;

    public GameView(Context context){

        super(context);

        getHolder().addCallback(this);

        thread = new MainThread(getHolder(), this, context);
        setFocusable(true);

    }


    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
        characterSprite = new CharacterSprite();

        thread.setRunning(true);
        thread.start();

    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {

        boolean retry = true;
        while(retry){
            try{
                thread.setRunning(false);
                thread.join();

            }catch (Exception e) {e.printStackTrace();}
            retry=false;
        }

    }

    public void update(float x, float y) {

        characterSprite.update(x,y);
    }

    @Override
    public void draw(Canvas canvas){
        super.draw(canvas);
        if (canvas!=null){
            characterSprite.draw(canvas);
        }
    }
}
