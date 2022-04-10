package com.project.pmi.model;

public class Estado {
    private int id;
    private String descripcion;
    private String estado;
    private String porcentajeAvance;
    private String fecha;
    private int idProyecto;

    public Estado() {
    }

    public Estado(int id, String descripcion, String estado, String porcentajeAvance, String fecha, int idProyecto) {
        this.id = id;
        this.descripcion = descripcion;
        this.estado = estado;
        this.porcentajeAvance = porcentajeAvance;
        this.fecha = fecha;
        this.idProyecto = idProyecto;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getPorcentajeAvance() {
        return porcentajeAvance;
    }

    public void setPorcentajeAvance(String porcentajeAvance) {
        this.porcentajeAvance = porcentajeAvance;
    }

    public int getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(int idProyecto) {
        this.idProyecto = idProyecto;
    }
}
