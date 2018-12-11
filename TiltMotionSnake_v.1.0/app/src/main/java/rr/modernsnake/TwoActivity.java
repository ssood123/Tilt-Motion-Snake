package rr.modernsnake;

//Import files needed to run the program
import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

import rr.modernsnake.engine.GameEngine;
import rr.modernsnake.engine.GameEngineTwo;
import rr.modernsnake.enums.GameState;
import rr.modernsnake.views.SnakeView;

import static rr.modernsnake.enums.Direction.East;
import static rr.modernsnake.enums.Direction.North;
import static rr.modernsnake.enums.Direction.South;
import static rr.modernsnake.enums.Direction.West;

public class TwoActivity extends AppCompatActivity implements OnClickListener{

    private Button upOne;
    private Button upTwo;
    private Button rightOne;
    private Button rightTwo;
    private Button downOne;
    private Button downTwo;
    private Button leftOne;
    private Button leftTwo;
    private TextView time;
    private TextView announcement2;

    private int messageCounter;

    private GameEngineTwo gameEngineTwo; // Creates the GameEngineTwo object
    private SnakeView snakeView; // Starts making the game map
    private final Handler handler = new Handler();
    private final long updateDelay = 150; // Determines the speed of the snake

    public static String scoreMessage = ""; // Blank String
    public static String color = "black"; // Declares a string to change the color of the end screen text

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);
        //Declares the button needed to pay the game
        upOne= (Button) findViewById(R.id.up); // Creates the button to move up
        upOne.setOnClickListener(this); //On click listen function used
        upTwo= (Button) findViewById(R.id.up2); // Creates the button to move up
        upTwo.setOnClickListener(this); //On click listen function used
        rightOne= (Button) findViewById(R.id.right); // Creates the button to move up
        rightOne.setOnClickListener(this); //On click listen function used
        rightTwo= (Button) findViewById(R.id.right2); // Creates the button to move up
        rightTwo.setOnClickListener(this); //On click listen function used
        downOne= (Button) findViewById(R.id.down); // Creates the button to move up
        downOne.setOnClickListener(this); //On click listen function used
        downTwo= (Button) findViewById(R.id.down2); // Creates the button to move up
        downTwo.setOnClickListener(this); //On click listen function used
        leftOne= (Button) findViewById(R.id.left); // Creates the button to move up
        leftOne.setOnClickListener(this); //On click listen function used
        leftTwo= (Button) findViewById(R.id.left2); // Creates the button to move up
        leftTwo.setOnClickListener(this); //On click listen function used
        time= (TextView) findViewById(R.id.time); // Creates the box for the timer
        announcement2 = (TextView) findViewById(R.id.begin2);

        // Starts the game engine
        gameEngineTwo = new GameEngineTwo();
        gameEngineTwo.initGame();
        snakeView = (SnakeView) findViewById(R.id.snakeView);
        CountDownTimer countDownTimer = new CountDownTimer(60000, 1000) {
            public void onTick(long millisUntilFinished) {
                time.setText(String.valueOf(millisUntilFinished/1000));
            }
            public void onFinish() {
                gameEngineTwo.setCurrentGameState(GameState.Over);
            }
        }.start();
        startUpdateHandler();
    }


    private void startUpdateHandler(){
            handler.postDelayed(new Runnable(){
            @Override
            public void run(){
                gameEngineTwo.Update();

                if(gameEngineTwo.getCurrentGameState() == GameState.Running){
                    handler.postDelayed(this, updateDelay );
                }

                if(gameEngineTwo.getCurrentGameState() == GameState.Lost){
                    OnGameLost(1);
                }
                if(gameEngineTwo.getCurrentGameState() == GameState.Lost2){
                    OnGameLost(2);
                }
                if(gameEngineTwo.getCurrentGameState() == GameState.Draw){
                    OnGameLost(0);
                }
                if(gameEngineTwo.getCurrentGameState() == GameState.Over){
                    OnGameLost(-1); // Time runs out
                }
                snakeView.setSnakeViewMap(gameEngineTwo.getMap());
                snakeView.invalidate();
                if(messageCounter == 0){
                    announcement2.setVisibility(View.VISIBLE);
                    messageCounter++;
                }
                else if(messageCounter == 15){
                    announcement2.setVisibility(View.INVISIBLE);
                }
                else if(messageCounter >= 1 && messageCounter < 15)
                {
                    messageCounter++;
                }
            }
        }, updateDelay);

    }
    private void OnGameLost(int player){ // What happens when you lose
        if(player == 1) { // Player one lost
            scoreMessage = "\t\t\t\t\t\tblue snake won \n\t\t\t\t\t\t\t\t\t\t\t\t\t\twith\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t" + String.valueOf(gameEngineTwo.score2) + "\n\t\t\t\t\t\t\t\t\t\t\t\t\tpoints";
            color = "blue";

        }
        else if(player == 2){ // Player two lost
            scoreMessage = "\t\t\t\t\t\t\tred snake won \n\t\t\t\t\t\t\t\t\t\t\t\t\t\twith \n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t" + String.valueOf(gameEngineTwo.score) + " \n\t\t\t\t\t\t\t\t\t\t\t\t\tpoints";
            color = "red";
        }
        else if(player == 0){
            scoreMessage = "\t\t\t\t\t\t\t\t\tit's a draw";
            color = "black";
        }
        else if(player == -1)
        {
            if(gameEngineTwo.score > gameEngineTwo.score2) {
                scoreMessage = "\t\t\t\t\t\t\tred snake won \n\t\t\t\t\t\t\t\t\t\t\t\t\t\twith \n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t" + String.valueOf(gameEngineTwo.score) + " \n\t\t\t\t\t\t\t\t\t\t\t\t\tpoints";
                color = "red";
            }
            else if(gameEngineTwo.score2 > gameEngineTwo.score) {
                scoreMessage = "\t\t\t\t\t\tblue snake won \n\t\t\t\t\t\t\t\t\t\t\t\t\t\twith\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t" + String.valueOf(gameEngineTwo.score2) + "\n\t\t\t\t\t\t\t\t\t\t\t\t\tpoints";
                color = "blue";
            }
            else if(gameEngineTwo.score == gameEngineTwo.score2) {
                scoreMessage = "\t\t\t\t\t\t\t\t\tit's a draw";
                color = "black";
            }
        }
        Intent endActivityTwo = new Intent(TwoActivity.this, EndActivityTwo.class); // Creates object intent to launch a new activity
        startActivity(endActivityTwo); // Launches the new activity
        //LostSound.release();
        finish();

    }

    public static String getScore() // Get's the end of game message for other activities to use
    {
        return scoreMessage;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) // Gets the id of the button pressed
        {
            case R.id.up: {
                gameEngineTwo.UpdateDirection(South);
                break;
            }
            case R.id.right: {
                gameEngineTwo.UpdateDirection(West);
                break;
            }
            case R.id.down: {
                gameEngineTwo.UpdateDirection(North);
                break;
            }
            case R.id.left: {
                gameEngineTwo.UpdateDirection(East);
                break;
            }

            // Directions for the second snake
            case R.id.up2: {
                gameEngineTwo.UpdateDirection2(North);
                break;
            }
            case R.id.right2: {
                gameEngineTwo.UpdateDirection2(East);
                break;
            }
            case R.id.down2: {
                gameEngineTwo.UpdateDirection2(South);
                break;
            }
            case R.id.left2: {
                gameEngineTwo.UpdateDirection2(West);
                break;
            }
        }
    }
}