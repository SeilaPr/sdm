package com.example.seila.gigiaradio;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class ContactoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    public void openWeb(View view)
    {
        Uri uri = Uri.parse("https://www.gigiaradio.com");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

    public void openTwitter(View view)
    {
        Uri uri = Uri.parse("https://www.twitter.com/gigiaradio");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

    public void openInsta(View view)
    {
        Uri uri = Uri.parse("https://www.instagram.com/gigiaradio");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

    public void sendEmail(View view) {
        String[] TO = {"gigiaradioceo@gmail.com"}; //aquí pon tu correo
        String[] CC = {""};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_CC, CC);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Asunto");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Escribe aquí tu mensaje");

        try {
            startActivity(Intent.createChooser(emailIntent, "Enviar email..."));
            finish();
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(ContactoActivity.this,
                    "No tienes clientes de email instalados.", Toast.LENGTH_SHORT).show();
        }
    }
}
