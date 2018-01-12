package com.example.seila.gigiaradio;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.TextView;

public class NoticiaEspecificaActivity extends AppCompatActivity {
    private TextView txtTitulo;                // Campo gráfico que muestra el títutlo
    private TextView txtDescripcion;        // Campo gráfico que muestra la descripción
    private TextView txtFecha;                // Campo gráfico que muestra la fecha
    private TextView txtLink;                // Campo gráfico que muestra el link

    private Uri uriNoticia;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_noticia_especifica);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        // Inicialización de campos gráficos
        txtTitulo = (TextView) findViewById(R.id.txtTitulo);
        txtDescripcion = (TextView) findViewById(R.id.txtDescripcion);
        txtDescripcion.setMovementMethod(LinkMovementMethod.getInstance());
        txtFecha = (TextView) findViewById(R.id.txtFecha);
        txtLink = (TextView) findViewById(R.id.txtLink);

        obtenerNoticia();
    }

    public void obtenerNoticia(){

        // Recibe el intent con la noticia
        Intent intent = getIntent();
        Noticia noticia = (Noticia) intent.getSerializableExtra("NOTICIA");

        // Muestra el contenido de la noticia
        txtTitulo.setText(noticia.getTitulo());
        txtDescripcion.setText(Html.fromHtml(noticia.getDescripcion()));
        txtFecha.setText(noticia.getFechaPublicacion());
        txtLink.setText(noticia.getUrl());

        uriNoticia = Uri.parse(noticia.getUrl());
    }

    public void openNoticia(View view)
    {
        Intent intent = new Intent(Intent.ACTION_VIEW, uriNoticia);
        startActivity(intent);
    }
}
