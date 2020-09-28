package com.example.blabla;

import android.graphics.Canvas;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

public class MainThread extends Thread {

    private SurfaceHolder surfaceHolder;
    private GameView gameView;
    private boolean running;
    public static Canvas canvas;
    private SurfaceView view;
    private Touchh touchh;

    private TouchEvent touchEvent;

    public MainThread(SurfaceHolder surfaceHolder, GameView gameView){
        super();
        this.gameView=gameView;
        this.surfaceHolder=surfaceHolder;



    }
    public void setRunning(boolean isRunning) {
        running = isRunning;

    }
    @Override
    public void run() {
        if (running) {
            canvas = null;

            try {
                canvas = this.surfaceHolder.lockCanvas();
                synchronized(surfaceHolder) {
                    this.gameView.update();
                    this.gameView.draw(canvas);
                }
            } catch (Exception e) {} finally {
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


    public void onClick() {
        canvas = null;

        try {
            canvas = this.surfaceHolder.lockCanvas();
            synchronized(surfaceHolder) {
                this.gameView.update();
                this.gameView.draw(canvas);
            }
        } catch (Exception e) {} finally {
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
