package com.example.seila.gigiaradio;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class PodcastActivity extends AppCompatActivity {


    private Podcast carmin1, carmin2, carmin3, carmin4;
    private Podcast perlora;
    private Podcast beerpong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_podcast);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        perlora = new Podcast("Fiestas de Perlora", R.raw.perlora, "11 de julio de 2017", "En esta fiesta, nuestro equipo" +
                " preguntó a los allí presentes anécdotas graciosas estando borrachos. También se preguntó por maneras de ligar" +
                " a través de Instagram, Whatsapp y, a los más veteranos de las redes sociales, cómo se ligaba por Tuenti. Qué tiempos" +
                " aquellos...");

        carmin1 = new Podcast("Carmín Parte 1", R.raw.carmin1, "26 de julio de 2017", "Parte 1 de la grabación realizada por nuestro equipo" +
                " en el Carmín de la Pola 2017, dónde se preguntó qué es la cosa más rara que le ha ocurrido a la gente" +
                " en el Carmín, y quién ganaría en una pelea entre Bobobó y Falete. Como siempre, las respuestas son" +
                " muy inesperadas...");

        carmin2 = new Podcast("Carmín Parte 2", R.raw.carmin2, "26 de julio de 2017", "Parte 2 de la grabación realizada por nuestro equipo" +
                " en el Carmín de la Pola 2017, dónde se preguntó qué es la cosa más rara que le ha ocurrido a la gente" +
                " en el Carmín, y quién ganaría en una pelea entre Bobobó y Falete. Como siempre, las respuestas son" +
                " muy inesperadas...");

        carmin3 = new Podcast("Carmín Parte 3", R.raw.carmin3, "29 de julio de 2017", "Parte 3 de la grabación realizada por nuestro equipo" +
                " en el Carmín de la Pola 2017, dónde se preguntó qué es la cosa más rara que le ha ocurrido a la gente" +
                " en el Carmín, y quién ganaría en una pelea entre Bobobó y Falete. Como siempre, las respuestas son" +
                " muy inesperadas...");

        carmin4 = new Podcast("Carmín Parte 4", R.raw.carmin4, "29 de julio de 2017", "Parte 4 de la grabación realizada por nuestro equipo" +
                " en el Carmín de la Pola 2017, dónde se preguntó qué es la cosa más rara que le ha ocurrido a la gente" +
                " en el Carmín, y quién ganaría en una pelea entre Bobobó y Falete. Como siempre, las respuestas son" +
                " muy inesperadas...");

        beerpong = new Podcast("Campeonato de beerpong", R.raw.beerpong, "29 de julio de 2017", "Retransmisión" +
                " en directo de los cuartos de final del primer torneo de Beerpong organizado en el bar Macomo Gijón, donde" +
                " se enfrentaron nuestros dos locutores Roberto Pérez y César Monetero frente al encargado del bar Diego Canal" +
                " y el camarero Diego González 'Chino'. Comentaristas: Alberto Martínez y Diego Suárez 'Deco'." +
                " ¿Quién ganará?");
    }

    public void showCarmin1(View view)
    {
        final Intent mIntent = new Intent(PodcastActivity.this, PlayPodcastActivity.class);
        mIntent.putExtra("titulo", carmin1.getTitulo());
        mIntent.putExtra("uri", carmin1.getUri());
        mIntent.putExtra("fecha", carmin1.getFecha_emision());
        mIntent.putExtra("descripcion", carmin1.getDescripcion());
        startActivity(mIntent);
    }

    public void showCarmin2(View view)
    {
        final Intent mIntent = new Intent(PodcastActivity.this, PlayPodcastActivity.class);
        mIntent.putExtra("titulo", carmin2.getTitulo());
        mIntent.putExtra("uri", carmin2.getUri());
        mIntent.putExtra("fecha", carmin2.getFecha_emision());
        mIntent.putExtra("descripcion", carmin2.getDescripcion());
        startActivity(mIntent);
    }

    public void showCarmin3(View view)
    {
        final Intent mIntent = new Intent(PodcastActivity.this, PlayPodcastActivity.class);
        mIntent.putExtra("titulo", carmin3.getTitulo());
        mIntent.putExtra("uri", carmin3.getUri());
        mIntent.putExtra("fecha", carmin3.getFecha_emision());
        mIntent.putExtra("descripcion", carmin3.getDescripcion());
        startActivity(mIntent);
    }

    public void showCarmin4(View view)
    {
        final Intent mIntent = new Intent(PodcastActivity.this, PlayPodcastActivity.class);
        mIntent.putExtra("titulo", carmin4.getTitulo());
        mIntent.putExtra("uri", carmin4.getUri());
        mIntent.putExtra("fecha", carmin4.getFecha_emision());
        mIntent.putExtra("descripcion", carmin4.getDescripcion());
        startActivity(mIntent);
    }

    public void showPerlora(View view)
    {
        final Intent mIntent = new Intent(PodcastActivity.this, PlayPodcastActivity.class);
        mIntent.putExtra("titulo", perlora.getTitulo());
        mIntent.putExtra("uri", perlora.getUri());
        mIntent.putExtra("fecha", perlora.getFecha_emision());
        mIntent.putExtra("descripcion", perlora.getDescripcion());
        startActivity(mIntent);
    }

    public void showPong(View view)
    {
        final Intent mIntent = new Intent(PodcastActivity.this, PlayPodcastActivity.class);
        mIntent.putExtra("titulo", beerpong.getTitulo());
        mIntent.putExtra("uri", beerpong.getUri());
        mIntent.putExtra("fecha", beerpong.getFecha_emision());
        mIntent.putExtra("descripcion", beerpong.getDescripcion());
        startActivity(mIntent);
    }

    public void openIvoox(View view)
    {
        Uri uri = Uri.parse("https://www.ivoox.com/escuchar-gigiaradio-temporada-2017_nq_300067_1.html");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

}
