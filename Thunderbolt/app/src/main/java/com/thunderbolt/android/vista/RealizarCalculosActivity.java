package com.thunderbolt.android.vista;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.db.android.constantes.CalculosPrincipales;
import com.db.android.facade.ProyectoFacade;
import com.db.android.facade.ProyectoFacadeLocal;
import com.db.android.model.Proyecto;
import com.thunderbolt.android.R;
import com.thunderbolt.android.vista.adaptador.RecyclerViewAdapterCalculosPrincipales;
import com.thunderbolt.android.vista.adaptador.RecyclerViewAdapterProyectos;
import com.thunderbolt.android.vista.utils.CrearPDF;
import com.thunderbolt.android.vista.utils.EnumAdapter;
import com.thunderbolt.android.vista.utils.EnviarCorreo;

import java.sql.SQLException;
import java.util.Date;

public class RealizarCalculosActivity extends AppCompatActivity implements View.OnClickListener{

    private Proyecto proyecto;
    private Button btnEnviarCorreo;
    private RecyclerView.LayoutManager manager;
    private RecyclerView recyclerView;
    private ProyectoFacadeLocal proyectoFacadeLocal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realizar_calculos);
        setToolbar();
        Intent intent = getIntent();
        btnEnviarCorreo= (Button) findViewById(R.id.btnEnviarCorreo);
        btnEnviarCorreo.setOnClickListener(this);
        proyectoFacadeLocal= new ProyectoFacade();
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

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnEnviarCorreo:
                proyecto.setNombrePDF(new StringBuilder().append(proyecto.getNombreEstructura()).append("-").append(new Date().toString()).toString());
                try {
                    proyectoFacadeLocal.crear(proyecto);
                    CrearPDF.crear(proyecto);
                    EnviarCorreo.enviar(proyecto.getUsuario().getCorreo(),proyecto.getNombrePDF());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
        }
    }
}
