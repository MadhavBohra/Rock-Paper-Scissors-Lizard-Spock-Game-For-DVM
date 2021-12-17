package com.example.sheldonrpslsgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;

import android.os.Bundle;
import android.os.Handler;


public class MainActivity extends AppCompatActivity {
    public static boolean muted = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Song playing in the background


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this,MainMenu.class);
                startActivity(intent);
                finish();
            }
        },4000);
    }
}