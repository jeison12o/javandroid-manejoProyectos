package com.project.pmi.dao;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;

import com.project.pmi.conexiones.Conexion;
import com.project.pmi.model.Reunion;

import java.util.ArrayList;
import java.util.List;

public class DaoReunion {

    Conexion conex;

    public DaoReunion(Activity activity){
        conex = new Conexion(activity);
    }

    public boolean guardar(Reunion reunion) throws Exception {
        ContentValues registro = new ContentValues();
        registro.put("latitud", reunion.getLatitud());
        registro.put("longitud", reunion.getLongitud());
        registro.put("mensajeSitio", reunion.getMensajeSitio());
        registro.put("tematica", reunion.getTematica());
        registro.put("idProyecto", reunion.getIdProyecto());
        return conex.ejecutarInsert("reunion", registro);
    }

    public Reunion buscar(int id) {
        Reunion reunion = null;
        String consulta = "select  latitud, longitud, mensajeSitio, tematica, idProyecto from reunion where id=" + id+";";
        Cursor temp = conex.ejecutarSearch(consulta);
        if (temp.getCount() > 0) {
            temp.moveToFirst();
            reunion = new Reunion(id, temp.getString(0), temp.getString(1), temp.getString(2), temp.getString(3), temp.getInt(4));
        }
        conex.cerrarConexion();
        return reunion;
    }

    public boolean eliminar(Reunion reunion) {
        String condicion = " id = " + reunion.getId();
        return conex.ejecutarDelete("reunion", condicion);
    }

    public boolean modificar(Reunion reunion) {
        String condicion = " id = " + reunion.getId();
        ContentValues registro = new ContentValues();
        registro.put("latitud", reunion.getLatitud());
        registro.put("longitud", reunion.getLongitud());
        registro.put("mensajeSitio", reunion.getMensajeSitio());
        registro.put("tematica", reunion.getTematica());
        registro.put("idProyecto", reunion.getIdProyecto());
        return conex.ejecutarUpdate("reunion", condicion, registro);
    }

    public List<Reunion> listar() {
        List<Reunion> lista = new ArrayList();
        String consulta = "select id, latitud, longitud, mensajeSitio, tematica, idProyecto from reunion ;";
        Cursor temp = conex.ejecutarSearch(consulta);
        if (temp.moveToFirst()) {
            do {
                Reunion reunion = new Reunion(temp.getInt(0), temp.getString(1), temp.getString(2), temp.getString(3), temp.getString(4), temp.getInt(5));
                lista.add(reunion);
            } while (temp.moveToNext());
        }
        return lista;
    }
}
