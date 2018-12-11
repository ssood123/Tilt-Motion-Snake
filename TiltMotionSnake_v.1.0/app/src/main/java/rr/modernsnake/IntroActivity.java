package rr.modernsnake;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;


public class IntroActivity extends AppCompatActivity implements OnClickListener {
    private Button gameOne; // Creates a button object for game one
    private Button gameTwo; // Creates a button object for game two

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Creates this activity (essentially a background rectangle that you can
        //put stuff on)
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        gameOne = (Button) findViewById(R.id.buttonToGameActivity); // Creates the button connection with xml for game one
        gameOne.setOnClickListener(this); //On click listen function used
        gameTwo = (Button) findViewById(R.id.game2); // Creates the button connection with xml for game two
        gameTwo.setOnClickListener(this); //On click listen function used

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) // Gets the id of the button pressed
        {
            case R.id.buttonToGameActivity: {
                launchGameOne();
                break;
            }
            case R.id.game2: {
                launchGameTwo();
                break;
            }
        }
    }

    private void launchGameOne()
    {
        Intent oneActivity = new Intent(IntroActivity.this, OneActivity.class); // Creates object intent to launch a new activity (game one)
        startActivity(oneActivity); // Launches the new activity
        finish();
    }

    private void launchGameTwo()
    {
        Intent twoActivity = new Intent(IntroActivity.this, TwoActivity.class); // Creates object intent to launch a new activity (game two)
        startActivity(twoActivity); // Launches the new activity
        finish();
    }
}
