package com.thunderbolt.android.vista;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.base.android.ContextProvider;
import com.db.android.constantes.Ambiente;
import com.db.android.constantes.EstructuraEnEvaluacion;
import com.db.android.facade.ProyectoFacade;
import com.db.android.facade.ProyectoFacadeLocal;
import com.db.android.model.DimensionesEstructura;
import com.db.android.model.NumeroEventosPeligorsos;
import com.db.android.model.Proyecto;
import com.thunderbolt.android.R;
import com.thunderbolt.android.vista.calculos.Calculos;
import com.thunderbolt.android.vista.utils.EnumAdapter;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class NumeroEventosPeligrososActivity extends AppCompatActivity implements View.OnClickListener{

    private Proyecto proyecto;
    private EditText largo;
    private EditText ancho;
    private EditText alto;
    private TextView varlosAnchoS1;
    private TextView varlosAltoS1;
    private TextView valorLargoS1;
    private TextView estructuraEnEvaluacionDescripcion;
    private TextView estructuraEnEvaluacionValor;
    private ImageButton imgBEnumS1;
    private ImageView imageViewArrowS1;
    private TextView varlosAnchoS2;
    private TextView varlosAltoS2;
    private TextView valorLargoS2;
    private LinearLayout linearDimensionesS1;
    private LinearLayout expandibleS1;
    private LinearLayout linearLayoutS1;
    private LinearLayout linearEstructuraEnEvaluacion;
    private ImageButton imgBDimensionS1;
    private LinearLayout linearLayoutS2;
    private LinearLayout linearLayoutS3;
    private LinearLayout linearLayoutS4;
    private String calculo;
    private Dialog customDialog;
    private ProyectoFacadeLocal proyectoFacadeLocal;
    private Button btnCalcularS1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numero_eventos_peligrosos);
        Intent intent = getIntent();
        proyectoFacadeLocal= new ProyectoFacade();

        expandibleS1= (LinearLayout) findViewById(R.id.expandible_s1);
        linearLayoutS1= (LinearLayout) findViewById(R.id.toogleS1);
        imgBDimensionS1= (ImageButton) findViewById(R.id.imgBDimensionEstructura);
        varlosAltoS1= (TextView) findViewById(R.id.ValorAltoS1);
        varlosAnchoS1= (TextView) findViewById(R.id.ValorAnchoS1);
        valorLargoS1 = (TextView) findViewById(R.id.ValorLargoS1);
        imgBEnumS1=(ImageButton) findViewById(R.id.imgBEnumS1);
        imageViewArrowS1=(ImageView) findViewById(R.id.imageViewArrowS1);
        linearDimensionesS1= (LinearLayout) findViewById(R.id.linearDimensionesS1);
        linearEstructuraEnEvaluacion= (LinearLayout) findViewById(R.id.linearEstructuraEnEvaluacion);
        estructuraEnEvaluacionDescripcion= (TextView) findViewById(R.id.estructuraEnEvaluacionDescripcion);
        estructuraEnEvaluacionValor= (TextView) findViewById(R.id.estructuraEnEvaluacionValor);
        btnCalcularS1= (Button) findViewById(R.id.btnCalcularS1);
        linearEstructuraEnEvaluacion.setVisibility(View.GONE);
        linearDimensionesS1.setVisibility(View.GONE);
        //S1
         btnCalcularS1.setOnClickListener(this);
        imgBDimensionS1.setOnClickListener(this);
        expandibleS1.setOnClickListener(this);
        imgBEnumS1.setOnClickListener(this);
        linearLayoutS1.setVisibility(View.GONE);
        //S2

        //dialogo
        if (intent.getExtras() != null && intent.getExtras().getSerializable("proyecto") != null) {
            proyecto = (Proyecto) intent.getExtras().getSerializable("proyecto");
            if(proyecto.getNumeroEventosPeligorsos()==null) {
                proyecto.setNumeroEventosPeligorsos(new NumeroEventosPeligorsos());
            }
            if(proyecto.getNumeroEventosPeligorsos().getDimensionesEstructura()==null){
            proyecto.getNumeroEventosPeligorsos().setDimensionesEstructura(new DimensionesEstructura());
        }

        }
        if (intent.getExtras() != null && intent.getExtras().getString("calculo") != null) {
            calculo=  intent.getExtras().getString("calculo");
        }
        crearDialogoDimensiones();
        cargarEstructuraEnEvaluacion();
        cargarDimensiones(proyecto.getNumeroEventosPeligorsos().getDimensionesEstructura());
        abrirCalculo(calculo);

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

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.expandible_s1:
                toggle_s1();
                break;
            case R.id.imgBDimensionEstructura:
                dialogoDimensiones();
                break;
            case R.id.imgBEnumS1:
                listaDeEnum(EstructuraEnEvaluacion.mapValuesEnum(), EnumAdapter.enumKeysValues(EstructuraEnEvaluacion.values()),"EstructuraEnEvaluacion",this.getClass());
                break;

            case R.id.btnCalcularS1:
                Double res=  Calculos.nd(proyecto);
                btnCalcularS1.setText(res.toString());
                btnCalcularS1.setEnabled(false);
                break;

        }
    }

    public void toggle_s1(){
        linearLayoutS1.setVisibility(linearLayoutS1.isShown()? View.GONE: View.VISIBLE);
        imageViewArrowS1.setImageResource(linearLayoutS1.isShown()?R.mipmap.flecha_arriba: R.mipmap.flecha_abajo);
    }

    public void dialogoDimensiones(){
  customDialog.show();
    }

    public void crearDialogoDimensiones(){
        customDialog = new Dialog(this);
        customDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        customDialog.setCancelable(false);
        customDialog.setContentView(R.layout.dialogo_dimensiones_estructura);
        largo=(EditText) customDialog.findViewById(R.id.largo);
        ancho= (EditText)customDialog.findViewById(R.id.ancho);
        alto= (EditText) customDialog.findViewById(R.id.alto);
        ((Button) customDialog.findViewById(R.id.aceptarDimensiones)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
              if(!alto.getText().toString().equals("") && !ancho.getText().toString().equals("") && !largo.getText().toString().equals("")  )
                  {

                      proyecto.getNumeroEventosPeligorsos().getDimensionesEstructura().setAlto(Float.valueOf(alto.getText().toString()));
                      proyecto.getNumeroEventosPeligorsos().getDimensionesEstructura().setAncho(Float.valueOf(ancho.getText().toString()));
                      proyecto.getNumeroEventosPeligorsos().getDimensionesEstructura().setLargo(Float.valueOf(largo.getText().toString()));
                      cargarDimensiones(proyecto.getNumeroEventosPeligorsos().getDimensionesEstructura());
                      linearDimensionesS1.setVisibility(View.VISIBLE);
                      try {
                          proyectoFacadeLocal.crear(proyecto);
                      } catch (SQLException e) {
                          e.printStackTrace();
                      }
                      customDialog.dismiss();
                  }else{
                  Toast.makeText(ContextProvider.getContext(), "Debe llenar todos los campos", Toast.LENGTH_SHORT).show();
              }
            }
        });

    }

    public void cargarDimensiones(DimensionesEstructura dimensionesEstructura){
        if(proyecto.getNumeroEventosPeligorsos().getDimensionesEstructura().getLargo()!=null) {
            valorLargoS1.setText(String.valueOf(dimensionesEstructura.getLargo()));
            varlosAnchoS1.setText(String.valueOf(dimensionesEstructura.getAncho()));
            varlosAltoS1.setText(String.valueOf(dimensionesEstructura.getAlto()));
            linearDimensionesS1.setVisibility(View.VISIBLE);
        }
    }

    public void cargarEstructuraEnEvaluacion(){
        if(proyecto.getNumeroEventosPeligorsos().getEstructuraEnEvaluacion()!=null){
            estructuraEnEvaluacionDescripcion.setText(proyecto.getNumeroEventosPeligorsos().getEstructuraEnEvaluacion().getdescripcion());
            estructuraEnEvaluacionValor.setText(proyecto.getNumeroEventosPeligorsos().getEstructuraEnEvaluacion().getValor().toString());
            linearEstructuraEnEvaluacion.setVisibility(View.VISIBLE);
        }

    }

    public void listaDeEnum(Map<String, String[]> map, List<String> list,String c, Class a ){
        Intent intentEnums = new Intent(this,ListaDeEnums.class);
        intentEnums.putExtra("proyecto", proyecto);
        intentEnums.putExtra("enumSeleccionado", (Serializable) map);
        intentEnums.putExtra("titulosEnum", (Serializable) list);
        intentEnums.putExtra("clase", c);
        intentEnums.putExtra("actividad", (Serializable) a);
        startActivity(intentEnums);
    }

    public void abrirCalculo(String calculo){
        if(calculo!=null){
            switch (calculo){
                case "s1":
                    linearLayoutS1.setVisibility(View.VISIBLE);
                    break;

            }
        }
    }
}

