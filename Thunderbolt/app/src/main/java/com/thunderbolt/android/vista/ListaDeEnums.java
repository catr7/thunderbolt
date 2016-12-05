package com.thunderbolt.android.vista;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.db.android.constantes.Ambiente;
import com.db.android.model.Proyecto;
import com.thunderbolt.android.R;
import com.thunderbolt.android.vista.adaptador.RecyclerViewAdapterGenericValues;
import com.thunderbolt.android.vista.utils.EnumAdapter;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListaDeEnums extends AppCompatActivity {

    private RecyclerView.LayoutManager manager;
    private RecyclerView recyclerView;
    private Map<String, String[]> enumSeleccionado = new HashMap<>();
    private List<String> titulosEnum = new ArrayList<>();
    private Proyecto proyecto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generic_values);
        setToolbar();
        Intent intent = getIntent();
        if (intent.getExtras() != null && intent.getExtras().getSerializable("proyecto") != null) {
            proyecto=(Proyecto)intent.getExtras().getSerializable("proyecto");
        }
        if (intent.getExtras() != null && intent.getExtras().getSerializable("enumSeleccionado") != null) {
            enumSeleccionado=(Map<String, String[]>) intent.getExtras().getSerializable("enumSeleccionado");
        }
        if (intent.getExtras() != null && intent.getExtras().getSerializable("titulosEnum") != null) {
            titulosEnum= (List<String>) intent.getExtras().getSerializable("titulosEnum");
        }

        recyclerView = (RecyclerView) findViewById(R.id.listaValoresGenericos);
        manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        RecyclerViewAdapterGenericValues adaptador = new RecyclerViewAdapterGenericValues(enumSeleccionado, titulosEnum);
        recyclerView.setAdapter(adaptador);
    }

    private void setToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar ab = getSupportActionBar();
        if (ab != null) {
            // Poner ícono del drawer toggle
            ab.setHomeAsUpIndicator(R.drawable.icono_atras);
            ab.setDisplayHomeAsUpEnabled(true);
        }
    }

}
