package com.thunderbolt.android.vista;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.db.android.facade.UsuarioFacade;
import com.db.android.facade.UsuarioFacadeLocal;
import com.db.android.model.Proyecto;
import com.db.android.model.Usuario;
import com.thunderbolt.android.R;

import java.sql.SQLException;

public class CrearUsuarioActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnCrearUsuario;
    private EditText txtENombre;
    private EditText txtEApellido;
    private EditText txtECorreo;
    private EditText txtEDireccion;
    private EditText txtETelefono;
    private boolean editar, crear;
    private Proyecto proyecto;
    private Usuario usuario;
    private UsuarioFacadeLocal usuarioFacadeLocal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_usuario);
        Intent intent = getIntent();
        txtENombre = (EditText) findViewById(R.id.TxtENombreUsuario);
        txtEApellido = (EditText) findViewById(R.id.TextEApellidoUsuario);
        txtECorreo = (EditText) findViewById(R.id.TextECorreoUsuario);
        txtEDireccion = (EditText) findViewById(R.id.TextEDireccionUsuario);
        txtETelefono = (EditText) findViewById(R.id.TextETelefonoUsuario);
        btnCrearUsuario = (Button) findViewById(R.id.btnCrearUsuario);
        btnCrearUsuario.setOnClickListener(this);
        usuarioFacadeLocal = new UsuarioFacade();
        if (intent.getExtras() != null && intent.getExtras().getSerializable("usuario") != null) {
            usuario = (Usuario) intent.getExtras().getSerializable("usuario");
            txtENombre.setText(usuario.getNombre());
            txtEApellido.setText(usuario.getApellido());
            txtECorreo.setText(usuario.getCorreo());
            txtEDireccion.setText(usuario.getDireccion());
            txtETelefono.setText(usuario.getTelefono());
        }else{
            usuario=new Usuario();
        }
        if (intent.getExtras() != null && intent.getExtras().getSerializable("proyecto") != null) {
            proyecto= (Proyecto) intent.getExtras().getSerializable("proyecto");
        }
        if(intent.getExtras() != null && intent.getExtras().getBoolean("editar")){
            editar=true;
        }
        if(intent.getExtras() != null && intent.getExtras().getBoolean("crear")){
            crear=true;
        }
        setToolbar();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnCrearUsuario:
                accionUsuario();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent= new Intent(this,UsuariosActivity.class);
        intent.putExtra("proyecto",proyecto);
        intent.putExtra("editar",editar);
        intent.putExtra("crear",crear);
        startActivity(intent);
    }

    public void accionUsuario() {
        if (txtECorreo.getText() != null && !txtECorreo.getText().toString().toString().equals("")) {
            guardar();
    }else{
            Toast.makeText(this, "Debe ingresar el correo electronico", Toast.LENGTH_SHORT).show();
        }
    }

    public void guardar(){
        usuario.setNombre(txtENombre.getText().toString());
        usuario.setApellido(txtEApellido.getText().toString());
        usuario.setCorreo(txtECorreo.getText().toString());
        usuario.setDireccion(txtEDireccion.getText().toString());
        usuario.setTelefono(txtETelefono.getText().toString());
        try {
            usuarioFacadeLocal.crear(usuario);
            Intent intent = new Intent(this, UsuariosActivity.class);
            intent.putExtra("proyecto",proyecto);
            intent.putExtra("editar",editar);
            intent.putExtra("crear",crear);
            startActivity(intent);
        } catch (SQLException e) {
            Toast.makeText(this, "error al crear el usuario", Toast.LENGTH_SHORT).show();
        }
    }
    public void eliminar() {
        try {
            usuarioFacadeLocal.eliminar(usuario);
        } catch (SQLException e) {
            Toast.makeText(this, "error al eliminar el usuario", Toast.LENGTH_SHORT).show();
        }

    }

    private void setToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar ab = getSupportActionBar();
        if (ab != null) {
            if (usuario.getCorreo() != null) {
                ab.setTitle("Usuario");
                btnCrearUsuario.setText("Editar");
            } else {
                ab.setTitle("Crear Usuario");
                btnCrearUsuario.setText("Crear Usuario");
            }
        }
    }

}
