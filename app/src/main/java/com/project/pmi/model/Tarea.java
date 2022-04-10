package com.project.pmi.model;

public class Tarea {
    private int id;
    private String nombre;
    private String porcentajeDesarrollo;
    private String fechaInicio;
    private String fechafin;
    private String estado;
    private int idActividad;

    public Tarea() {
    }

    public Tarea(int id, String nombre, String porcentajeDesarrollo, String fechaInicio, String fechafin, String estado, int idActividad) {
        this.id = id;
        this.nombre = nombre;
        this.porcentajeDesarrollo = porcentajeDesarrollo;
        this.fechaInicio = fechaInicio;
        this.fechafin = fechafin;
        this.estado = estado;
        this.idActividad = idActividad;
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

    public String getPorcentajeDesarrollo() {
        return porcentajeDesarrollo;
    }

    public void setPorcentajeDesarrollo(String porcentajeDesarrollo) {
        this.porcentajeDesarrollo = porcentajeDesarrollo;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechafin() {
        return fechafin;
    }

    public void setFechafin(String fechafin) {
        this.fechafin = fechafin;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getIdActividad() {
        return idActividad;
    }

    public void setIdActividad(int idActividad) {
        this.idActividad = idActividad;
    }
}
