package com.project.pmi.controller;

import android.app.Activity;

import com.project.pmi.dao.DaoProyecto;
import com.project.pmi.dao.DaoUsuario;
import com.project.pmi.model.Proyecto;

import java.util.List;

public class CtlProyecto {

    DaoProyecto dao;

    public CtlProyecto(Activity activity) {
        dao = new DaoProyecto(activity);
    }

    public List<Proyecto> listarIdDirector(int idDirector) {
        return dao.listarIdDirector(idDirector);
    }

    public boolean guardar(Proyecto proyecto) throws Exception{
        Proyecto p = dao.buscarNombre(proyecto.getNombre());
        if (p == null) {
            return dao.guardar(proyecto);
        }else{
            throw new Exception("ya existe un proyecto con ese nombre debe cambiarlo");
        }
    }

    public Proyecto buscarNombre(String nombre) throws  Exception{
        Proyecto p = dao.buscarNombre(nombre);
        if (p == null) {
            throw new Exception("no existe el proyecto que busca");
        }else {
            return p;
        }
    }

    public boolean eliminarNombre(String nombre) {
        return dao.eliminarNombre(nombre);
    }

    public boolean Modificar(Proyecto proyecto) {
        return dao.modificar(proyecto);
    }
}
