package com.project.pmi.model;

public class Actividad {
    private int id;
    private String nombre;
    private String descripcion;
    private String fechaInicio;
    private String fechaFin;
    private int idProyecto;
    private int idIntegranteProyecto;

    public Actividad() {
    }

    public Actividad(int id, String nombre, String descripcion, String fechaInicio, String fechaFin, int idProyecto, int idIntegranteProyecto) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.idProyecto = idProyecto;
        this.idIntegranteProyecto = idIntegranteProyecto;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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

    public int getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(int idProyecto) {
        this.idProyecto = idProyecto;
    }

    public int getIdIntegranteProyecto() {
        return idIntegranteProyecto;
    }

    public void setIdIntegranteProyecto(int idIntegranteProyecto) {
        this.idIntegranteProyecto = idIntegranteProyecto;
    }
}
