package com.example.vrv1a;

import android.content.Context;
import android.graphics.Canvas;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.GestureDetector.OnContextClickListener;

import androidx.core.view.GestureDetectorCompat;

public class MainThread extends Thread implements GestureDetector.OnGestureListener {

    private SurfaceHolder surfaceHolder;
    private GestureDetectorCompat mDetector;
    private GameView gameView;
    private boolean running;
    public static Canvas canvas;

    public MainThread(SurfaceHolder holder, GameView gameView, Context context) {
        super();
        this.surfaceHolder=holder;
        this.gameView = gameView;
        mDetector = new GestureDetectorCompat(context,this);
        

    }

    @Override
    public boolean onDown(MotionEvent motionEvent) {

        float x=motionEvent.getX();
        float y=motionEvent.getY();

        gameView.update(x,y);


        return true;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        return false;
    }
    @Override
    public void run(){

        canvas=null;
        if (running) {
            try {
                canvas = this.surfaceHolder.lockCanvas();
                synchronized (surfaceHolder) {

                    this.gameView.draw(canvas);
                }
            } catch (Exception e) {

            } finally {
                if (canvas != null) {
                    try {
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }
        }

    }

    public void setRunning(boolean b) { running=b;
    }
}
