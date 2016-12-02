package com.thunderbolt.android.vista;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.db.android.facade.UsuarioFacade;
import com.db.android.facade.UsuarioFacadeLocal;
import com.db.android.model.Proyecto;
import com.db.android.model.Usuario;
import com.thunderbolt.android.R;
import com.thunderbolt.android.vista.adaptador.RecyclerViewAdapterUsuarios;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuariosActivity extends AppCompatActivity implements View.OnClickListener {

    private Proyecto proyecto;
    private RecyclerView.LayoutManager manager;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuarios);
        setToolbar();
        Intent intent = getIntent();
        recyclerView = (RecyclerView) findViewById(R.id.listaUsuarios);
        List<Usuario> usuarios= new ArrayList<>();
        UsuarioFacadeLocal usuarioFacadeLocal = new UsuarioFacade();
        try {
            usuarios.addAll(usuarioFacadeLocal.buscarUsuarios());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (intent.getExtras() != null && intent.getExtras().getSerializable("proyecto") != null) {
            proyecto= (Proyecto) intent.getExtras().getSerializable("proyecto");
        }
        RecyclerViewAdapterUsuarios adaptador = new RecyclerViewAdapterUsuarios(usuarios,proyecto);
        manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adaptador);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.btnNuevoUsuario);
        fab.setOnClickListener(this);

    }

    private void setToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setHomeAsUpIndicator(R.drawable.icono_atras);
            ab.setTitle("Lista de Usuarios");
            ab.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnNuevoUsuario:
                Intent intent= new Intent(this,CrearUsuarioActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent= new Intent(this,CrearProyectoActivity.class);
        startActivity(intent);
    }
}
