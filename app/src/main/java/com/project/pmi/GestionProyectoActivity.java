package com.project.pmi;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.project.pmi.controller.CtlProyecto;
import com.project.pmi.controller.CtlUsuario;
import com.project.pmi.model.Proyecto;

import java.util.Calendar;

public class GestionProyectoActivity extends AppCompatActivity {

    private CtlProyecto ctlProyecto;
    private TextInputEditText txtNombre, txtFechaFin, txtFechaInicio, txtEtapa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion_proyecto);
        ctlProyecto = new CtlProyecto(this);
        txtNombre = findViewById(R.id.txtProyectoNombre);
        txtFechaInicio = findViewById(R.id.txtProyectoFechaInicio);
        txtFechaFin = findViewById(R.id.txtProyectoFechaFin);
        txtEtapa = findViewById(R.id.txtProyectoEtapa);

        txtFechaInicio.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Calendar mcurrentDate = Calendar.getInstance();
                DatePickerDialog mDatePicker = new DatePickerDialog( GestionProyectoActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            public void onDateSet(DatePicker datepicker, int selectedyear,
                                                  int selectedmonth, int selectedday) {
                                txtFechaInicio.setText(selectedyear + "/" + selectedmonth + "/" + selectedday);
                            }
                        }, mcurrentDate.get(Calendar.YEAR), mcurrentDate.get(Calendar.MONTH),
                        mcurrentDate.get(Calendar.DAY_OF_MONTH));
                mDatePicker.setTitle("Select date");
                mDatePicker.show();
            }
        });

        txtFechaFin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Calendar mcurrentDate = Calendar.getInstance();
                DatePickerDialog mDatePicker = new DatePickerDialog( GestionProyectoActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            public void onDateSet(DatePicker datepicker, int selectedyear,
                                                  int selectedmonth, int selectedday) {
                                txtFechaFin.setText(selectedyear + "/" + selectedmonth + "/" + selectedday);
                            }
                        }, mcurrentDate.get(Calendar.YEAR), mcurrentDate.get(Calendar.MONTH),
                        mcurrentDate.get(Calendar.DAY_OF_MONTH));
                mDatePicker.setTitle("Select date");
                mDatePicker.show();
            }
        });
    }

    public void guardarProyecto(View view) {
        String nombre, fechaInicio, fechaFin, etapa;
        nombre = txtNombre.getText().toString();
        fechaInicio = txtFechaInicio.getText().toString();
        fechaFin = txtFechaFin.getText().toString();
        etapa = txtEtapa.getText().toString();

        if (nombre.isEmpty() || fechaFin.isEmpty() || fechaInicio.isEmpty() || etapa.isEmpty()) {
            imprimirMensaje("debe llenar todos los datos");
        }else {
            Proyecto proyecto = new Proyecto();
            proyecto.setNombre(nombre);
            proyecto.setFechaInicio(fechaInicio);
            proyecto.setFechaFin(fechaFin);
            proyecto.setEtapa(etapa);
            proyecto.setIdDirector(CtlUsuario.getUsuarioIntante().getId());

            try {
                if (ctlProyecto.guardar(proyecto)) {
                    imprimirMensaje("se guardo con exito el proyecto");
                    limpiar();
                }else {
                    imprimirMensaje("error al guardar");
                }
            } catch (Exception e) {
                imprimirMensaje(e.getMessage());
                txtNombre.setText("");
            }
        }
    }

    private void imprimirMensaje(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }

    private void limpiar() {
        txtNombre.setText("");
        txtFechaInicio.setText("");
        txtFechaFin.setText("");
        txtEtapa.setText("");
    }
}
