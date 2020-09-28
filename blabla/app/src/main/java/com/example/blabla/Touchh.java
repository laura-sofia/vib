package com.example.blabla;

import android.content.Context;
import android.view.SurfaceHolder;
import android.view.View;

public class Touchh implements View.OnClickListener {

    MainThread mainThread;
    public Touchh( SurfaceHolder surfaceHolder,MainThread mainThread) {
        onClick((View) surfaceHolder);
        this.mainThread=mainThread;
    }

    @Override
    public void onClick(View view) {

        mainThread.onClick();

    }
}
