package com.project.pmi.dao;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;

import com.project.pmi.conexiones.Conexion;
import com.project.pmi.model.Estado;

import java.util.ArrayList;
import java.util.List;

public class DaoEstado {

    Conexion conex;

    public DaoEstado(Activity activity){
        conex = new Conexion(activity);
    }

    public boolean guardar(Estado estado) throws Exception {
        ContentValues registro = new ContentValues();
        registro.put("descripcion", estado.getDescripcion());
        registro.put("estado", estado.getEstado());
        registro.put("porcentajeAvance", estado.getPorcentajeAvance());
        registro.put("fecha", estado.getFecha());
        registro.put("idProyecto", estado.getIdProyecto());
        return conex.ejecutarInsert("estado", registro);
    }

    public Estado buscar(int id) {
        Estado estado = null;
        String consulta = "select  descripcion, estado, porcentajeAvance, fecha, idProyecto  from estado where id=" + id+";";
        Cursor temp = conex.ejecutarSearch(consulta);
        if (temp.getCount() > 0) {
            temp.moveToFirst();
            estado = new Estado(id, temp.getString(0), temp.getString(1), temp.getString(2), temp.getString(3), temp.getInt(4));
        }
        conex.cerrarConexion();
        return estado;
    }

    public boolean eliminar(Estado estado) {
        String condicion = " id = " + estado.getId();
        return conex.ejecutarDelete("estado", condicion);
    }

    public boolean modificar(Estado estado) {
        String condicion = " id = " + estado.getId();
        ContentValues registro = new ContentValues();
        registro.put("descripcion", estado.getDescripcion());
        registro.put("estado", estado.getEstado());
        registro.put("porcentajeAvance", estado.getPorcentajeAvance());
        registro.put("fecha", estado.getFecha());
        registro.put("idProyecto", estado.getIdProyecto());
        return conex.ejecutarUpdate("estado", condicion, registro);
    }

    public List<Estado> listar() {
        List<Estado> lista = new ArrayList();
        String consulta = "select id, descripcion, estado, porcentajeAvance, fecha, idProyecto  from estado ;";
        Cursor temp = conex.ejecutarSearch(consulta);
        if (temp.moveToFirst()) {
            do {
                Estado estado = new Estado( temp.getInt(0), temp.getString(1), temp.getString(2), temp.getString(3), temp.getString(4), temp.getInt(5));
                lista.add(estado);
            } while (temp.moveToNext());
        }
        return lista;
    }
}
