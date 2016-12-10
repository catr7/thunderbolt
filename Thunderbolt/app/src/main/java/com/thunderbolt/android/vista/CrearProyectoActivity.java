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
    private TextView txtVBuscarUsuario;
    private TextView txtVUsuarioNombreSeleccionado;
    private TextView txtVnombreUsuarioEncontrado;
    private TextView txtVCorreoUsuarioEncontrado;
    private TextView txtVCorreoUsuarioSeleccionado;
    private EditText txtENombreEstructura;
    private EditText txtEPais;
    private EditText txtEDireccion;
    private Spinner spinnerEstado;
    private Button btnRealizarCalculos;
    private boolean editar, crear;
    private ProyectoFacadeLocal proyectoFacadeLocal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_proyecto);
        Intent intent = getIntent();

        proyectoFacadeLocal = new ProyectoFacade();

        txtVBuscarUsuario = (TextView) findViewById(R.id.txtVBuscarUsuario);
        txtENombreEstructura = (EditText) findViewById(R.id.TextENombreEstructuraProyecto);
        txtEPais = (EditText) findViewById(R.id.TextEPaisProyecto);
        txtEDireccion = (EditText) findViewById(R.id.TextEDireccionProyecto);
        imgBBuscarUsuario = (ImageView) findViewById(R.id.imgBBuscarUsuario);
        spinnerEstado = (Spinner) findViewById(R.id.spinnerEstado);
        btnRealizarCalculos = (Button) findViewById(R.id.btnRealizarCalculos);
        txtVUsuarioNombreSeleccionado = (TextView) findViewById(R.id.txtVUsuarioNombreSeleccionado);
        txtVCorreoUsuarioEncontrado = (TextView) findViewById(R.id.txtVCoreoUsuarioEncontrado);
        txtVnombreUsuarioEncontrado = (TextView) findViewById(R.id.txtVnombreUsuarioEncontrado);
        txtVCorreoUsuarioSeleccionado = (TextView) findViewById(R.id.txtVCorreoUsuarioSeleccionado);
        imgBBuscarUsuario.setOnClickListener(this);
        spinnerEstado.setAdapter(new ArrayAdapter<Estado>(this, android.R.layout.simple_spinner_item, Estado.values()));
        btnRealizarCalculos.setOnClickListener(this);


        if (intent.getExtras() != null && intent.getExtras().getSerializable("proyecto") != null) {
            proyectoNuevo = (Proyecto) intent.getExtras().getSerializable("proyecto");
                txtENombreEstructura.setText(proyectoNuevo.getNombreEstructura());
                txtEPais.setText(proyectoNuevo.getPais());
                txtEDireccion.setText(proyectoNuevo.getDireccion());
            if(proyectoNuevo.getEstado()!=null) {
                spinnerEstado.setSelection(proyectoNuevo.getEstado().ordinal());
            }
        } else {
            proyectoNuevo = new Proyecto();
        }
        setToolbar();

        if (intent.getExtras() != null) {
            habilitar(intent.getExtras().getBoolean("editar"));
        }
        if (intent.getExtras() != null && intent.getExtras().getSerializable("usuario") != null) {
            usuarioSeleccionado = (Usuario) intent.getExtras().getSerializable("usuario");
            cargarUsuario(usuarioSeleccionado);
            proyectoNuevo.setUsuario(usuarioSeleccionado);
        } else if (proyectoNuevo.getUsuario() != null) {
            usuarioSeleccionado = proyectoNuevo.getUsuario();
            cargarUsuario(usuarioSeleccionado);
        }

        if (intent.getExtras() != null && intent.getExtras().getBoolean("crear")) {
            crear=intent.getExtras().getBoolean("crear");
            habilitar(crear);
            btnRealizarCalculos.setText("Crear Proyecto");
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
        proyectoNuevo.setEstado((Estado) spinnerEstado.getSelectedItem());
        proyectoNuevo.setNombreEstructura(txtENombreEstructura.getText().toString());
        proyectoNuevo.setPais(txtEPais.getText().toString());
        proyectoNuevo.setDireccion(txtEDireccion.getText().toString());
        intentUsuarios.putExtra("proyecto", proyectoNuevo);
        intentUsuarios.putExtra("editar", editar);
        intentUsuarios.putExtra("crear", crear);
        startActivity(intentUsuarios);
    }

    public void cargarUsuario(Usuario usuario) {
        String apellido = "";
        if (editar) {
            txtVBuscarUsuario.setVisibility(View.VISIBLE);
            imgBBuscarUsuario.setVisibility(View.VISIBLE);

        } else {
            imgBBuscarUsuario.setVisibility(View.GONE);
            txtVBuscarUsuario.setVisibility(View.GONE);
        }

        if (usuario.getNombre() != null && usuario.getNombre() != "") {
            txtVnombreUsuarioEncontrado.setVisibility(View.VISIBLE);
            txtVUsuarioNombreSeleccionado.setVisibility(View.VISIBLE);
            if (usuario.getApellido() != null) {
                apellido = usuario.getApellido();
            }
            txtVUsuarioNombreSeleccionado.setText(new StringBuilder().append(usuario.getNombre()).append(" ").append(apellido));
        } else {
            txtVnombreUsuarioEncontrado.setVisibility(View.GONE);
            txtVUsuarioNombreSeleccionado.setVisibility(View.GONE);
        }

        if (usuario.getCorreo() != null || usuario.getCorreo() != "") {
            txtVCorreoUsuarioEncontrado.setVisibility(View.VISIBLE);
            txtVCorreoUsuarioSeleccionado.setText(usuario.getCorreo());
        } else {
            txtVCorreoUsuarioEncontrado.setVisibility(View.GONE);
        }
        if (btnRealizarCalculos.getText().equals("Crear Proyecto")) {
            txtVBuscarUsuario.setVisibility(View.VISIBLE);
            imgBBuscarUsuario.setVisibility(View.VISIBLE);
        }
    }

    public void irARealizarCalculos() {
        if (spinnerEstado.getSelectedItem() != "" && proyectoNuevo.getUsuario() != null) {
            if (proyectoNuevo.getEstatus() == null || editar == true) {
                guardarProyecto();
            }
            if (btnRealizarCalculos.getText().toString().equalsIgnoreCase("Realizar Calculos")) {
                Intent intentCalulos = new Intent(this, RealizarCalculosActivity.class);
                intentCalulos.putExtra("proyecto", proyectoNuevo);
                startActivity(intentCalulos);
            }
            btnRealizarCalculos.setText("Realizar Calculos");
        } else {
            Toast.makeText(this, "Debe llenar todos los campos", Toast.LENGTH_SHORT).show();
        }
    }

    public void guardarProyecto() {
        try {
            proyectoNuevo.setEstado((Estado) spinnerEstado.getSelectedItem());
            proyectoNuevo.setNombreEstructura(txtENombreEstructura.getText().toString());
            proyectoNuevo.setPais(txtEPais.getText().toString());
            proyectoNuevo.setDireccion(txtEDireccion.getText().toString());
            proyectoNuevo.setEstatus(Estatus.EN_PROCESO);
            proyectoFacadeLocal.crear(proyectoNuevo);
            if (editar) {
                editar = false;
                habilitar(false);
            }
        } catch (SQLException e) {
            Toast.makeText(this, "Ocurrio un problema al crear el proyecto", Toast.LENGTH_SHORT).show();
        }
    }

    public void habilitar(boolean activo) {
        editar = activo;
        txtENombreEstructura.setEnabled(activo);
        txtEDireccion.setEnabled(activo);
        spinnerEstado.setEnabled(activo);
        if (editar) {
            btnRealizarCalculos.setText("Editar Proyecto");
        }
    }

    public void eliminar() {
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
            if (proyectoNuevo.getEstatus() != null) {
                ab.setTitle("Proyecto");
                btnRealizarCalculos.setText("Realizar Calculos");
            } else {
                ab.setTitle("Crear Proyecto");
                btnRealizarCalculos.setText("Crear Proyecto");
            }
            ab.setDisplayHomeAsUpEnabled(true);
        }
    }

}
