package com.project.pmi.dao;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;

import com.project.pmi.conexiones.Conexion;
import com.project.pmi.model.Encargo;

import java.util.ArrayList;
import java.util.List;

public class DaoEncargo {

    Conexion conex;

    public DaoEncargo(Activity activity){
        conex = new Conexion(activity);
    }

    public boolean guardar(Encargo encargo) throws Exception {
        ContentValues registro = new ContentValues();
        registro.put("nombre", encargo.getNombre());
        registro.put("descripcion", encargo.getDescripcion());
        registro.put("horario", encargo.getHorario());
        registro.put("salario", encargo.getSalario());
        registro.put("idProyecto", encargo.getIdProyecto());
        return conex.ejecutarInsert("encargo", registro);
    }

    public Encargo buscar(int id) {
        Encargo encargo = null;
        String consulta = "select  nombre, descripcion, horario, salario, idProyecto from encargo where id=" + id+";";
        Cursor temp = conex.ejecutarSearch(consulta);
        if (temp.getCount() > 0) {
            temp.moveToFirst();
            encargo = new Encargo(id, temp.getString(0), temp.getString(1), temp.getString(2), temp.getString(3), temp.getInt(4) );
        }
        conex.cerrarConexion();
        return encargo;
    }

    public boolean eliminar(Encargo encargo) {
        String condicion = " id = " + encargo.getId();
        return conex.ejecutarDelete("encargo", condicion);
    }

    public boolean modificar(Encargo encargo) {
        String condicion = " id = " + encargo.getId();
        ContentValues registro = new ContentValues();
        registro.put("nombre", encargo.getNombre());
        registro.put("descripcion", encargo.getDescripcion());
        registro.put("horario", encargo.getHorario());
        registro.put("salario", encargo.getSalario());
        registro.put("idProyecto", encargo.getIdProyecto());
        return conex.ejecutarUpdate("encargo", condicion, registro);
    }

    public List<Encargo> listar() {
        List<Encargo> lista = new ArrayList();
        String consulta = "select  id, nombre, descripcion, horario, salario, idProyecto from encargo ;";
        Cursor temp = conex.ejecutarSearch(consulta);
        if (temp.moveToFirst()) {
            do {
                Encargo encargo = new Encargo( temp.getInt(0), temp.getString(1), temp.getString(2), temp.getString(3), temp.getString(4), temp.getInt(5));
                lista.add(encargo);
            } while (temp.moveToNext());
        }
        return lista;
    }
}
