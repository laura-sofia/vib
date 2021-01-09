package com.example.lovlyactivity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class BoardActivity extends AppCompatActivity implements OnWonListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BoardView view = findViewById(R.id.boardView);
        view.setOnWonListener(this);
    }


    public void onWon(Checker ch) {
        if (ch != Checker.NOCHECKER) {
            Intent intent = new Intent(this, Win.class);
            intent.putExtra("WON", Checker.checkerToString(ch));
            startActivity(intent);
        }
    }
}