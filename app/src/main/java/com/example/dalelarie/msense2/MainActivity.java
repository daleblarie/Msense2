package com.example.dalelarie.msense2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.media.MediaPlayer;
import android.os.Vibrator;
import android.content.Context;



public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    /** Called when the user touches the button */


    //replace yourActivity.this with your own activity or if you declared a context you can write context.getSystemService(Context.VIBRATOR_SERVICE);
    public void sendMessage(View view) {
        final MediaPlayer mp = MediaPlayer.create(this, R.raw.ping);
        final Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);;

        // Do something in response to button click
        if(view.getId() == R.id.button)
        {
            vibe.vibrate(500);
            mp.start();
            MessageBox("Hello World");
        }
    }
    public void MessageBox(String message)
    {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

}
