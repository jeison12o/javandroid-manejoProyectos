package com.project.pmi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.project.pmi.controller.CtlProyecto;
import com.project.pmi.controller.CtlUsuario;
import com.project.pmi.model.Proyecto;

import java.util.List;

public class MenuProyectoActivity extends AppCompatActivity {

    ListView lstProyecto;
    CtlProyecto ctlProyecto;
    List<Proyecto> lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_proyecto);
        lstProyecto = findViewById(R.id.lstProyecto);
        iniciar();
    }

    @Override
    protected void onStart() {
        super.onStart();
        iniciar();
    }

    private void iniciar() {
        ctlProyecto = new CtlProyecto(this);
        lista = ctlProyecto.listarIdDirector(CtlUsuario.getUsuarioIntante().getId());
        ArrayAdapter<Proyecto> adapter = new ArrayAdapter<Proyecto>(this, android.R.layout.simple_list_item_1, lista);
        lstProyecto.setAdapter(adapter);
        lstProyecto.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MenuProyectoActivity.this, lista.get(position).toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void vistaGestionProyecto(View view) {
        startActivity(new Intent(this, GestionProyectoActivity.class));
    }
}
