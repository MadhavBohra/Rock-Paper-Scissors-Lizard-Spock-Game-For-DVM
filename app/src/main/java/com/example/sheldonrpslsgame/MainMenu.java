package com.example.sheldonrpslsgame;

import static com.example.sheldonrpslsgame.MainActivity.muted;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainMenu extends AppCompatActivity {


    private AppCompatButton PlayButton;
    private AppCompatButton ExitButton;
    private AppCompatButton Instructions;
    public ImageView ivAudio;
    MediaPlayer mainMenuPlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

       mainMenuPlayer = MediaPlayer.create(this,R.raw.main_menu_audio);



        PlayButton = (AppCompatButton) findViewById(R.id.bt_start);
        ExitButton = (AppCompatButton) findViewById(R.id.bt_main_exit);
        Instructions = (AppCompatButton) findViewById(R.id.bt_instructions);
        ivAudio = (ImageView) findViewById(R.id.iv_audio);

        PlayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainMenu.this,HomeActivity.class);

                mainMenuPlayer.stop();
                startActivity(intent);
                finish();

            }
        });

        Instructions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainMenu.this,TutorialVideo.class);

                mainMenuPlayer.stop();
                startActivity(intent);
                finish();
            }
        });

        ExitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finishAffinity();
                System.exit(0);
            }
        });



        mainMenuPlayer.start();
        if(muted){
            //change icon to muted
            ivAudio.setImageResource(R.drawable.ic_baseline_volume_off_24);
            //mute the audio
            mainMenuPlayer.pause();
        }
        else if(!muted){
            //change icon to unmuted
            ivAudio.setImageResource(R.drawable.ic_baseline_volume_up_24);
            //play the audio
            mainMenuPlayer.start();
            mainMenuPlayer.setLooping(true);
        }

        ivAudio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(muted){
                    //change image to unmuted
                    ivAudio.setImageResource(R.drawable.ic_baseline_volume_up_24);
                    //unpause audio
                    mainMenuPlayer.start();
                    mainMenuPlayer.setLooping(true);

                    muted = false;
                }else {
                    muted = true;
                    //change image to muted
                    ivAudio.setImageResource(R.drawable.ic_baseline_volume_off_24);
                    //pause audio
                    mainMenuPlayer.pause();

                }
            }
        });


    }

    @Override
    protected void onPause(){
        super.onPause();
        mainMenuPlayer.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // play your music here.
        mainMenuPlayer.start();
        mainMenuPlayer.setLooping(true);
    }

    @Override
    public void onBackPressed(){
        finishAffinity();
        System.exit(0);
    }
}