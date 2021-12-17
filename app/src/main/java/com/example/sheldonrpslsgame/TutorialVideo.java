package com.example.sheldonrpslsgame;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class TutorialVideo extends AppCompatActivity {
    VideoView tutorial;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial_video);
        tutorial = findViewById(R.id.vv_tutorial);

        String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.tutorial;
        Uri uri = Uri.parse(videoPath);
        tutorial.setVideoURI(uri);
        tutorial.start();

        tutorial.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mediaPlayer.setLooping(true);
            }
        });
        MediaController mediaController = new MediaController(this);
        tutorial.setMediaController(mediaController);
        mediaController.setAnchorView(tutorial);

    }

    public void onBackPressed(){
        Intent intent = new Intent(TutorialVideo.this,MainMenu.class);
        tutorial.stopPlayback();
        startActivity(intent);
        finish();
    }
    @Override
    protected void onPause(){
        super.onPause();
        tutorial.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // play your music here.
        tutorial.start();

    }


}