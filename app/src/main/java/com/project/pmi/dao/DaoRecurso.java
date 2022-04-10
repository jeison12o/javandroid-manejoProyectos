package com.project.pmi.dao;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;

import com.project.pmi.conexiones.Conexion;
import com.project.pmi.model.Recurso;

import java.util.ArrayList;
import java.util.List;

public class DaoRecurso {

    Conexion conex;

    public DaoRecurso(Activity activity){
        conex = new Conexion(activity);
    }

    public boolean guardar(Recurso recurso) throws Exception {
        ContentValues registro = new ContentValues();
        registro.put("nombre", recurso.getNombre());
        registro.put("cantidad", recurso.getCantidad());
        registro.put("descripcion", recurso.getDescripcion());
        registro.put("ubicacion", recurso.getUbicacion());
        return conex.ejecutarInsert("recurso", registro);
    }

    public Recurso buscar(int id) {
        Recurso recurso = null;
        String consulta = "select  nombre, cantidad, descripcion, ubicacion from recurso where id=" + id+";";
        Cursor temp = conex.ejecutarSearch(consulta);
        if (temp.getCount() > 0) {
            temp.moveToFirst();
            recurso = new Recurso(id, temp.getString(0), temp.getInt(1), temp.getString(2), temp.getString(3));
        }
        conex.cerrarConexion();
        return recurso;
    }

    public boolean eliminar(Recurso recurso) {
        String condicion = " id = " + recurso.getId();
        return conex.ejecutarDelete("recurso", condicion);
    }

    public boolean modificar(Recurso recurso) {
        String condicion = " id = " + recurso.getId();
        ContentValues registro = new ContentValues();
        registro.put("nombre", recurso.getNombre());
        registro.put("cantidad", recurso.getCantidad());
        registro.put("descripcion", recurso.getDescripcion());
        registro.put("ubicacion", recurso.getUbicacion());
        return conex.ejecutarUpdate("usuario", condicion, registro);
    }

    public List<Recurso> listar() {
        List<Recurso> lista = new ArrayList();
        String consulta = "select  id, nombre, cantidad, descripcion, ubicacion from recurso ;";
        Cursor temp = conex.ejecutarSearch(consulta);
        if (temp.moveToFirst()) {
            do {
                Recurso recurso = new Recurso(temp.getInt(0), temp.getString(1), temp.getInt(2), temp.getString(3), temp.getString(4));
                lista.add(recurso);
            } while (temp.moveToNext());
        }
        return lista;
    }
}
