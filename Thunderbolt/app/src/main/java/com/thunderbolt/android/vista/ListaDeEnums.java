package com.thunderbolt.android.vista;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

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
    private TextView txtVTituloEnum;
    private String c, calculo;
    private Class a;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generic_values);
        setToolbar();
        txtVTituloEnum= (TextView) findViewById(R.id.txtVTituloEnum);

        Intent intent = getIntent();
        if(intent.getExtras() != null && intent.getExtras().getString("titulo") != null){
            txtVTituloEnum.setText(intent.getExtras().getString("titulo"));
        }
        if(intent.getExtras() != null && intent.getExtras().getString("calculo") != null){
           calculo= intent.getExtras().getString("calculo");
        }
        if (intent.getExtras() != null && intent.getExtras().getSerializable("proyecto") != null) {
            proyecto=(Proyecto) intent.getExtras().getSerializable("proyecto");
        }
        if (intent.getExtras() != null && intent.getExtras().getSerializable("enumSeleccionado") != null) {
            enumSeleccionado=(Map<String, String[]>) intent.getExtras().getSerializable("enumSeleccionado");
        }
        if (intent.getExtras() != null && intent.getExtras().getSerializable("titulosEnum") != null) {
            titulosEnum= (List<String>) intent.getExtras().getSerializable("titulosEnum");
        }
        if (intent.getExtras() != null && intent.getExtras().getString("clase") != null) {
            c= intent.getExtras().getString("clase");
        }
        if (intent.getExtras() != null && intent.getExtras().getSerializable("actividad") != null) {
            a= (Class) intent.getExtras().getSerializable("actividad");
        }
        recyclerView = (RecyclerView) findViewById(R.id.listaValoresGenericos);
        manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        RecyclerViewAdapterGenericValues adaptador = new RecyclerViewAdapterGenericValues(enumSeleccionado, titulosEnum,proyecto,c,a,calculo);
        recyclerView.setAdapter(adaptador);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent= new Intent(this,a);
        intent.putExtra("proyecto",proyecto);
        intent.putExtra("calculo",calculo);
        startActivity(intent);
    }


    private void setToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle("Seleccione");
            ab.setHomeAsUpIndicator(R.drawable.icono_atras);
            ab.setDisplayHomeAsUpEnabled(true);
        }
    }

}
