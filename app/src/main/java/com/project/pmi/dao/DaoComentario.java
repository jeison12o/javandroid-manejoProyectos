package com.project.pmi.dao;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;

import com.project.pmi.conexiones.Conexion;
import com.project.pmi.model.Comentario;

import java.util.ArrayList;
import java.util.List;

public class DaoComentario {

    Conexion conex;

    public DaoComentario(Activity activity){
        conex = new Conexion(activity);
    }

    public boolean guardar(Comentario comentario) throws Exception {
        ContentValues registro = new ContentValues();
        registro.put("mensaje", comentario.getMensaje());
        registro.put("idTarea", comentario.getIdTarea());
        return conex.ejecutarInsert("comentario", registro);
    }

    public Comentario buscar(int id) {
        Comentario comentario = null;
        String consulta = "select  mensaje, idTarea  from comentario where id=" + id+";";
        Cursor temp = conex.ejecutarSearch(consulta);
        if (temp.getCount() > 0) {
            temp.moveToFirst();
            comentario = new Comentario(id, temp.getString(0), temp.getInt(1));
        }
        conex.cerrarConexion();
        return comentario;
    }

    public boolean eliminar(Comentario comentario) {
        String condicion = " id = " + comentario.getId();
        return conex.ejecutarDelete("comentario", condicion);
    }

    public boolean modificar(Comentario comentario) {
        String condicion = " id = " + comentario.getId();
        ContentValues registro = new ContentValues();
        registro.put("mensaje", comentario.getMensaje());
        registro.put("idTarea", comentario.getIdTarea());
        return conex.ejecutarUpdate("comentarioo", condicion, registro);
    }

    public List<Comentario> listar() {
        List<Comentario> lista = new ArrayList();
        String consulta = "select  id, mensaje, idTarea  from comentario ;";
        Cursor temp = conex.ejecutarSearch(consulta);
        if (temp.moveToFirst()) {
            do {
                Comentario comentario = new Comentario( temp.getInt(0), temp.getString(1), temp.getInt(2));
                lista.add(comentario);
            } while (temp.moveToNext());
        }
        return lista;
    }
}
