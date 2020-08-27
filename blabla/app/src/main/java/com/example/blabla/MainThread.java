package com.example.blabla;

import android.graphics.Canvas;
import android.view.SurfaceHolder;
import android.view.View;

public class MainThread extends Thread  {

 //   private SurfaceHolder surfaceHolder;
    private GameView gameView;
    private boolean running;
    public static Canvas canvas;
    private View view;

    private TouchEvent touchEvent;

    public MainThread(View view, GameView gameView){
        super();
        this.gameView=gameView;
        this.view=view;
      //  this.surfaceHolder=surfaceHolder;

    }
  /*  public void setRunning(boolean isRunning) {
        running = isRunning;
    }
    @Override
    public void run() {
        if (running) {
            canvas = null;

            try {
                canvas = ();
                synchronized(surfaceHolder) {
                  //  this.gameView.update();
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
        while(gameView.onClick()){

        }
    }*/
}
