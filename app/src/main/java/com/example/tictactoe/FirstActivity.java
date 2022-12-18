package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class FirstActivity extends AppCompatActivity {
    Button singlePlayer , multiPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        singlePlayer = findViewById(R.id.single);
        multiPlayer = findViewById(R.id.multi);
        SharedPreferences sharedPreferences = getSharedPreferences("mode",MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();

        singlePlayer.setOnClickListener(v -> {
           edit.putInt("mode",1);
           edit.apply();
           startActivity(new Intent(this,MainActivity.class));
        });
        multiPlayer.setOnClickListener(v -> {
            edit.putInt("mode",0);
            edit.apply();
            startActivity(new Intent(this,MainActivity.class));
        });

    }
}