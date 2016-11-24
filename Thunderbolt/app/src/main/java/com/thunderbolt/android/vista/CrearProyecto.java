package com.thunderbolt.android.vista;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;

import com.thunderbolt.android.R;

public class CrearProyecto extends AppCompatActivity implements View.OnClickListener {

    private ImageButton imgBBuscarUsuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_proyecto);
        setToolbar();

        imgBBuscarUsuario = (ImageButton) findViewById(R.id.imgBBuscarUsuario);
        imgBBuscarUsuario.setOnClickListener(this);
    }

    private void setToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setHomeAsUpIndicator(R.drawable.icono_atras);
            ab.setTitle("Crear Proyecto");
            ab.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.imgBBuscarUsuario:
                Intent intent= new Intent(this,UsuariosActivity.class);
                startActivity(intent);
                break;
        }
    }
}
