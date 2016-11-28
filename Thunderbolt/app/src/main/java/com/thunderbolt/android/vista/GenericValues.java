package com.thunderbolt.android.vista;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.db.android.constantes.Ambiente;
import com.thunderbolt.android.R;
import com.thunderbolt.android.vista.adaptador.RecyclerViewAdapterGenericValues;
import com.thunderbolt.android.vista.utils.EnumAdapter;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GenericValues extends AppCompatActivity {

    private RecyclerView.LayoutManager manager;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generic_values);
        setToolbar();
        recyclerView = (RecyclerView) findViewById(R.id.listaValoresGenericos);
        manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        RecyclerViewAdapterGenericValues adaptador = new RecyclerViewAdapterGenericValues(Ambiente.mapValuesEnum(), EnumAdapter.enumKeysValues(Ambiente.values()));
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
