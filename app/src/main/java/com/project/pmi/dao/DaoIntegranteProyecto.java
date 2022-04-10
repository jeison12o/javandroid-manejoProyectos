package com.project.pmi.dao;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;

import com.project.pmi.conexiones.Conexion;
import com.project.pmi.model.IntegranteProyecto;

import java.util.ArrayList;
import java.util.List;

public class DaoIntegranteProyecto {

    Conexion conex;

    public DaoIntegranteProyecto(Activity activity){
        conex = new Conexion(activity);
    }

    public boolean guardar(IntegranteProyecto integranteProyecto) throws Exception {
        ContentValues registro = new ContentValues();
        registro.put("idProyecto", integranteProyecto.getIdProyecto());
        registro.put("idIntegrante", integranteProyecto.getIdIntegrante());
        return conex.ejecutarInsert("integranteProyecto", registro);
    }

    public IntegranteProyecto buscar(int id) {
        IntegranteProyecto integranteProyecto = null;
        String consulta = "select  idProyecto, idIntegrante  from integranteProyecto where id=" + id+";";
        Cursor temp = conex.ejecutarSearch(consulta);
        if (temp.getCount() > 0) {
            temp.moveToFirst();
            integranteProyecto = new IntegranteProyecto(id, temp.getInt(0), temp.getInt(1));
        }
        conex.cerrarConexion();
        return integranteProyecto;
    }

    public boolean eliminar(IntegranteProyecto integranteProyecto) {
        String condicion = " id = " + integranteProyecto.getId();
        return conex.ejecutarDelete("integranteProyecto", condicion);
    }

    public boolean modificar(IntegranteProyecto integranteProyecto) {
        String condicion = " id = " + integranteProyecto.getId();
        ContentValues registro = new ContentValues();
        registro.put("idProyecto", integranteProyecto.getIdProyecto());
        registro.put("idIntegrante", integranteProyecto.getIdIntegrante());
        return conex.ejecutarUpdate("integranteProyecto", condicion, registro);
    }

    public List<IntegranteProyecto> listar() {
        List<IntegranteProyecto> lista = new ArrayList();
        String consulta = "select  id, idProyecto, idIntegrante  from integranteProyecto ;";
        Cursor temp = conex.ejecutarSearch(consulta);
        if (temp.moveToFirst()) {
            do {
                IntegranteProyecto integranteProyecto = new IntegranteProyecto(temp.getInt(0), temp.getInt(1), temp.getInt(2));
                lista.add(integranteProyecto);
            } while (temp.moveToNext());
        }
        return lista;
    }
}
