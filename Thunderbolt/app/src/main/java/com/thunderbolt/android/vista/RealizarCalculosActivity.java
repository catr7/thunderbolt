package com.thunderbolt.android.vista;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.db.android.constantes.CalculosPrincipales;
import com.db.android.model.Proyecto;
import com.thunderbolt.android.R;
import com.thunderbolt.android.vista.adaptador.RecyclerViewAdapterCalculosPrincipales;
import com.thunderbolt.android.vista.adaptador.RecyclerViewAdapterProyectos;
import com.thunderbolt.android.vista.utils.EnumAdapter;

public class RealizarCalculosActivity extends AppCompatActivity {

    private Proyecto proyecto;
    private RecyclerView.LayoutManager manager;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realizar_calculos);
        setToolbar();
        Intent intent = getIntent();
        recyclerView = (RecyclerView) findViewById(R.id.listaCalculosPrincipales);
        if (intent.getExtras() != null && intent.getExtras().getSerializable("proyecto") != null) {
            proyecto= (Proyecto) intent.getExtras().getSerializable("proyecto");
        }
        RecyclerViewAdapterCalculosPrincipales adaptador = new RecyclerViewAdapterCalculosPrincipales(proyecto, EnumAdapter.enumKeysValues(CalculosPrincipales.values()));
        manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adaptador);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, CrearProyectoActivity.class);
        intent.putExtra("proyecto",proyecto);
        startActivity(intent);
    }

    private void setToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle("Realizar Calculos");
        }
    }
}
