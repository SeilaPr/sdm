package com.example.seila.gigiaradio;

import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.UploadErrorException;
import com.google.firebase.auth.FirebaseAuth;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import static android.Manifest.permission.INTERNET;
import static android.Manifest.permission.RECORD_AUDIO;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class GrabarActivity extends AppCompatActivity {

    private FirebaseAuth auth;
    private String email;

    //Dropbox
    private static final String ACCESS_TOKEN = "zcXEO5Gh-mQAAAAAAAABxDm8GxJSNOcc7lA1gBC1u5WyYiqhuAlGi6UkSc3EGQS0";

    private String path = "";
    private MediaRecorder mRecorder;

    private MediaPlayer mPlayer ;
    private DbxClientV2 client; //Dropbox

    private Button grabar,finalizar,reproducir,enviar;

    private String nombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grabar);

        auth = FirebaseAuth.getInstance();
        email = auth.getCurrentUser().getEmail().trim();

        DbxRequestConfig config = DbxRequestConfig.newBuilder("GigiaRadio").build();
        client = new DbxClientV2(config, ACCESS_TOKEN);

        grabar = (Button) findViewById(R.id.grabar);
        finalizar = (Button) findViewById(R.id.finalizar);
        reproducir = (Button) findViewById(R.id.reproducir);
        enviar = (Button) findViewById(R.id.enviar);

        finalizar.setEnabled(false);
        reproducir.setEnabled(false);
        enviar.setEnabled(false);
    }

    public void comenzar(View view){

        if(permisos()) {
            nombre = email + System.currentTimeMillis();
            path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" +
                    nombre + ".mp3";

            mRecorder();

            try {
                mRecorder.prepare();
                mRecorder.start();
            } catch (IllegalStateException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            grabar.setEnabled(false);
            finalizar.setEnabled(true);
            Toast.makeText(GrabarActivity.this, "Ha comenzado la grabación",
                    Toast.LENGTH_SHORT).show();
        }else{
            pedirPermisos();
        }
    }

    public void finalizar(View view){
        mRecorder.stop();
        mRecorder.release();
        finalizar.setEnabled(false);
        reproducir.setEnabled(true);
        enviar.setEnabled(true);

        Toast.makeText(GrabarActivity.this, "Ha finalizado la grabación",
                Toast.LENGTH_SHORT).show();
    }

    public void repro(View view){
        mPlayer = new MediaPlayer();
        try {
            mPlayer.setDataSource(path);
            mPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }

        mPlayer.start();
        Toast.makeText(GrabarActivity.this, "Reproduciendo grabación",
                Toast.LENGTH_SHORT).show();
    }

    public void enviar(View view){

        if(mPlayer != null){
            mPlayer.stop();
            mPlayer.release();
        }

        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                try (InputStream in = new FileInputStream(path)) {
                    client.files().uploadBuilder("/"+
                            nombre +".mp3")
                            .uploadAndFinish(in);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    System.err.print("Fichero no encontrado "+ path);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (UploadErrorException e) {
                    e.printStackTrace();
                    System.err.print("Error al subir");
                } catch (DbxException e) {
                    e.printStackTrace();
                    System.err.print("Error de dropbox");
                }
            }
        });

        thread.start();
        finish();
        Toast.makeText(GrabarActivity.this, "Audio enviado",
                Toast.LENGTH_SHORT).show();
    }


    private void mRecorder() {
        mRecorder = new MediaRecorder();
        mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
        mRecorder.setOutputFile(path);
    }

    private void pedirPermisos() {
        ActivityCompat.requestPermissions(GrabarActivity.this, new
                String[]{WRITE_EXTERNAL_STORAGE, RECORD_AUDIO, INTERNET}, 200);
    }

    public boolean permisos() {
        int result = ContextCompat.checkSelfPermission(getApplicationContext(),
                WRITE_EXTERNAL_STORAGE);
        int result1 = ContextCompat.checkSelfPermission(getApplicationContext(),
                RECORD_AUDIO);
        int result2 = ContextCompat.checkSelfPermission(getApplicationContext(),
                INTERNET);
        return result == PackageManager.PERMISSION_GRANTED &&
                result1 == PackageManager.PERMISSION_GRANTED && result2 == PackageManager.PERMISSION_GRANTED;
    }
}
