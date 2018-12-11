package rr.modernsnake;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.widget.TextView;


public class EndActivityTwo extends AppCompatActivity implements OnClickListener {

    private Button backtostart; // Declares a button for the start screen
    private Button backtogame; // Declares a button to restart the game
    private TextView scoreScreen; // Text object that says your score

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Creates this activity
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        scoreScreen = (TextView) findViewById(R.id.Score);
        scoreScreen.setText(TwoActivity.getScore());
        if(TwoActivity.color == "black")
            scoreScreen.setTextColor(Color.BLACK);
        else if(TwoActivity.color == "blue")
            scoreScreen.setTextColor((Color.BLUE));
        else if(TwoActivity.color == "red")
            scoreScreen.setTextColor(Color.RED);
        //Creates a button that leads to MainActivity (the start screen)
        backtostart = (Button) findViewById(R.id.backToMain);
        backtostart.setOnClickListener(this);

        //Creates a button that leads to gameActivity (the game screen)
        backtogame = (Button) findViewById(R.id.backToGame);
        backtogame.setOnClickListener(this);

    }

    @Override
    public void onClick(View v)
    {
        switch(v.getId()) // Gets the id of the button pressed
        {
            case R.id.backToMain : { // If backToMain button is pressed
                launchMain();
                break;
            }
            case R.id.backToGame : { // If backToGame button is pressed
                launchGame();
                break;
            }


        }
    }

    private void launchGame() // Launches the game activity
    {
        Intent twoActivity = new Intent(EndActivityTwo.this, TwoActivity.class); // Creates object intent to launch a new activity
        startActivity(twoActivity); // Launches the new activity
        finish();
    }

    private void launchMain() // Launches the main activity (intro)
    {
        Intent introActivity = new Intent(EndActivityTwo.this, IntroActivity.class); // Creates object intent to launch a new activity
        startActivity(introActivity); // Launches the new activity
        finish();
    }
}
