package com.example.lovlyactivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Win extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_win);

        Intent intent = getIntent();
        String whoWon = "The " + intent.getStringExtra("WON") + " checker won!";

        TextView textView = findViewById(R.id.textView);
        textView.setText(whoWon);
    }

    public void goToMenu(View view) {
        Intent intent = new Intent(this, Menu.class);
        startActivity(intent);
    }

    public void Restart(View view) {
        Intent intent = new Intent(this, BoardActivity.class);
        startActivity(intent);
    }
}