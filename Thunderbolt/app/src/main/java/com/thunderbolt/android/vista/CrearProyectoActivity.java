package com.thunderbolt.android.vista;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
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
    private boolean editar;
    private  ProyectoFacadeLocal proyectoFacadeLocal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_proyecto);
        Intent intent = getIntent();
        proyectoFacadeLocal = new ProyectoFacade();
        nombreEstructura = (EditText) findViewById(R.id.TextENombreEstructuraProyecto);
        pais = (EditText) findViewById(R.id.TextEPaisProyecto);
        direccion = (EditText) findViewById(R.id.TextEDireccionProyecto);
        imgBBuscarUsuario = (ImageButton) findViewById(R.id.imgBBuscarUsuario);
        spinnerEstado = (Spinner) findViewById(R.id.spinnerEstado);
        btnRealizarCalculos = (Button) findViewById(R.id.btnRealizarCalculos);
        txtVUsuarioSeleccionado = (TextView) findViewById(R.id.txtVUsuario);

        imgBBuscarUsuario.setOnClickListener(this);
        spinnerEstado.setAdapter(new ArrayAdapter<Estado>(this, android.R.layout.simple_spinner_item, Estado.values()));
        btnRealizarCalculos.setOnClickListener(this);

        if (proyectoNuevo == null) {
            if (intent.getExtras() != null && intent.getExtras().getSerializable("proyecto") != null) {
                proyectoNuevo = (Proyecto) intent.getExtras().getSerializable("proyecto");
                txtVUsuarioSeleccionado.setText(proyectoNuevo.getUsuario().getCorreo());
                nombreEstructura.setText(proyectoNuevo.getNombreEstructura());
                nombreEstructura.setEnabled(false);
                pais.setText(proyectoNuevo.getPais());
                direccion.setText(proyectoNuevo.getDireccion());
                direccion.setEnabled(false);
                spinnerEstado.setSelection(proyectoNuevo.getEstado().ordinal());
                spinnerEstado.setEnabled(false);
                imgBBuscarUsuario.setEnabled(false);
                editar=false;
            } else {
                proyectoNuevo = new Proyecto();
                proyectoNuevo.setFechaCreacion(new Date());
            }
        }

        setToolbar();

        if (intent.getExtras() != null && intent.getExtras().getSerializable("usuario") != null) {
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
            if (proyectoNuevo.getEstatus()!=null) {
                ab.setTitle("Proyecto");
                btnRealizarCalculos.setText("Realizar Calculos");
            } else {
                ab.setTitle("Crear Proyecto");
                btnRealizarCalculos.setText("Crear Proyecto");
            }
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
            if(proyectoNuevo.getEstatus()==null || editar==true) {
                guardarProyecto();
            }
            Intent intentCalulos = new Intent(this, RealizarCalculosActivity.class);
            intentCalulos.putExtra("proyecto", proyectoNuevo);
            startActivity(intentCalulos);
            finish();
        } else {
            Toast.makeText(this, "Debe llenar todos los campos", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, InicioActivity.class);
        startActivity(intent);
    }

    public void guardarProyecto(){
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
    }

    public void editar(){
        nombreEstructura.setEnabled(true);
        direccion.setEnabled(true);
        spinnerEstado.setEnabled(true);
        imgBBuscarUsuario.setEnabled(true);
        btnRealizarCalculos.setText("Editar Proyecto");
        editar=true;
    }

    public void eliminar(){
        try {
            proyectoFacadeLocal.eliminar(proyectoNuevo);
            onBackPressed();
        } catch (SQLException e) {
            Toast.makeText(this, "Ocurrio un problema al eliminar el proyecto", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (id){
            case R.id.accion_editar:
                editar();
                break;
            case  R.id.accion_eliminar:
                eliminar();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
