package com.project.pmi.model;

public class RecursoTarea {
    private int id;
    private int idTarea;
    private int idRecurso;

    public RecursoTarea() {
    }

    public RecursoTarea(int id, int idTarea, int idRecurso) {
        this.id = id;
        this.idTarea = idTarea;
        this.idRecurso = idRecurso;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdTarea() {
        return idTarea;
    }

    public void setIdTarea(int idTarea) {
        this.idTarea = idTarea;
    }

    public int getIdRecurso() {
        return idRecurso;
    }

    public void setIdRecurso(int idRecurso) {
        this.idRecurso = idRecurso;
    }
}
