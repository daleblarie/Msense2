package com.example.dalelarie.msense2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.media.MediaPlayer;
import android.os.Vibrator;
import android.content.Context;
import android.widget.SeekBar;
import android.os.CountDownTimer;




public class MainActivity extends AppCompatActivity {

    private boolean sounding;
    private Long tempo;
    SeekBar seekBar;
    EditText editInput;
    String contentCheck;
    Button startStopBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startStopBtn = (Button) findViewById(R.id.button);

        editInput = (EditText) findViewById(R.id.edit_input);

        editInput.requestFocus();
//        tempo = 0;
        sounding = false;
//        SeekBar seekBar = (SeekBar)findViewById(R.id.seekBar);
//
//        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
//
//            @Override
//            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
//                Toast.makeText(getApplicationContext(),"seekbar progress: "+progress, Toast.LENGTH_SHORT).show();
//                tempo = progress;
//            }
//
//            @Override
//            public void onStartTrackingTouch(SeekBar seekBar) {
//                MessageBox("start");
//            }
//
//            @Override
//            public void onStopTrackingTouch(SeekBar seekBar) {
//                MessageBox("stop");
//            }
//        });
    }
    /** Called when the user touches the button */

    public void showKeyboard(View view){
        InputMethodManager imm = (InputMethodManager)this.getSystemService(Context.INPUT_METHOD_SERVICE);
        editInput.selectAll();
//        InputMethodManager mgr = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(editInput, 0);
    }

    public void hideKeyboard(View view){
        InputMethodManager imm = (InputMethodManager)this.getSystemService(Context.INPUT_METHOD_SERVICE);
//        InputMethodManager mgr = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(editInput.getWindowToken(), 0);
    }

    //replace yourActivity.this with your own activity or if you declared a context you can write context.getSystemService(Context.VIBRATOR_SERVICE);
   public void buttonClick(View view){
        long speed = 300000;
       String content = editInput.getText().toString();
       contentCheck = editInput.getText().toString();
       if (!sounding) {
           if (!contentCheck.isEmpty()) {
               tempo = Long.parseLong(content);
               speed = (long) ((60000 / tempo));
           } else {
               sounding = true;
               MessageBox("enter a tempo in steps per minute");
           }
       }

        hideKeyboard(view);
       final View viewRef = view;
       final CountDownTimer countDownRef;
       sounding = !sounding;
       startStopBtn.setText("start");
       if(sounding){
           countDownRef = new CountDownTimer(300000, speed) {

               public void onTick(long millisUntilFinished) {
                   if(!sounding){
                       this.cancel();
                   } else {
                       startStopBtn.setText("stop");
                       sendMessage(viewRef);
                   }
               }

               public void onFinish() {
                   startStopBtn.setText("start");
                   sounding = false;
               }
           };
       countDownRef.start();
       }
//       Toast.makeText(this, Boolean.toString(sounding), Toast.LENGTH_SHORT).show();
   }
    public void sendMessage(View view) {
        final MediaPlayer mp = MediaPlayer.create(this, R.raw.ping);
        final Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);;

        // Do something in response to button click
        if(view.getId() == R.id.button) {
            vibe.vibrate(500);
            mp.start();
//            MessageBox("Hello World");
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
