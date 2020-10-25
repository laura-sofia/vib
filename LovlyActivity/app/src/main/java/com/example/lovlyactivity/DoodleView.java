package com.example.lovlyactivity;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class DoodleView extends View {


    private DrawingClass drawing;

    public DoodleView(Context context) {
        super(context);
        init(null,0);
    }
    public DoodleView(Context context, AttributeSet attributeSet){
        super(context,attributeSet);
        init(attributeSet,0);
    }
    public DoodleView(Context context,AttributeSet attributeSet,int defStyle){
        super(context,attributeSet,defStyle);
        init(attributeSet,defStyle);
    }
    private void init(AttributeSet attrs, int defStyle){

        drawing=new DrawingClass();
    }
    @Override
    public void onDraw(Canvas canvas){
        super.onDraw(canvas);

        drawing.drawSquare(canvas,getWidth(),getHeight());
    }
    @Override
    public boolean onTouchEvent(MotionEvent event){

        float touchX=event.getX();
        float touchY=event.getY();

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:

                drawing.update(touchX,touchY,getHeight(),getWidth());

                break;

            case MotionEvent.ACTION_UP:
                break;
        }
        invalidate();
        return true;

    }

}
