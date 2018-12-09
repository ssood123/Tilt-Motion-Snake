package rr.modernsnake;

//Import files needed to run the program
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;

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

    private int index;
    private int index2;

    private GameEngineTwo gameEngineTwo; // Creates the GameEngineTwo object
    private SnakeView snakeView; // Starts making the game map
    private final Handler handler = new Handler();
    private final long updateDelay = 200; // Determines the speed of the snake

    public static String scoreMessage = ""; // Blank String

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



        // Starts the game engine
        gameEngineTwo = new GameEngineTwo(); // Creates the second engine
        gameEngineTwo.initGame(); // Creates the objects
        snakeView = (SnakeView) findViewById(R.id.snakeView); // Creates the map
        startUpdateHandler(); // Start updating
    }

    private void startUpdateHandler(){
        handler.postDelayed(new Runnable(){
            @Override
            public void run(){
                gameEngineTwo.Update();

                if(gameEngineTwo.getCurrentGameState() == GameState.Running) {
                    if (index == 0) {
                        gameEngineTwo.UpdateDirection(North);
                    }
                    if (index == 1) {
                        gameEngineTwo.UpdateDirection(East);
                    }
                    if (index == 2) {
                        gameEngineTwo.UpdateDirection(South);
                    }
                    if (index == 3) {
                        gameEngineTwo.UpdateDirection(West);
                    }
                    if (index2 == 0) {
                        gameEngineTwo.UpdateDirection(North);
                    }
                    if (index2 == 1) {
                        gameEngineTwo.UpdateDirection(East);
                    }
                    if (index2 == 2) {
                        gameEngineTwo.UpdateDirection(South);
                    }
                    if (index2 == 3) {
                        gameEngineTwo.UpdateDirection(West);
                    }

                    handler.postDelayed(this, updateDelay);
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

                snakeView.setSnakeViewMap(gameEngineTwo.getMap());
                snakeView.invalidate();
            }
        }, updateDelay);

    }
    private void OnGameLost(int player){ // What happens when you lose
        if(player == 1) { // Player one lost
            scoreMessage = "Player two won with " + String.valueOf(gameEngineTwo.score) + " points.";
        }
        else if(player == 2){ // Player two lost
            scoreMessage = "Player one won with " + String.valueOf(gameEngineTwo.score2) + " points.";
        }
        else if(player == 0){
            scoreMessage = "It's a draw.";
        }
        Intent endActivityTwo = new Intent(TwoActivity.this, EndActivityTwo.class); // Creates object intent to launch a new activity
        startActivity(endActivityTwo); // Launches the new activity
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
                index=2;
                break;
            }
            case R.id.right: {
                index=3;
                break;
            }
            case R.id.down: {
                index=0;
                break;
            }
            case R.id.left: {
                index=1;
                break;
            }

            // Directions for the second snake
            case R.id.up2: {
                index2=0;
                break;
            }
            case R.id.right2: {
                index2=1;
                break;
            }
            case R.id.down2: {
                index2=2;
                break;
            }
            case R.id.left2: {
                index=3;
                break;
            }
        }
    }
}