package com.example.blabla;

import android.app.Activity;
import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.SurfaceHolder;

import androidx.core.view.GestureDetectorCompat;

public class TouchEvent extends Activity implements GestureDetector.OnGestureListener{

    private SurfaceHolder surfaceHolder;
    private Context context;
    private GestureDetectorCompat mDetector;

    public TouchEvent(Context context,SurfaceHolder surfaceHolder){

        mDetector = new GestureDetectorCompat(context, this);


    }



    @Override
    public boolean onTouchEvent(MotionEvent event){
        if (this.mDetector.onTouchEvent(event)) {
            return true;
        }
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onDown(MotionEvent event) {

        System.out.println("onDownOccured"+event.getX());

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


}
