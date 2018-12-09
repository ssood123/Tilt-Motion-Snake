package rr.modernsnake;

//Import files needed to run the program
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import rr.modernsnake.engine.GameEngine;
import rr.modernsnake.enums.GameState;
import rr.modernsnake.views.SnakeView;

import static rr.modernsnake.enums.Direction.East;
import static rr.modernsnake.enums.Direction.North;
import static rr.modernsnake.enums.Direction.South;
import static rr.modernsnake.enums.Direction.West;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener{


    private GameEngine gameEngine;
    private SnakeView snakeView;
    private final Handler handler = new Handler();
    private final long updateDelay = 500; // Determines the speed of the snake

    private float prevX, prevY;
    private SensorManager sensorManager;
    private Sensor gyroscopeSensor;
    private SensorEventListener gyroscopeEventListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        gyroscopeSensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);

        if(gyroscopeSensor == null){
            Toast.makeText(this,"The device has no gyroscope sensor", Toast.LENGTH_SHORT).show();

        }
        gyroscopeEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                if(sensorEvent.values[0] > .75f){
                    gameEngine.UpdateDirection(South);
                }
                else if(sensorEvent.values[0] < -.75f ){
                    gameEngine.UpdateDirection(North);
                }
                else if(sensorEvent.values[1] > .75){
                    gameEngine.UpdateDirection(East);
                }
                else if(sensorEvent.values[1] < -.75){
                    gameEngine.UpdateDirection((West));
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };


        gameEngine = new GameEngine();
        gameEngine.initGame();
        snakeView = (SnakeView)findViewById(R.id.snakeView);
        startUpdateHandler();
    }

    @Override
    protected void onResume(){
        super.onResume();
        sensorManager.registerListener(gyroscopeEventListener, gyroscopeSensor, SensorManager.SENSOR_DELAY_FASTEST);
    }
    @Override
    protected void onPause(){
        super.onPause();
        sensorManager.unregisterListener(gyroscopeEventListener);
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
        Toast.makeText(this, "You Lost.", Toast.LENGTH_SHORT).show(); // Shows the lost message

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) { // What occurs when the screen is touch
        return true;
    }
}