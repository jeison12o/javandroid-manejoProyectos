package com.project.pmi.dao;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;

import com.project.pmi.conexiones.Conexion;
import com.project.pmi.model.Actividad;

import java.util.ArrayList;
import java.util.List;

public class DaoActividad {

    Conexion conex;

    public DaoActividad(Activity activity){
        conex = new Conexion(activity);
    }

    public boolean guardar(Actividad actividad) throws Exception {
        ContentValues registro = new ContentValues();
        registro.put("nombre", actividad.getNombre());
        registro.put("descripcion", actividad.getDescripcion());
        registro.put("fechaInicio", actividad.getFechaInicio());
        registro.put("fechaFin", actividad.getFechaFin());
        registro.put("idProyecto", actividad.getIdProyecto());
        registro.put("idIntegranteProyecto", actividad.getIdIntegranteProyecto());
        return conex.ejecutarInsert("actividad", registro);
    }

    public Actividad buscar(int id) {
        Actividad actividad = null;
        String consulta = "select  nombre, descripcion, fechaInicio, fechaFin, idProyecto, idIntegranteProyecto  from actividad  where id=" + id+";";
        Cursor temp = conex.ejecutarSearch(consulta);
        if (temp.getCount() > 0) {
            temp.moveToFirst();
            actividad = new Actividad(id, temp.getString(0), temp.getString(1), temp.getString(2), temp.getString(3), temp.getInt(4), temp.getInt(5));
        }
        conex.cerrarConexion();
        return actividad;
    }

    public boolean eliminar(Actividad actividad) {
        String condicion = " id = " + actividad.getId();
        return conex.ejecutarDelete("actividad", condicion);
    }

    public boolean modificar(Actividad actividad) {
        String condicion = " id = " + actividad.getId();
        ContentValues registro = new ContentValues();
        registro.put("nombre", actividad.getNombre());
        registro.put("descripcion", actividad.getDescripcion());
        registro.put("fechaInicio", actividad.getFechaInicio());
        registro.put("fechaFin", actividad.getFechaFin());
        registro.put("idProyecto", actividad.getIdProyecto());
        registro.put("idIntegranteProyecto", actividad.getIdIntegranteProyecto());
        return conex.ejecutarUpdate("usuario", condicion, registro);
    }

    public List<Actividad> listar() {
        List<Actividad> lista = new ArrayList();
        String consulta = "select id, nombre, descripcion, fechaInicio, fechaFin, idProyecto, idIntegranteProyecto  from actividad ;";
        Cursor temp = conex.ejecutarSearch(consulta);
        if (temp.moveToFirst()) {
            do {
                Actividad actividad = new Actividad( temp.getInt(0), temp.getString(1), temp.getString(2), temp.getString(3), temp.getString(4), temp.getInt(5), temp.getInt(6));
                lista.add(actividad);
            } while (temp.moveToNext());
        }
        return lista;
    }
}
