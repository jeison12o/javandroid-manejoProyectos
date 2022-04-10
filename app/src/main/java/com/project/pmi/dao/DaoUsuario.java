package com.project.pmi.dao;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;

import com.project.pmi.conexiones.Conexion;
import com.project.pmi.model.Usuario;

import java.util.ArrayList;
import java.util.List;

public class DaoUsuario {

    Conexion conex;

    public DaoUsuario(Activity activity){
        conex = new Conexion(activity);
    }

    public boolean guardar(Usuario usuario) throws Exception {
        ContentValues registro = new ContentValues();
        registro.put("tipoDocumento", usuario.getTipoDocumento());
        registro.put("numeroDocumento", usuario.getNumeroDocumento());
        registro.put("nombres", usuario.getNombres());
        registro.put("apellidos", usuario.getApellidos());
        registro.put("fechaNacimiento", usuario.getFechaNacimiento());
        registro.put("contrasena", usuario.getContrasena());
        registro.put("correoElectronico", usuario.getCorreoElectronico());
        registro.put("tipoUsuario", usuario.getTipoUsuario());
        return conex.ejecutarInsert("usuario", registro);
    }

    public Usuario buscar(int id) {
        Usuario usuario= null;
        String consulta = "select  tipoDocumento, numeroDocumento, nombres, apellidos, fechaNacimiento, contrasena, correoElectronico, tipoUsuario  from usuario where id=" + id+";";
        Cursor temp = conex.ejecutarSearch(consulta);
        if (temp.getCount() > 0) {
            temp.moveToFirst();
            usuario = new Usuario(id, temp.getString(0), temp.getString(1), temp.getString(2), temp.getString(3), temp.getString(4), temp.getString(5), temp.getString(6), temp.getString(7));
        }
        conex.cerrarConexion();
        return usuario;
    }

    public Usuario buscaCorreo(String correo) {
        Usuario usuario= null;
        String consulta = "select id, tipoDocumento, numeroDocumento, nombres, apellidos, fechaNacimiento, contrasena, correoElectronico, tipoUsuario  from usuario where correoElectronico='" + correo+"';";
        Cursor temp = conex.ejecutarSearch(consulta);
        if (temp.getCount() > 0) {
            temp.moveToFirst();
            usuario = new Usuario(temp.getInt(0), temp.getString(1), temp.getString(2), temp.getString(3), temp.getString(4), temp.getString(5), temp.getString(6), temp.getString(7), temp.getString(8));
        }
        conex.cerrarConexion();
        return usuario;
    }

    public Usuario buscaDocumento(Usuario u) {
        Usuario usuario= null;
        String consulta = "select id, tipoDocumento, numeroDocumento, nombres, apellidos, fechaNacimiento, contrasena, correoElectronico, tipoUsuario  from usuario where numeroDocumento='" + u.getTipoDocumento()+"' and tipoDocumento='"+u.getTipoDocumento()+" ';";
        Cursor temp = conex.ejecutarSearch(consulta);
        if (temp.getCount() > 0) {
            temp.moveToFirst();
            usuario = new Usuario(temp.getInt(0), temp.getString(1), temp.getString(2), temp.getString(3), temp.getString(4), temp.getString(5), temp.getString(6), temp.getString(7), temp.getString(8));
        }
        conex.cerrarConexion();
        return usuario;
    }

    public boolean eliminar(Usuario usuario) {
        String condicion = " id = " + usuario.getId();
        return conex.ejecutarDelete("usuario", condicion);
    }

    public boolean modificar(Usuario usuario) {
        String condicion = " id = " + usuario.getId();
        ContentValues registro = new ContentValues();
        registro.put("tipoDocumento", usuario.getTipoDocumento());
        registro.put("numeroDocumento", usuario.getNumeroDocumento());
        registro.put("nombres", usuario.getNombres());
        registro.put("apellidos", usuario.getApellidos());
        registro.put("fechaNacimiento", usuario.getFechaNacimiento());
        registro.put("contrasena", usuario.getContrasena());
        registro.put("correoElectronico", usuario.getCorreoElectronico());
        registro.put("tipoUsuario", usuario.getTipoUsuario());
        return conex.ejecutarUpdate("usuario", condicion, registro);
    }

    public List<Usuario> listar() {
        List<Usuario> lista = new ArrayList();
        String consulta = "select  id, tipoDocumento, numeroDocumento, nombres, apellidos, fechaNacimiento, contrasena, correoElectronico, tipoUsuario  from usuario ;";
        Cursor temp = conex.ejecutarSearch(consulta);
        if (temp.moveToFirst()) {
            do {
                Usuario usuario = new Usuario(temp.getInt(0), temp.getString(1), temp.getString(2), temp.getString(3), temp.getString(4), temp.getString(5), temp.getString(6), temp.getString(7), temp.getString(8));
                lista.add(usuario);
            } while (temp.moveToNext());
        }
        return lista;
    }
}
