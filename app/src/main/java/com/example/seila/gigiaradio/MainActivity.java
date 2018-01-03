package com.example.seila.gigiaradio;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private Button play;
    private MediaPlayer mPlayer;
    private boolean playing = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        play = (Button) findViewById(R.id.btnPlay);
    }

    public void setPlaying(boolean playing)
    {
        this.playing = playing;
    }

    public void reproducir(View view)
    {
        if (!playing) {
            playGigiaRadio();
            play.setText(R.string.stop);
        }
        else {
            stop();
            play.setText(R.string.play);
        }
    }

    public void openPodcast(View view)
    {
        stop();
        play.setText(R.string.play);
        final Intent mIntent = new Intent(MainActivity.this, PodcastActivity.class);
        startActivity(mIntent);
    }

    public void playGigiaRadio()
    {
        String radio = "http://195.154.33.95:8220/;";
        try{
            if (mPlayer == null) {
                mPlayer = new MediaPlayer();
                mPlayer.setDataSource(radio);
                mPlayer.prepareAsync();
                mPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener(){

                    @Override
                    public void onPrepared(MediaPlayer mediaPlayer) {
                        mediaPlayer.start();
                    }
                });

                setPlaying(true);
            }
        } catch (IOException e){
            e.printStackTrace();

        }

    }

    public void stop()
    {
        if (mPlayer != null)
        {
            mPlayer.stop();
            mPlayer.release();
            mPlayer = null;

            setPlaying(false);
        }
    }

}
