package com.example.seila.gigiaradio;

/**
 * Created by robertoperez on 2/1/18.
 */

public class Podcast {

    private int uri;
    private String titulo;
    private String fecha_emision;
    private String descripcion;

    public Podcast(String titulo, int uri, String fecha, String desc)
    {
        this.titulo = titulo;
        this.uri = uri;
        this.fecha_emision = fecha;
        this.descripcion = desc;
    }

    public int getUri() {
        return uri;
    }

    public void setUri(int uri) {
        this.uri = uri;
    }

    public String getFecha_emision() {
        return fecha_emision;
    }

    public void setFecha_emision(String fecha_emision) {
        this.fecha_emision = fecha_emision;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }


    @Override
    public String toString()
    {
        return descripcion;
    }
}
