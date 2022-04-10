package com.project.pmi.dao;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;

import com.project.pmi.conexiones.Conexion;
import com.project.pmi.model.Proyecto;

import java.util.ArrayList;
import java.util.List;

public class DaoProyecto {
    Conexion conex;

    public DaoProyecto(Activity activity){
        conex = new Conexion(activity);
    }

    public boolean guardar(Proyecto proyecto) throws Exception{
        ContentValues registro = new ContentValues();
        registro.put("nombre", proyecto.getNombre());
        registro.put("fechaInicio", proyecto.getFechaInicio());
        registro.put("fechaFin", proyecto.getFechaFin());
        registro.put("etapa", proyecto.getEtapa());
        registro.put("idDirector", proyecto.getIdDirector());
        return conex.ejecutarInsert("proyecto", registro);
    }

    public Proyecto buscar(int id) {
        Proyecto proyecto= null;
        String consulta = "select  nombre, fechaInicio, fechaFin, etapa, idDirector  from proyecto where id=" + id+";";
        Cursor temp = conex.ejecutarSearch(consulta);
        if (temp.getCount() > 0) {
            temp.moveToFirst();
            proyecto = new Proyecto(id, temp.getString(0), temp.getString(1), temp.getString(2), temp.getString(3), temp.getInt(4));
        }
        conex.cerrarConexion();
        return proyecto;
    }

    public Proyecto buscarNombre(String nombre) {
        Proyecto proyecto= null;
        String consulta = "select id, fechaInicio, fechaFin, etapa, idDirector  from proyecto where nombre ='" + nombre+"';";
        Cursor temp = conex.ejecutarSearch(consulta);
        if (temp.getCount() > 0) {
            temp.moveToFirst();
            proyecto = new Proyecto(temp.getInt(0), nombre, temp.getString(1), temp.getString(2), temp.getString(3), temp.getInt(4));
        }
        conex.cerrarConexion();
        return proyecto;
    }

    public boolean eliminar(Proyecto proyecto) {
        String condicion = " id = " + proyecto.getId();
        return conex.ejecutarDelete("proyecto", condicion);
    }

    public boolean eliminarNombre(String nombre) {
        String condicion = " nombre = '" +nombre +"'";
        return conex.ejecutarDelete("proyecto", condicion);
    }

    public boolean modificar(Proyecto proyecto) {
        String condicion = " id = " + proyecto.getId();
        ContentValues registro = new ContentValues();
        registro.put("nombre", proyecto.getNombre());
        registro.put("fechaInicio", proyecto.getFechaInicio());
        registro.put("fechaFin", proyecto.getFechaFin());
        registro.put("etapa", proyecto.getEtapa());
        registro.put("idDirector", proyecto.getIdDirector());
        return conex.ejecutarUpdate("proyecto", condicion, registro);
    }

    public List<Proyecto> listar() {
        List<Proyecto> lista = new ArrayList();
        String consulta = "select  id,nombre, fechaInicio, fechaFin, etapa, idDirector  from proyecto ;";
        Cursor temp = conex.ejecutarSearch(consulta);
        if (temp.moveToFirst()) {
            do {
                Proyecto proyecto = new Proyecto(temp.getInt(0), temp.getString(1), temp.getString(2), temp.getString(3), temp.getString(4), temp.getInt(5));
                lista.add(proyecto);
            } while (temp.moveToNext());
        }
        return lista;
    }

    public List<Proyecto> listarIdDirector(int idDirector) {
        List<Proyecto> lista = new ArrayList();
        String consulta = "select  id, nombre, fechaInicio, fechaFin, etapa, idDirector  from proyecto where idDirector ="+idDirector+";";
        Cursor temp = conex.ejecutarSearch(consulta);
        if (temp.moveToFirst()) {
            do {
                Proyecto proyecto = new Proyecto(temp.getInt(0), temp.getString(1), temp.getString(2), temp.getString(3), temp.getString(4), temp.getInt(5));
                lista.add(proyecto);
            } while (temp.moveToNext());
        }
        return lista;
    }

}
