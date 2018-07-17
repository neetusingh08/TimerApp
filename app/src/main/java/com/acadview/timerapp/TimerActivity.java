package com.acadview.timerapp;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class TimerActivity extends AppCompatActivity {

    Button button3;
    TextView textView1;
    CountDownTimer countDownTimer;
    SeekBar seekBar;
    Boolean counterIsActive = false;


    public void resetTimer() {
        textView1.setText("0:30");
        seekBar.setProgress(30);
        countDownTimer.cancel();
        seekBar.setEnabled(true);
        counterIsActive = false;
    }

    public void updateTimer(int secondsLeft) {

        int min = (int) secondsLeft / 60;
        int sec = secondsLeft - min * 60;

        String secondString = Integer.toString(sec);

        if (sec <= 9) {
            secondString = "0" + secondString;
        }
        textView1.setText(Integer.toString(min) + ":" + secondString);

    }


    public void controlTimer(View view) {

        if (counterIsActive == false) {
            counterIsActive = true;
            seekBar.setEnabled(false);
            new CountDownTimer(seekBar.getProgress() * 1000 + 100, 1000) {
                @Override
                public void onTick(long l) {
                    updateTimer((int) l / 1000);

                }

                @Override
                public void onFinish() {
                    resetTimer();
                Toast.makeText(TimerActivity.this,"Finished",Toast.LENGTH_SHORT).show();


            }
        }.start();
    }else{
            resetTimer();
        }

}




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

         seekBar = (SeekBar)findViewById(R.id.seekBar);
        final TextView textView=(TextView)findViewById(R.id.textview1);
        seekBar.setMax(600);
        seekBar.setProgress(30);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                updateTimer(i);


            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        button3 = (Button)findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                controlTimer(view);
            }
        });



}}
