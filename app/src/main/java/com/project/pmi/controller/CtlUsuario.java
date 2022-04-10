package com.project.pmi.controller;

import android.app.Activity;

import com.project.pmi.dao.DaoUsuario;
import com.project.pmi.model.Usuario;

import java.util.List;

public class CtlUsuario {

    DaoUsuario dao;
    private static Usuario usuarioIntante = null;

    public CtlUsuario(Activity activity) {
        dao = new DaoUsuario(activity);
    }

    public boolean registrar(Usuario usuario) throws Exception {
        Usuario u = dao.buscaCorreo(usuario.getCorreoElectronico());
        if (u == null) {
            u = dao.buscaDocumento(usuario);
            if (u == null) {
                return dao.guardar(usuario);
            }else {
                throw  new Exception("ya existe una persona con ese tipo y numero de documento debe cambiarlo ");
            }
        }else {
            throw  new Exception("ya existe una persona con ese correo debe cambiarlo");
        }
        //throw  new Exception("error al guardar");
    }

    public void eliminar(int id) throws Exception {
        Usuario usuario = new Usuario();
        usuario.setId(id);
            if (dao.eliminar(usuario)) {
                throw new Exception("se elimino con exito");
            } else {
                throw new Exception("error al eliminar o no existe el usuario");
            }
    }

    public void actualizar(Usuario usuario) throws Exception {
        try {
            dao.modificar(usuario);
        }catch (Exception e) {
            System.out.println(e.getMessage()+"causa:"+e.getCause());
        }
    }

    public Usuario buscar(int id) throws Exception {
        Usuario usuario = dao.buscar(id);
        if (usuario != null) {
            return usuario;
        }
        throw new Exception("no existe el usuario");
    }

    public List<Usuario> listar() {
        return dao.listar();
    }

    public Usuario login(Usuario usuario) throws  Exception{
        Usuario busqueda = dao.buscaCorreo(usuario.getCorreoElectronico());
        if (busqueda == null) {
            throw new Exception("Esta usuario no se ha registrado");
        }else {
            if (busqueda.getContrasena().equals(usuario.getContrasena())) {
                return busqueda;
            }else {
                throw new Exception("Contraseña incorrecta");
            }
        }
    }

    public static Usuario getUsuarioIntante() {
        return usuarioIntante;
    }

    public static void setUsuarioIntante(Usuario usuarioIntante) {
        CtlUsuario.usuarioIntante = usuarioIntante;
    }
}
