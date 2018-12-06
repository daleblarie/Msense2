package com.example.dalelarie.msense2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.media.MediaPlayer;
import android.os.Vibrator;
import android.content.Context;
import android.widget.SeekBar;
import android.os.CountDownTimer;




public class MainActivity extends AppCompatActivity {

    private boolean sounding;
    private int tempo;
    SeekBar seekBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tempo = 0;
        sounding = false;
        SeekBar seekBar = (SeekBar)findViewById(R.id.seekBar);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                Toast.makeText(getApplicationContext(),"seekbar progress: "+progress, Toast.LENGTH_SHORT).show();
                tempo = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                MessageBox("start");
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                MessageBox("stop");
            }
        });
    }
    /** Called when the user touches the button */


    //replace yourActivity.this with your own activity or if you declared a context you can write context.getSystemService(Context.VIBRATOR_SERVICE);
   public void buttonClick(View view){
       final View viewRef = view;
       final CountDownTimer countDownRef;
       sounding = !sounding;
       if(sounding){
           countDownRef = new CountDownTimer(20000, 5000) {

               public void onTick(long millisUntilFinished) {
                   if(!sounding){
                       this.cancel();
                   } else {
                       sendMessage(viewRef);
                   }
               }

               public void onFinish() {
                   sounding = false;
               }
           };
       countDownRef.start();
       }
       Toast.makeText(this, Boolean.toString(sounding), Toast.LENGTH_SHORT).show();
   }
    public void sendMessage(View view) {
        final MediaPlayer mp = MediaPlayer.create(this, R.raw.ping);
        final Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);;

        // Do something in response to button click
        if(view.getId() == R.id.button) {
            vibe.vibrate(500);
            mp.start();
            MessageBox("Hello World");
        }
    }
    public void MessageBox(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
//
//    public void updateTempo(View view){
//        if(view.getId() == R.id.seekBar) {
//            MessageBox("changing");
//        }
//    }

}
