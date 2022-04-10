package com.project.pmi.model;

public class Proyecto {
    private int id;
    private String nombre;
    private String fechaInicio;
    private String fechaFin;
    private String etapa;
    private int idDirector;

    public Proyecto() {
    }

    public Proyecto(int id, String nombre, String fechaInicio, String fechaFin, String etapa, int idDirector) {
        this.id = id;
        this.nombre = nombre;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.etapa = etapa;
        this.idDirector = idDirector;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getEtapa() {
        return etapa;
    }

    public void setEtapa(String etapa) {
        this.etapa = etapa;
    }

    public int getIdDirector() {
        return idDirector;
    }

    public void setIdDirector(int idDirector) {
        this.idDirector = idDirector;
    }

    @Override
    public String toString() {
        return "Proyecto{" +
                "id=" + id +", nombre='" + nombre + ", fechaInicio=" + fechaInicio +
                ", fechaFin=" + fechaFin +", etapa=" + etapa;
    }
}
