package rr.modernsnake;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;


public class IntroActivity extends AppCompatActivity implements OnClickListener {
    private Button gameOne; // Creates a button object

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Creates this activity (essentially a background rectangle that you can
        //put stuff on)
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        gameOne = (Button) findViewById(R.id.buttonToGameActivity); // Creates the button connection with xml
        gameOne.setOnClickListener(this); //On click listen function used

    }

    @Override
    public void onClick(View v)
    {
        launchGameOne(); // Launches game one
    }

    private void launchGameOne()
    {
        Intent oneActivity = new Intent(IntroActivity.this, OneActivity.class); // Creates object intent to launch a new activity
        startActivity(oneActivity); // Launches the new activity
    }

}
