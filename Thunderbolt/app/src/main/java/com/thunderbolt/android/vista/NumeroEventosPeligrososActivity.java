package com.thunderbolt.android.vista;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;

import com.db.android.model.Proyecto;
import com.thunderbolt.android.R;

public class NumeroEventosPeligrososActivity extends AppCompatActivity {

    private Proyecto proyecto;
    private LinearLayout linearLayoutS1;
    private LinearLayout linearLayoutS2;
    private LinearLayout linearLayoutS3;
    private LinearLayout linearLayoutS4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numero_eventos_peligrosos);
        Intent intent = getIntent();
        linearLayout= (LinearLayout) findViewById(R.id.prueba2);
        linearLayout.setVisibility(View.GONE);
        if (intent.getExtras() != null && intent.getExtras().getSerializable("proyecto") != null) {
            proyecto = (Proyecto) intent.getExtras().getSerializable("proyecto");
        }
        setToolbar();
    }

    public void toggle_contents(View v){
        linearLayout.setVisibility(linearLayout.isShown()? View.GONE: View.VISIBLE);
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
