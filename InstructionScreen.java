package com.example.paudeljenishspeedtactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class InstructionScreen extends AppCompatActivity {
    private MediaPlayer bgMusic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instruction_screen);
        //Makes Music play----------------------------------
        bgMusic=MediaPlayer.create(this,R.raw.bgmusic);
        playMusic();
    }

    public void playMusic() {
        bgMusic.start();
        bgMusic.setLooping(true);
    }
    public void stopMusic() {
        bgMusic.pause();
    }


    public void nextClick (View view) {
        startActivity(new Intent(InstructionScreen.this, MainActivity.class));

    }

//Makes music play and pause --------------------------------------------------------------------------------
    public void mute (View view) {
        Button mute = (Button) findViewById(R.id.mutemusic);
        String a = mute.getText().toString();
        if (bgMusic.isPlaying()) {
            bgMusic.pause();
            mute.setText("Music: OFF");
            mute.setBackgroundColor(Color.RED);
        } else {
            bgMusic.start();
            mute.setText("Music: ON");
            mute.setBackgroundColor(Color.parseColor("#80CBC4"));
        }


    }
}
