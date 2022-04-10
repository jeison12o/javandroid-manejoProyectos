package com.project.pmi.dao;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;

import com.project.pmi.conexiones.Conexion;
import com.project.pmi.model.RecursoTarea;

import java.util.ArrayList;
import java.util.List;

public class DaoRecursoTarea {

    Conexion conex;

    public DaoRecursoTarea(Activity activity){
        conex = new Conexion(activity);
    }

    public boolean guardar(RecursoTarea recursoTarea) throws Exception {
        ContentValues registro = new ContentValues();
        registro.put("idTarea", recursoTarea.getIdTarea());
        registro.put("idRecurso", recursoTarea.getIdRecurso());
        return conex.ejecutarInsert("recurso_tarea", registro);
    }

    public RecursoTarea buscar(int id) {
        RecursoTarea recursoTarea = null;
        String consulta = "select  idTarea, idRecurso  from recurso_tarea where id=" + id+";";
        Cursor temp = conex.ejecutarSearch(consulta);
        if (temp.getCount() > 0) {
            temp.moveToFirst();
            recursoTarea = new RecursoTarea( id,temp.getInt(0), temp.getInt(1));
        }
        conex.cerrarConexion();
        return recursoTarea;
    }

    public boolean eliminar(RecursoTarea recursoTarea) {
        String condicion = " id = " + recursoTarea.getId();
        return conex.ejecutarDelete("recurso_tarea", condicion);
    }

    public boolean modificar(RecursoTarea recursoTarea) {
        String condicion = " id = " + recursoTarea.getId();
        ContentValues registro = new ContentValues();
        registro.put("idTarea", recursoTarea.getIdTarea());
        registro.put("idRecurso", recursoTarea.getIdRecurso());
        return conex.ejecutarUpdate("recurso_tarea", condicion, registro);
    }

    public List<RecursoTarea> listar() {
        List<RecursoTarea> lista = new ArrayList();
        String consulta = "select id, idTarea, idRecurso  from recurso_tarea ;";
        Cursor temp = conex.ejecutarSearch(consulta);
        if (temp.moveToFirst()) {
            do {
                RecursoTarea recursoTarea = new RecursoTarea( temp.getInt(0),temp.getInt(1), temp.getInt(2));
                lista.add(recursoTarea);
            } while (temp.moveToNext());
        }
        return lista;
    }

}
