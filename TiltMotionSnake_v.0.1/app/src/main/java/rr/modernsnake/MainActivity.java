package rr.modernsnake;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import rr.modernsnake.engine.GameEngine;
import rr.modernsnake.enums.GameState;
import rr.modernsnake.views.SnakeView;

public class MainActivity extends AppCompatActivity {


    private GameEngine gameEngine;
    private SnakeView snakeView;
    private final Handler handler = new Handler();
    private final long updateDelay = 125;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        gameEngine = new GameEngine();
        gameEngine.initGame();
        snakeView = (SnakeView)findViewById(R.id.snakeView);
        startUpdateHandler();
    }

    private void startUpdateHandler(){
        handler.postDelayed(new Runnable(){
            @Override
            public void run(){
                gameEngine.Update();

                if(gameEngine.getCurrentGameState() == GameState.Running){
                    handler.postDelayed(this, updateDelay );
                }
                if(gameEngine.getCurrentGameState() == GameState.Lost){
                    OnGameLost();
                }
                snakeView.setSnakeViewMap(gameEngine.getMap());
                snakeView.invalidate();
            }
        }, updateDelay);

    }
    private void OnGameLost(){ // What happens when you lose
        Toast.makeText(this, "You Lost.", Toast.LENGTH_SHORT).show();
    }
}
