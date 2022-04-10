package com.project.pmi.model;

public class Reunion {
    private int id;
    private String latitud;
    private String longitud;
    private String mensajeSitio;
    private String tematica;
    private int idProyecto;

    public Reunion() {
    }

    public Reunion(int id, String latitud, String longitud, String mensajeSitio, String tematica, int idProyecto) {
        this.id = id;
        this.latitud = latitud;
        this.longitud = longitud;
        this.mensajeSitio = mensajeSitio;
        this.tematica = tematica;
        this.idProyecto = idProyecto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public String getMensajeSitio() {
        return mensajeSitio;
    }

    public void setMensajeSitio(String mensajeSitio) {
        this.mensajeSitio = mensajeSitio;
    }

    public String getTematica() {
        return tematica;
    }

    public void setTematica(String tematica) {
        this.tematica = tematica;
    }

    public int getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(int idProyecto) {
        this.idProyecto = idProyecto;
    }
}
