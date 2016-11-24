package com.thunderbolt.android.vista;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.db.android.facade.UsuarioFacade;
import com.db.android.facade.UsuarioFacadeLocal;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_usuario);
        setToolbar();
        txtENombre =(EditText) findViewById(R.id.TxtENombreUsuario);
        txtEApellido=(EditText) findViewById(R.id.TextEApellidoUsuario);
        txtECorreo= (EditText) findViewById(R.id.TextECorreoUsuario);
        txtEDireccion=(EditText) findViewById(R.id.TextEDireccionUsuario);
        txtETelefono=(EditText) findViewById(R.id.TextETelefonoUsuario);
        btnCrearUsuario = (Button) findViewById(R.id.btnCrearUsuario);
        btnCrearUsuario.setOnClickListener(this);
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
            case R.id.btnCrearUsuario:
                guardarUsuario();
                break;
        }
    }

    public void guardarUsuario(){
        if(txtECorreo.getText()!= null && !txtECorreo.getText().toString().toString().equals("")){
        UsuarioFacadeLocal usuarioFacadeLocal= new UsuarioFacade();
        try {
            usuarioFacadeLocal.crear(new Usuario(txtENombre.getText().toString(),txtEApellido.getText().toString(), txtECorreo.getText().toString(), txtEDireccion.getText().toString(), txtETelefono.getText().toString()));
            Intent intent= new Intent(this,UsuariosActivity.class);
            startActivity(intent);
            finish();
        } catch (SQLException e) {
            Toast.makeText(this, "error al crear el usuario", Toast.LENGTH_SHORT).show();
        }
        }else{
            Toast.makeText(this, "Debe agregar el correo del usuario", Toast.LENGTH_SHORT).show();
        }
    }
}
