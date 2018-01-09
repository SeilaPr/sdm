package com.example.seila.gigiaradio;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.twitter.sdk.android.core.DefaultLogger;
import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterConfig;
import com.twitter.sdk.android.tweetui.TweetTimelineRecyclerViewAdapter;
import com.twitter.sdk.android.tweetui.UserTimeline;

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
        twitter();
    }

    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.acerca_de) {
            openAcercaDe();
            return true;
        }
        else if (id == R.id.contacto) {
            openContacto();
            return true;
        }
        else if (id == R.id.equio) {
            openEquipo();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void openEquipo() {
        final Intent mIntent = new Intent(MainActivity.this, EquipoActivity.class);
        startActivity(mIntent);
    }

    private void openContacto() {
        final Intent mIntent = new Intent(MainActivity.this, ContactoActivity.class);
        startActivity(mIntent);
    }

    private void openAcercaDe() {
        final Intent mIntent = new Intent(MainActivity.this, AcercaDeActivity.class);
        startActivity(mIntent);
    }

    private void twitter() {
        TwitterConfig config = new TwitterConfig.Builder(this)
                .logger(new DefaultLogger(Log.DEBUG))
                .twitterAuthConfig(new TwitterAuthConfig("1zkRSru1gH4Ou6RNbRN75mw5o", "hRQy0pjf7D0LIAybkGqMC0BcuFTVzzIUQkCeGEqCqRY7wvOtN7"))
                .debug(true)
                .build();
        Twitter.initialize(config);


        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycled);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        final UserTimeline userTimeline = new UserTimeline.Builder().screenName("gigiaradio").build();

        final TweetTimelineRecyclerViewAdapter adapter =
                new TweetTimelineRecyclerViewAdapter.Builder(this)
                        .setTimeline(userTimeline)
                        .setViewStyle(R.style.tw__TweetLightWithActionsStyle)
                        .build();

        recyclerView.setAdapter(adapter);
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
