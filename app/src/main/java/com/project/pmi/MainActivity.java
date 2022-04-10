package com.project.pmi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.project.pmi.controller.CtlUsuario;
import com.project.pmi.model.Usuario;

public class MainActivity extends AppCompatActivity {

    private CtlUsuario ctlUsuario;
    private TextInputEditText txtCorreo, txtContrasena;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ctlUsuario = new CtlUsuario(this);
        txtCorreo = findViewById(R.id.txtLoginCorreo);
        txtContrasena = findViewById(R.id.txtLoginContraseña);
    }

    public void vistaRegistrarUsuario(View view) {
        startActivity(new Intent(this, RegistrarUsuarioActivity.class));
    }

    public void login(View view) {
        String correo = txtCorreo.getText().toString();
        String contra = txtContrasena.getText().toString();

        if (correo.isEmpty() || contra.isEmpty()) {
            imprimirMensaje("debe ingresar los datos");
        }else {
            Usuario usuario = new Usuario();
            usuario.setCorreoElectronico(correo);
            usuario.setContrasena(contra);

            try {
                Usuario r = ctlUsuario.login(usuario);
                ctlUsuario.setUsuarioIntante(r);
                Intent intent;
                if (r.getTipoUsuario().equals("director")) {
                    intent = new Intent(this, MenuProyectoActivity.class);
                }else {
                    intent = new Intent(this, ProyectosIntegranteActivity.class);
                }
                startActivity(intent);
            } catch (Exception e) {
                imprimirMensaje(e.getMessage());
            }
        }
    }

    private void imprimirMensaje(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }
}
