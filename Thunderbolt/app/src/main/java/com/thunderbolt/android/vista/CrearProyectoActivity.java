package com.thunderbolt.android.vista;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.db.android.constantes.Estado;
import com.db.android.constantes.Estatus;
import com.db.android.facade.ProyectoFacade;
import com.db.android.facade.ProyectoFacadeLocal;
import com.db.android.model.Proyecto;
import com.db.android.model.Usuario;
import com.thunderbolt.android.R;

import java.sql.SQLException;
import java.util.Date;

public class CrearProyectoActivity extends AppCompatActivity implements View.OnClickListener {

    private Usuario usuarioSeleccionado;
    private Proyecto proyectoNuevo;
    private ImageButton imgBBuscarUsuario;
    private TextView txtVUsuarioSeleccionado;
    private EditText nombreEstructura;
    private EditText pais;
    private EditText direccion;
    private Spinner spinnerEstado;
    private Button btnRealizarCalculos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_proyecto);
        setToolbar();

        nombreEstructura = (EditText) findViewById(R.id.TextENombreEstructuraProyecto);
        pais = (EditText) findViewById(R.id.TextEPaisProyecto);
        direccion = (EditText) findViewById(R.id.TextEDireccionProyecto);
        imgBBuscarUsuario = (ImageButton) findViewById(R.id.imgBBuscarUsuario);
        spinnerEstado = (Spinner) findViewById(R.id.spinnerEstado);
        btnRealizarCalculos = (Button) findViewById(R.id.btnRealizarCalculos);

        imgBBuscarUsuario.setOnClickListener(this);
        spinnerEstado.setAdapter(new ArrayAdapter<Estado>(this, android.R.layout.simple_spinner_item, Estado.values()));
        btnRealizarCalculos.setOnClickListener(this);

        if (proyectoNuevo == null) {
            proyectoNuevo = new Proyecto();
            proyectoNuevo.setFechaCreacion(new Date());
        }
        Intent intent = getIntent(); // gets the previously created intent

        if (intent.getExtras() != null && intent.getExtras().getSerializable("usuario") != null) {
            txtVUsuarioSeleccionado = (TextView) findViewById(R.id.txtVUsuario);
            usuarioSeleccionado = (Usuario) intent.getExtras().getSerializable("usuario");
            proyectoNuevo.setUsuario(usuarioSeleccionado);
            txtVUsuarioSeleccionado.setText(usuarioSeleccionado.getCorreo());
        }
    }

    private void setToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle("Crear Proyecto");
            ab.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imgBBuscarUsuario:
                irAUsuarios();
                break;
            case R.id.btnRealizarCalculos:
                irARealizarCalculos();
                break;
        }
    }

    public void irAUsuarios() {
        Intent intentUsuarios = new Intent(this, UsuariosActivity.class);
        startActivity(intentUsuarios);
        finish();
    }

    public void irARealizarCalculos() {
        if (spinnerEstado.getSelectedItem() != "" && proyectoNuevo.getUsuario() != null) {
            ProyectoFacadeLocal proyectoFacadeLocal= new ProyectoFacade();
            try {
                proyectoNuevo.setEstado((Estado) spinnerEstado.getSelectedItem());
                proyectoNuevo.setNombreEstructura(nombreEstructura.getText().toString());
                proyectoNuevo.setPais(pais.getText().toString());
                proyectoNuevo.setDireccion(direccion.getText().toString());
                proyectoNuevo.setEstatus(Estatus.EN_PROCESO);
                proyectoFacadeLocal.crear(proyectoNuevo);
            } catch (SQLException e) {
                Toast.makeText(this, "Ocurrio un problema al crear el proyecto", Toast.LENGTH_SHORT).show();
            }
            Intent intentCalulos = new Intent(this, RealizarCalculosActivity.class);
            intentCalulos.putExtra("proyecto",proyectoNuevo);
            startActivity(intentCalulos);
            finish();
        } else {
            Toast.makeText(this, "Debe llenar todos los campos", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent= new Intent(this,InicioActivity.class);
        startActivity(intent);
    }

}
