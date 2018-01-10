package com.example.seila.gigiaradio;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class PideCancionActivity extends AppCompatActivity {

    private EditText nombre, cancion, preguntas;
    private String nombreStr, cancionStr, preguntasStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pide_cancion);

        nombre = (EditText) findViewById(R.id.editNombre);
        cancion = (EditText) findViewById(R.id.editCancion);
        preguntas = (EditText) findViewById(R.id.editPreguntas);


    }

    public void enviar(View view)
    {
        nombreStr = nombre.getText().toString();
        cancionStr = cancion.getText().toString();
        preguntasStr = preguntas.getText().toString();

        String[] TO = {"gigiaradioceo@gmail.com"};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);

        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "GigiaRadio: petición de canción");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Nombre: " + nombreStr + "\nCanción: " + cancionStr
             + "\nComentario: " + preguntasStr);

        try {
            if (!nombreStr.equals("") && !cancionStr.equals(""))
                startActivity(Intent.createChooser(emailIntent, "Enviando Email..."));
            else
                Toast.makeText(PideCancionActivity.this, "Rellena los campos nombre y canción", Toast.LENGTH_SHORT).show();
        }
        catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(PideCancionActivity.this, "No existe cliente Email instalado.", Toast.LENGTH_SHORT).show();
        }
    }
}
