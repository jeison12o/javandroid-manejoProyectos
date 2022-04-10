package com.project.pmi.model;

public class IntegranteProyecto {
    private int id;
    private int idProyecto;
    private int idIntegrante;

    public IntegranteProyecto() {
    }

    public IntegranteProyecto(int id, int idProyecto, int idIntegrante) {
        this.id = id;
        this.idProyecto = idProyecto;
        this.idIntegrante = idIntegrante;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(int idProyecto) {
        this.idProyecto = idProyecto;
    }

    public int getIdIntegrante() {
        return idIntegrante;
    }

    public void setIdIntegrante(int idIntegrante) {
        this.idIntegrante = idIntegrante;
    }
}
