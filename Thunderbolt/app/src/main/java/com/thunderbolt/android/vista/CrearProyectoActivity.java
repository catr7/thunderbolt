package com.thunderbolt.android.vista;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.db.android.constantes.Ambiente;
import com.db.android.constantes.Estado;
import com.db.android.constantes.Estatus;
import com.db.android.facade.ProyectoFacade;
import com.db.android.facade.ProyectoFacadeLocal;
import com.db.android.model.Proyecto;
import com.db.android.model.Usuario;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.thunderbolt.android.R;
import com.thunderbolt.android.vista.utils.EnumAdapter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private  Button pruebaPdf;
    private  Button pruebaCorreo;
    private boolean editar;
    private  ProyectoFacadeLocal proyectoFacadeLocal;


    private Button pruebaListaEnum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_proyecto);
        Intent intent = getIntent();

        pruebaListaEnum= (Button) findViewById(R.id.btnPruebaListaEnum);
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
        pruebaListaEnum.setOnClickListener(this);

        pruebaPdf= (Button)findViewById(R.id.btnPruebaPdf);
        pruebaPdf.setOnClickListener(this);
        pruebaCorreo= (Button)findViewById(R.id.btnPruebaCorreo);
        pruebaCorreo.setOnClickListener(this);
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
        if(intent.getExtras() != null && intent.getExtras().getBoolean("editar")){
            habilitar(true);
        }
        setToolbar();
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
            case R.id.btnPruebaListaEnum:
                Intent intent= new Intent(this, GenericValues.class);
                startActivity(intent);
                break;
            case R.id.btnPruebaPdf:
                crearPdf();
                break;
            case  R.id.btnPruebaCorreo:
                enviarCorreo();
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
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.activity_crear_proyecto);
        Document doc=new Document();
        String outpath= Environment.getExternalStorageDirectory()+"/pdfPrueba.pdf";
        try {
            PdfWriter.getInstance(doc, new FileOutputStream(outpath));
            doc.open();
            Paragraph preface = new Paragraph();
            preface.add(new Paragraph("Datos Del Proyecto",new Font(Font.FontFamily.TIMES_ROMAN, 24, Font.BOLD)));
            doc.add(preface);
            doc.close();
        } catch (FileNotFoundException e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        } catch (DocumentException e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

public void enviarCorreo() {
    Intent i = new Intent(Intent.ACTION_SEND);
    i.setType("text/plain");
    i.putExtra(Intent.EXTRA_EMAIL, new String[]{"carlosandrestorres30@gmail.com"});
    i.putExtra(Intent.EXTRA_SUBJECT, "Calculos del impacto.");
    i.putExtra(Intent.EXTRA_TEXT, "A continuacion se adjunta pdf con los resultados de los calculos del impacto en la estructura.");
    i.putExtra(Intent.EXTRA_STREAM, Uri.parse("file://"+Environment.getExternalStorageDirectory()+"/pdfPrueba.pdf"));
    try {
        startActivity(Intent.createChooser(i, "Enviar Correo con:"));
    } catch (android.content.ActivityNotFoundException ex) {
        Toast.makeText(this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
    }
}
}
