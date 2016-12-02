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
import android.widget.ImageView;
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
import com.thunderbolt.android.vista.utils.CrearPDF;
import com.thunderbolt.android.vista.utils.EnviarCorreo;
import java.sql.SQLException;
import java.util.Date;


public class CrearProyectoActivity extends AppCompatActivity implements View.OnClickListener {

    private Usuario usuarioSeleccionado;
    private Proyecto proyectoNuevo;
    private ImageView imgBBuscarUsuario;
    private TextView txtVUsuarioSeleccionado;
    private EditText txtENombreEstructura;
    private EditText txtEPais;
    private EditText txtEDireccion;
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
        txtENombreEstructura = (EditText) findViewById(R.id.TextENombreEstructuraProyecto);
        txtEPais = (EditText) findViewById(R.id.TextEPaisProyecto);
        txtEDireccion = (EditText) findViewById(R.id.TextEDireccionProyecto);
        imgBBuscarUsuario = (ImageView) findViewById(R.id.imgBBuscarUsuario);
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
                txtENombreEstructura.setText(proyectoNuevo.getNombreEstructura());
                txtENombreEstructura.setEnabled(false);
                txtEPais.setText(proyectoNuevo.getPais());
                txtEDireccion.setText(proyectoNuevo.getDireccion());
                txtEDireccion.setEnabled(false);
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
        if(intent.getExtras() != null && intent.getExtras().getBoolean("editar")){
            habilitar(true);
        }
        if (intent.getExtras() != null && intent.getExtras().getSerializable("usuario") != null) {
            usuarioSeleccionado = (Usuario) intent.getExtras().getSerializable("usuario");
            proyectoNuevo.setUsuario(usuarioSeleccionado);
            txtVUsuarioSeleccionado.setText(usuarioSeleccionado.getCorreo());
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, InicioActivity.class);
        startActivity(intent);
    }

    public void irAUsuarios() {
        Intent intentUsuarios = new Intent(this, UsuariosActivity.class);
        intentUsuarios.putExtra("proyecto", proyectoNuevo);
        intentUsuarios.putExtra("editar",editar);
        startActivity(intentUsuarios);
    }

    public void irARealizarCalculos() {
        if (spinnerEstado.getSelectedItem() != "" && proyectoNuevo.getUsuario() != null) {
            if(proyectoNuevo.getEstatus()==null || editar==true) {
                guardarProyecto();
            }
            if(btnRealizarCalculos.getText().toString().equalsIgnoreCase("Realizar Calculos")) {
                Intent intentCalulos = new Intent(this, RealizarCalculosActivity.class);
                intentCalulos.putExtra("proyecto", proyectoNuevo);
                startActivity(intentCalulos);
            }
            btnRealizarCalculos.setText("Realizar Calculos");
        } else {
            Toast.makeText(this, "Debe llenar todos los campos", Toast.LENGTH_SHORT).show();
        }
    }

    public void guardarProyecto(){
        try {
            proyectoNuevo.setEstado((Estado) spinnerEstado.getSelectedItem());
            proyectoNuevo.setNombreEstructura(txtENombreEstructura.getText().toString());
            proyectoNuevo.setPais(txtEPais.getText().toString());
            proyectoNuevo.setDireccion(txtEDireccion.getText().toString());
            proyectoNuevo.setEstatus(Estatus.EN_PROCESO);
            proyectoFacadeLocal.crear(proyectoNuevo);
            if(editar){
                editar=false;
                habilitar(false);
            }
        } catch (SQLException e) {
            Toast.makeText(this, "Ocurrio un problema al crear el proyecto", Toast.LENGTH_SHORT).show();
        }
    }

    public void habilitar(boolean activo){
        editar=activo;
        txtENombreEstructura.setEnabled(activo);
        txtEDireccion.setEnabled(activo);
        spinnerEstado.setEnabled(activo);
        imgBBuscarUsuario.setEnabled(activo);
        if(editar) {
            btnRealizarCalculos.setText("Editar Proyecto");
        }
    }

    public void eliminar(){
        try {
            proyectoFacadeLocal.eliminar(proyectoNuevo);
            onBackPressed();
        } catch (SQLException e) {
            Toast.makeText(this, "Ocurrio un problema al eliminar el proyecto", Toast.LENGTH_SHORT).show();
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

    private void crearPdf(){
        CrearPDF.crear("catr.pdf");
           }

public void enviarCorreo() {
    EnviarCorreo.enviar("carlosandrestorres30@gmail.com","pdfPrueba.pdf");
}
}
