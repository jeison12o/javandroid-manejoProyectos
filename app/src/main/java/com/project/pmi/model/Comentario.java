package com.project.pmi.model;

public class Comentario {
    private int id;
    private String mensaje;
    private int idTarea;

    public Comentario() {
    }

    public Comentario(int id, String mensaje, int idTarea) {
        this.id = id;
        this.mensaje = mensaje;
        this.idTarea = idTarea;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public int getIdTarea() {
        return idTarea;
    }

    public void setIdTarea(int idTarea) {
        this.idTarea = idTarea;
    }
}
