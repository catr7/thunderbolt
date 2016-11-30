package com.thunderbolt.android.vista;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.db.android.model.Proyecto;
import com.thunderbolt.android.R;

public class NumeroEventosPeligrososActivity extends AppCompatActivity {

    private Proyecto proyecto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numero_eventos_peligrosos);
        Intent intent = getIntent();
        if (intent.getExtras() != null && intent.getExtras().getSerializable("proyecto") != null) {
            proyecto = (Proyecto) intent.getExtras().getSerializable("proyecto");
        }
        setToolbar();
    }

    private void setToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle("Numero de Eventos Peligrosos");
            ab.setDisplayHomeAsUpEnabled(true);
        }
    }
}
