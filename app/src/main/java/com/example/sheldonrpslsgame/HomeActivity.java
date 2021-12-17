package com.example.sheldonrpslsgame;

import static com.example.sheldonrpslsgame.MainActivity.muted;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;



public class HomeActivity extends AppCompatActivity{

    // Declaring Buttons and Variables
    private ImageButton stoneButton,paperButton,scissorsButton,lizardButton,spockButton;
    public TextView tvPlayer,tvComputer,tvMessageDialog,tvTitleDialog;
    public String result;
    public String fighting;
    private TextView textView;
    AppCompatButton msgClose;
    public ImageView iv_ComputerChoice,iv_HumanChoice;
    Dialog dialog;
    Dialog gameOverDialog;
    public int humanscore = 0 ;
    public int computerscore = 0;
    private ImageView ivAudio;
    private MediaPlayer gameaudioPlayer;


    public String user_choice;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        ivAudio = (ImageView) findViewById(R.id.iv_game_audio);
        gameaudioPlayer =  MediaPlayer.create(this,R.raw.game_menu_audio);
        gameaudioPlayer.start();
        if(muted){
            //change icon to muted
            ivAudio.setImageResource(R.drawable.volumeoff);
            //mute the audio
            gameaudioPlayer.pause();
        }
        else if(!muted){
            //change icon to unmuted
            ivAudio.setImageResource(R.drawable.voulmeon);
            //play the audio
            gameaudioPlayer.start();
            gameaudioPlayer.setLooping(true);
        }

        ivAudio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(muted){
                    //change image to unmuted
                    ivAudio.setImageResource(R.drawable.voulmeon);
                    //unpause audio
                    gameaudioPlayer.start();
                    gameaudioPlayer.setLooping(true);

                    muted = false;
                }else if(!muted){
                    muted = true;
                    //change image to muted
                    ivAudio.setImageResource(R.drawable.volumeoff);
                    //pause audio
                    gameaudioPlayer.pause();

                }
            }
        });



        // Linking Variables with their ids


        stoneButton = (ImageButton) findViewById(R.id.b_stone);
        paperButton = (ImageButton) findViewById(R.id.b_paper);
        scissorsButton = (ImageButton) findViewById(R.id.b_scissors);
        lizardButton = (ImageButton) findViewById(R.id.b_lizard);
        spockButton = (ImageButton) findViewById(R.id.b_spock);
        iv_ComputerChoice = (ImageView) findViewById(R.id.iv_computer_choice);
        iv_HumanChoice = (ImageView) findViewById(R.id.iv_human_choice);
        tvPlayer = (TextView) findViewById(R.id.tv_player);
        tvComputer = (TextView) findViewById(R.id.tv_computer);


        textView = findViewById(R.id.textView);
        stoneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                play_turn(1);
                iv_HumanChoice.setImageResource(R.drawable.stone);
            }
        });
        paperButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                play_turn(2);
                iv_HumanChoice.setImageResource(R.drawable.paper);
            }
        });
        scissorsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                play_turn(3);
                iv_HumanChoice.setImageResource(R.drawable.scissors);
            }
        });
        lizardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                play_turn(4);
                iv_HumanChoice.setImageResource(R.drawable.lizard);

            }
        });
        spockButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                play_turn(5);
                iv_HumanChoice.setImageResource(R.drawable.spock);

            }
        });






    }

    @Override
    public void onBackPressed(){
        Intent intent = new Intent(HomeActivity.this,MainMenu.class);

        gameaudioPlayer.stop();
        startActivity(intent);
        finish();
    }
    @Override
    protected void onPause(){
        super.onPause();
        gameaudioPlayer.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // play your music here.
        gameaudioPlayer.start();
        gameaudioPlayer.setLooping(true);
    }

    public void play_turn(int buttonid)
    {
        String computer_choice = "";
        Random r = new Random();
        // any number from 1(rock) 2(paper) 3(scissors) 4(lizard) or 5(spock)
        int computer_choice_number = r.nextInt(5) + 1;
        System.out.println(computer_choice_number);

        //Declaring Choices Drawables
        int choices[] =new int[]{R.drawable.stone,R.drawable.paper,R.drawable.scissors,R.drawable.lizard,R.drawable.spock};


        // Change the Image for the AI
        iv_ComputerChoice.setImageResource(choices[computer_choice_number-1]);



        // The Code of Selection and Deciding a Winner and sending a message
        if (computer_choice_number != buttonid){
            if(buttonid == 1){
                switch (computer_choice_number){
                    case 2:
                        // Paper Covers Rock YOU LOOSE
                        result = "YOU LOOSE";
                        fighting = "Paper Covers Rock";
                        break;
                    case 3:
                        // Rock Crushes Scissors YOU WIN
                        result = "YOU WIN";
                        fighting = "Rock Crushes Scissors";
                        break;
                    case 4:
                        // Rock Crushes Lizard YOU WIN
                        result = "YOU WIN";
                        fighting = "Rock Crushes Lizard";
                        break;
                    case 5:
                        // Spock Vaporize Rock YOU LOOSE
                        result = "YOU LOOSE";
                        fighting = "Spock Vaporize Rock";
                        break;
                }
            }
            else if(buttonid == 2) {
                    switch (computer_choice_number) {
                        case 1:
                            result = "YOU WIN";
                            fighting = "Paper Covers Rock";
                            // Paper Covers Rock YOU WIN
                            break;
                        case 3:
                            // Scissors cuts Paper YOU LOOSE
                            result = "YOU LOOSE";
                            fighting = "Scissors cuts Paper";
                            break;
                        case 4:
                            // Lizard eats Paper YOU LOOSE
                            result = "YOU LOOSE";
                            fighting = "Lizard eats Paper";
                            break;
                        case 5:
                            // Paper Disproves Spock YOU WIN
                            result = "YOU WIN";
                            fighting = "Paper Disproves Spock";
                            break;
                    }
                }
                else if(buttonid == 3){
                        switch (computer_choice_number){
                            case 1:
                                // Rock Crushes Scissors YOU LOOSE
                                result = "YOU LOOSE";
                                fighting = "Rock Crushes Scissors";
                                break;
                            case 2:
                                // Scissors cuts Paper YOU WIN
                                result = "YOU WIN";
                                fighting = "Scissors cuts Paper";
                                break;
                            case 4:
                                // Scissors decapitates Lizard YOU WIN
                                result = "YOU WIN";
                                fighting = "Scissors decapitates Lizard";
                                break;
                            case 5:
                                // Spock Crushes Scissors YOU LOOSE
                                result = "YOU LOOSE";
                                fighting = "Spock Crushes Scissors ";
                                break;
                        }
                    }
            else if(buttonid == 4){
                switch (computer_choice_number){
                    case 1:
                        // Rock Crushes Lizard YOU LOOSE
                        result = "YOU LOOSE";
                        fighting = "Rock Crushes Lizard";
                        break;
                    case 2:
                        // Lizard eats Paper YOU WIN
                        result = "YOU WIN";
                        fighting = "Lizard eats Paper";
                        break;
                    case 3:
                        // Scissors decapitates Lizard YOU LOOSE
                        result = "YOU LOOSE";
                        fighting = "Scissors decapitates Lizard";
                        break;
                    case 5:
                        // Lizard Poisons Spock YOU WIN
                        result = "YOU WIN";
                        fighting = "Lizard Poisons Spock";
                        break;
                }
            }
            else if(buttonid == 5){
                switch (computer_choice_number){
                    case 1:
                        // Spock Vaporize Rock YOU WIN
                        result = "YOU WIN";
                        fighting = "Spock Vaporize Rock";
                        break;
                    case 2:
                        // Paper Disproves Spock YOU LOOSE
                        result = "YOU LOOSE";
                        fighting = "Paper Disproves Spock";
                        break;
                    case 3:
                        // Spock Crushes Scissors YOU WIN
                        result = "YOU WIN";
                        fighting = "Spock Crushes Scissors";
                        break;
                    case 4:
                        // Lizard Poisons Spock YOU LOOSE
                        result = "YOU LOOSE";
                        fighting = "Lizard Poisons Spock ";
                        break;
                }
            }

        }
        else {
            //ITS A DRAW
            result = "TIE";
            fighting = "What are the Ods? Lol";
        }


        // Call the Dialog box
        openDialog(result,fighting);







    }

    public void openDialog(String title, String message){
         //  DialogBox resultDialog = new DialogBox();
          // resultDialog.DialogOpener(title,message);
        //Toast.makeText(HomeActivity.this,title + "\n" + message,Toast.LENGTH_LONG).show();

           //resultDialog.show(getSupportFragmentManager(),"Dialog");


        //Scoring
        if(title == "YOU WIN"){
            humanscore = Integer.parseInt(tvPlayer.getText().toString()) + 1;
            tvPlayer.setText(Integer.toString(humanscore));
        }
        else if(title == "YOU LOOSE")
        {
            computerscore = Integer.parseInt(tvComputer.getText().toString()) + 1;
            tvComputer.setText(Integer.toString(computerscore));
        }
        if(humanscore >= 5 || computerscore >= 5){
                if(humanscore >=5){
                    //Game Over You Win at The End Pop Up
                    gameOverDialog = new Dialog(HomeActivity.this);
                    gameOverDialog.setContentView(R.layout.game_over_dialog_box);
                    gameOverDialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    gameOverDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
                    gameOverDialog.setCancelable(false);
                    AppCompatButton restartButton = (AppCompatButton) gameOverDialog.findViewById(R.id.bt_restart_dialog);
                    AppCompatButton exitButton = (AppCompatButton) gameOverDialog.findViewById(R.id.bt_exit_dialog);

                    // Message of Winning
                    TextView finalResult = (TextView) gameOverDialog.findViewById(R.id.tv_final_result);
                    finalResult.setText("YOU WIN!!! ");


                    //Restart
                    restartButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            reload();
                        }
                    });


                    //Exit

                    exitButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            finishAffinity();
                            System.exit(0);
                        }
                    });

                    gameOverDialog.show();
                }
                else {
                    gameOverDialog = new Dialog(HomeActivity.this);
                    gameOverDialog.setContentView(R.layout.game_over_dialog_box);
                    gameOverDialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    gameOverDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
                    gameOverDialog.setCancelable(false);


                    // Geting Dialog Box Views
                    AppCompatButton restartButton = (AppCompatButton) gameOverDialog.findViewById(R.id.bt_restart_dialog);
                    AppCompatButton exitButton = (AppCompatButton) gameOverDialog.findViewById(R.id.bt_exit_dialog);




                    // Message of Loosing
                    TextView finalResult = (TextView) gameOverDialog.findViewById(R.id.tv_final_result);
                    finalResult.setText("YOU LOOSE!!! ");


                    //Restart
                    restartButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            reload();
                        }
                    });


                    //Exit

                    exitButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            finishAffinity();
                            System.exit(0);
                        }
                    });

                    gameOverDialog.show();
                }
        }
        else {
            // Dialog box custom
            dialog = new Dialog(HomeActivity.this);
            dialog.setContentView(R.layout.dialog_box);
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
            dialog.setCancelable(false);
            msgClose = (AppCompatButton) dialog.findViewById(R.id.bt_message_close);
            tvMessageDialog = (TextView) dialog.findViewById(R.id.tv_dialog_message);
            tvTitleDialog = (TextView) dialog.findViewById(R.id.tv_dialog_title);
            tvTitleDialog.setText(title);
            tvMessageDialog.setText(message);
            msgClose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                }
            });
            dialog.show();

        }

    }


    public void reload(){
        Activity activity = HomeActivity.this;
        gameaudioPlayer.stop();
        activity.finish();
        activity.startActivity(activity.getIntent());

    }
}