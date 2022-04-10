package com.project.pmi.dao;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;

import com.project.pmi.conexiones.Conexion;
import com.project.pmi.model.Tarea;
import com.project.pmi.model.Usuario;

import java.util.ArrayList;
import java.util.List;

public class DaoTarea {

    Conexion conex;

    public DaoTarea(Activity activity){
        conex = new Conexion(activity);
    }

    public boolean guardar(Tarea tarea) throws Exception {
        ContentValues registro = new ContentValues();
        registro.put("nombre", tarea.getNombre());
        registro.put("porcentajeDesarrollo", tarea.getPorcentajeDesarrollo());
        registro.put("fechaInicio", tarea.getFechaInicio());
        registro.put("fechafin", tarea.getFechafin());
        registro.put("estado", tarea.getEstado());
        registro.put("idActividad", tarea.getIdActividad());
        return conex.ejecutarInsert("tarea", registro);
    }

    public Tarea buscar(int id) {
        Tarea tarea = null;
        String consulta = "select  nombre, porcentajeDesarrollo, fechaInicio, fechafin, estado, idActividad  from tarea where id=" + id+";";
        Cursor temp = conex.ejecutarSearch(consulta);
        if (temp.getCount() > 0) {
            temp.moveToFirst();
            tarea = new Tarea(id, temp.getString(0), temp.getString(1), temp.getString(2), temp.getString(3), temp.getString(4), temp.getInt(5) );
        }
        conex.cerrarConexion();
        return tarea;
    }

    public boolean eliminar(Tarea tarea) {
        String condicion = " id = " + tarea.getId();
        return conex.ejecutarDelete("tarea", condicion);
    }

    public boolean modificar(Tarea tarea) throws Exception{
        String condicion = " id = " + tarea.getId();
        ContentValues registro = new ContentValues();
        registro.put("nombre", tarea.getNombre());
        registro.put("porcentajeDesarrollo", tarea.getPorcentajeDesarrollo());
        registro.put("fechaInicio", tarea.getFechaInicio());
        registro.put("fechafin", tarea.getFechafin());
        registro.put("estado", tarea.getEstado());
        registro.put("idActividad", tarea.getIdActividad());
        return conex.ejecutarUpdate("tarea", condicion, registro);
    }

    public List<Tarea> listar() {
        List<Tarea> lista = new ArrayList();
        String consulta = "select  id, nombre, porcentajeDesarrollo, fechaInicio, fechafin, estado, idActividad  from tarea ;";
        Cursor temp = conex.ejecutarSearch(consulta);
        if (temp.moveToFirst()) {
            do {
                Tarea tarea = new Tarea(temp.getInt(0), temp.getString(1), temp.getString(2), temp.getString(3), temp.getString(4), temp.getString(5), temp.getInt(6));
                lista.add(tarea);
            } while (temp.moveToNext());
        }
        return lista;
    }
}
