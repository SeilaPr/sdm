package com.example.seila.gigiaradio;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Noticia implements Serializable {

    private String titulo;
    private String descripcion;
    private String fechaPublicacion;
    private String url;

    public Noticia(String titulo, String descripcion, String fechaPublicacion,
                   String url) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fechaPublicacion = fechaPublicacion;
        this.url = url;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getFechaPublicacion() {
        return fechaPublicacion;
    }

    public String getUrl() {
        return url;
    }

}
