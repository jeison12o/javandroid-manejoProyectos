package com.project.pmi;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.project.pmi.controller.CtlUsuario;
import com.project.pmi.model.Usuario;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class RegistrarUsuarioActivity extends AppCompatActivity {

    private CtlUsuario ctlUsuario;
    private TextInputEditText txtTipoDocumento, txtNumeroDocumento, txtNombre, txtApellidos, txtFechaNacimiento, txtContrasena, txtCorreoElectronico;
    private Spinner spinner ;
    private List<String> items;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_usuario);
        iniciar();
    }

    private void iniciar() {
        ctlUsuario = new CtlUsuario(this);
        txtTipoDocumento = findViewById(R.id.txtRegistroUsuarioTipoDocumento);
        txtNumeroDocumento = findViewById(R.id.txtRegistroUsuarioNumeroDocumento);
        txtNombre = findViewById(R.id.txtRegistroUsuarioNombres);
        txtApellidos = findViewById(R.id.txtRegistroUsuarioApellidos);
        txtFechaNacimiento = findViewById(R.id.txtRegistroUsuarioFechaNacimiento);
        txtContrasena = findViewById(R.id.txtRegistroUsuarioContrasena);
        txtCorreoElectronico = findViewById(R.id.txtRegistroUsuarioCorreoElectronico);
        spinner = findViewById(R.id.spnRegistroUsuarioTipoUsuario);
        items = new ArrayList();
        items.add("Seleccionar su tipo de usuario");
        items.add("director");
        items.add("integrante");
        //android.R.layout.simple_spinner_item
        adapter = new ArrayAdapter<String>(this, R.layout.list_item, items);
        spinner.setAdapter(adapter);

        txtFechaNacimiento.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Calendar mcurrentDate = Calendar.getInstance();
                DatePickerDialog mDatePicker = new DatePickerDialog( RegistrarUsuarioActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            public void onDateSet(DatePicker datepicker, int selectedyear,
                                                  int selectedmonth, int selectedday) {
                                txtFechaNacimiento.setText(selectedyear + "/" + selectedmonth + "/" + selectedday);
                            }
                        }, mcurrentDate.get(Calendar.YEAR), mcurrentDate.get(Calendar.MONTH),
                        mcurrentDate.get(Calendar.DAY_OF_MONTH));
                mDatePicker.setTitle("Select date");
                mDatePicker.show();
            }
        });
    }

    public void guardarUsuario(View view) {
        String tipo, numero, nombre, apellido, fechaNacimiento, contrasena, correo, tipoUsuario;
        tipo = txtTipoDocumento.getText().toString();
        numero = txtNumeroDocumento.getText().toString();
        nombre = txtNombre.getText().toString();
        apellido = txtApellidos.getText().toString();
        fechaNacimiento = txtFechaNacimiento.getText().toString();
        contrasena = txtContrasena.getText().toString();
        correo = txtContrasena.getText().toString();
        tipoUsuario = spinner.getSelectedItem().toString();

        if (tipo.isEmpty() || numero.isEmpty() || nombre.isEmpty() || apellido.isEmpty() || fechaNacimiento.isEmpty() || contrasena.isEmpty() || correo.isEmpty() || tipoUsuario.isEmpty() || tipoUsuario.equals("Seleccionar su tipo de usuario")) {
            imprimirMensaje("debe ingresar todos los datos correctamente");
        }else {
            Usuario usuario = new Usuario();
            usuario.setTipoDocumento(tipo);
            usuario.setNumeroDocumento(numero);
            usuario.setNombres(nombre);
            usuario.setApellidos(apellido);
            usuario.setFechaNacimiento(fechaNacimiento);
            usuario.setContrasena(contrasena);
            usuario.setCorreoElectronico(correo);
            usuario.setTipoUsuario(tipoUsuario);

            try {
                if (ctlUsuario.registrar(usuario)) {
                    imprimirMensaje("se creo su cuenta con exito");
                    limpiar();
                }else {
                    imprimirMensaje("error al crear la cuenta");
                }
            } catch (Exception e) {
                imprimirMensaje(e.getMessage());
            }
        }
    }

    private void imprimirMensaje(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }

    private void limpiar() {
        txtTipoDocumento.setText("");
        txtNumeroDocumento.setText("");
        txtNombre.setText("");
        txtApellidos.setText("");
        txtFechaNacimiento.setText("");
        txtContrasena.setText("");
        txtCorreoElectronico.setText("");
    }
}
