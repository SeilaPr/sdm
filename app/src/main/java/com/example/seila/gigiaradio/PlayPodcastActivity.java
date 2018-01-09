package com.example.seila.gigiaradio;

import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.IOException;

public class PlayPodcastActivity extends AppCompatActivity {

    private TextView txtFecha, txtTitulo, txtDesc;
    private MediaPlayer mp;
    private Button play;
    private boolean playing = false;

    private SeekBar seekBar;
    private Handler handler;
    private Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_podcast);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        Bundle bundle = getIntent().getExtras();
        String titulo = bundle.getString("titulo");
        String fecha = bundle.getString("fecha");
        String desc = bundle.getString("descripcion");
        int uri = bundle.getInt("uri");

        Uri uriAudio = Uri.parse("android.resource://com.example.seila.gigiaradio/" + uri);

        txtTitulo = (TextView) findViewById(R.id.txtTitulo);
        txtFecha = (TextView) findViewById(R.id.txtFecha);
        txtDesc = (TextView) findViewById(R.id.txtDescripcion);


        txtTitulo.setText(titulo);
        txtFecha.setText(fecha);
        txtDesc.setText(desc);

        handler = new Handler();

        play = (Button) findViewById(R.id.btnPlay);
        seekBar = (SeekBar) findViewById(R.id.seekBar);

        mp = new MediaPlayer();
        try {
            mp.setDataSource(this, uriAudio);
            mp.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }

        mp.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                seekBar.setMax(mp.getDuration());

            }
        });



        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean input) {
                if (input)
                {
                    mp.seekTo(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public void playCycle()
    {
        seekBar.setProgress(mp.getCurrentPosition());

        if (mp.isPlaying())
        {
            runnable = new Runnable() {
                @Override
                public void run() {
                    playCycle();
                }
            };
            handler.postDelayed(runnable, 1000);
        }
    }

    public void playPodcast(View view)
    {
        if (!playing)
        {
            mp.start();
            playCycle();
            play.setText(R.string.stop);
            playing = true;
        }
        else
        {
            mp.pause();
            play.setText(R.string.play);
            playing = false;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mp.release();
        handler.removeCallbacks(runnable);
    }
}
