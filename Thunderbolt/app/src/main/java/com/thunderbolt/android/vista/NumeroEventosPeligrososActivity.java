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
import com.db.android.constantes.EnrutamientoDeAcometida;
import com.db.android.constantes.EstructuraEnEvaluacion;
import com.db.android.constantes.TransformadorEnAcometida;
import com.db.android.facade.DimensionFacade;
import com.db.android.facade.DimensionFacadeLocal;
import com.db.android.facade.NumeroEventosPeligrososFacade;
import com.db.android.facade.NumeroEventosPeligrososFacadeLocal;
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

public class NumeroEventosPeligrososActivity extends AppCompatActivity implements View.OnClickListener {

    private Proyecto proyecto;
    private String calculo;
    private Dialog customDialog;
    private EditText largo, ancho, alto, longitudS3;
    private TextView varlosAnchoS1, varlosAltoS1, valorLargoS1, estructuraEnEvaluacionDescripcion, varlosAnchoS2, valorLargoS2, ambienteDescripcion, enrutamientoDescripcion, transformadorDescripcion;
    private ImageButton imgBEnumS1, imgBDimensionS1, imgBEnumS3Ambiente, imgBEnumS3Enrutamiento, imgBEnumS3Transformador;
    private ImageView imgVEstatusS1, imgVEstatusS2, imgVEstatusS3,imgVEstatusS4, imageViewArrowS1, imageViewArrowS2, imageViewArrowS3, imageViewArrowS4;
    private LinearLayout linearDimensionesS1, expandibleS1, expandibleS2, expandibleS3, expandibleS4, linearLayoutS1, linearLayoutS2, linearLayoutS3, linearLayoutS4, linearEstructuraEnEvaluacion, linearAmbiente, linearTransformador, linearEnrutamiento;
    private Button btnCalcularS1, btnCalcularS2, btnCalcularS3, btnCalcularS4;
    private ProyectoFacadeLocal proyectoFacadeLocal;
    private DimensionFacadeLocal dimensionFacadeLocal;
    private NumeroEventosPeligrososFacadeLocal numeroEventosPeligrososFacadeLocal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numero_eventos_peligrosos);
        Intent intent = getIntent();

        instanciar();
        inicializar();

        if (intent.getExtras() != null && intent.getExtras().getSerializable("proyecto") != null) {
            proyecto = (Proyecto) intent.getExtras().getSerializable("proyecto");
            if (proyecto.getNumeroEventosPeligorsos() == null) {
                try {
                    proyecto.setNumeroEventosPeligorsos(new NumeroEventosPeligorsos());
                    proyectoFacadeLocal.crear(proyecto);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        if (intent.getExtras() != null && intent.getExtras().getString("calculo") != null) {
            calculo = intent.getExtras().getString("calculo");
        }
        crearDialogoDimensiones();
        cargarEstructuraEnEvaluacion();
        cargarDimensiones();
        cargarLogintud();
        cargarAmbiente();
        cargarEnrutamiento();
        cargarTransformador();
        abrirCalculo(calculo);
        cargarEstatus();
        setToolbar();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, RealizarCalculosActivity.class);
        intent.putExtra("proyecto", proyecto);
        startActivity(intent);
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
        switch (v.getId()) {
            case R.id.expandible_s1:
                toggle_s1();
                break;
            case R.id.imgBDimensionEstructura:
                reinicarCalculoS1yS2(true);
                dialogoDimensiones();
                break;
            case R.id.imgBEnumS1:
                reinicarCalculoS1yS2(false);
                listaDeEnum(EstructuraEnEvaluacion.mapValuesEnum(), EnumAdapter.enumKeysValues(EstructuraEnEvaluacion.values()), "EstructuraEnEvaluacion", this.getClass(), "Situación Relativa del Edificio o Estructura en Evaluación", "s1");
                break;
            case R.id.btnCalcularS1:
                if (proyecto.getNumeroEventosPeligorsos().getDimensionesEstructura().getAncho() != null && proyecto.getNumeroEventosPeligorsos().getEstructuraEnEvaluacion() != null) {
                    proyecto.getNumeroEventosPeligorsos().setCalculo_np(Calculos.s1Nd(proyecto));
                    guardarProyecto();
                    btnCalcularS1.setText(new StringBuilder().append("Np= ").append(proyecto.getNumeroEventosPeligorsos().getCalculo_np().toString()));
                    btnCalcularS1.setEnabled(false);
                    imgVEstatusS1.setImageResource(R.mipmap.estatus_completo);
                } else {
                    Toast.makeText(ContextProvider.getContext(), "debe cargar las dimensiones y la Situación relativa del Edificio para generar el calculo", Toast.LENGTH_SHORT).show();

                }
                break;
            case R.id.expandible_s2:
                toggle_s2();
                break;
            case R.id.btnCalcularS2:
                if (proyecto.getNumeroEventosPeligorsos().getDimensionesEstructura().getAncho() != null) {
                    proyecto.getNumeroEventosPeligorsos().setCalculo_nm(Calculos.S2Nm(proyecto));
                    guardarProyecto();
                    btnCalcularS2.setText(new StringBuilder().append("Nm= ").append(proyecto.getNumeroEventosPeligorsos().getCalculo_nm().toString()));
                    btnCalcularS2.setEnabled(false);
                    imgVEstatusS2.setImageResource(R.mipmap.estatus_completo);
                } else {
                    Toast.makeText(ContextProvider.getContext(), "Debe cargar la dimension en S1", Toast.LENGTH_SHORT).show();

                }
                break;
            case R.id.expandible_s3:
                toggle_s3();
                break;
            case R.id.imgBEnumS3Ambiente:
                //  reinicarCalculoS1yS2(false);
                listaDeEnum(Ambiente.mapValuesEnum(), EnumAdapter.enumKeysValues(Ambiente.values()), "Ambiente", this.getClass(), "Ambiente", "s3");
                break;
            case R.id.imgBEnumS3Enrutamiento:
                // reinicarCalculoS1yS2(false);
                listaDeEnum(EnrutamientoDeAcometida.mapValuesEnum(), EnumAdapter.enumKeysValues(EnrutamientoDeAcometida.values()), "EnrutamientoDeAcometida", this.getClass(), "Enrutamiento de Acometida", "s3");
                break;
            case R.id.imgBEnumS3Transformador:
                //  reinicarCalculoS1yS2(false);
                listaDeEnum(TransformadorEnAcometida.mapValuesEnum(), EnumAdapter.enumKeysValues(TransformadorEnAcometida.values()), "TransformadorEnAcometida", this.getClass(), "Transformador en Acometida", "s3");
                break;
            case R.id.btnCalcularS3:
                if (longitudS3.getText() != null && !longitudS3.getText().toString().equals("")) {
                    proyecto.getNumeroEventosPeligorsos().setLongitud_de_la_acometida(Integer.valueOf(longitudS3.getText().toString()));
                }
                if (proyecto.getNumeroEventosPeligorsos().getLongitud_de_la_acometida() != null && proyecto.getNumeroEventosPeligorsos().getEnrutamientoDeAcometida() != null && proyecto.getNumeroEventosPeligorsos().getAmbiente() != null && proyecto.getNumeroEventosPeligorsos().getTransformadorEnAcometida() != null) {
                    proyecto.getNumeroEventosPeligorsos().setCalculo_nl(Calculos.s3Nl(proyecto));
                    guardarProyecto();
                    btnCalcularS3.setText(new StringBuilder().append("Nl= ").append(proyecto.getNumeroEventosPeligorsos().getCalculo_nm().toString()));
                    btnCalcularS3.setEnabled(false);
                    imgVEstatusS3.setImageResource(R.mipmap.estatus_completo);
                } else {
                    Toast.makeText(ContextProvider.getContext(), "Debe cargar todas las opciones", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.expandible_s4:
                toggle_s4();
                break;
            case R.id.btnCalcularS4:
                if (proyecto.getNumeroEventosPeligorsos().getLongitud_de_la_acometida() != null && proyecto.getNumeroEventosPeligorsos().getEnrutamientoDeAcometida() != null && proyecto.getNumeroEventosPeligorsos().getAmbiente() != null && proyecto.getNumeroEventosPeligorsos().getTransformadorEnAcometida() != null) {
                    proyecto.getNumeroEventosPeligorsos().setCalculo_ni(Calculos.s4Ni(proyecto));
                    guardarProyecto();
                    btnCalcularS4.setText(new StringBuilder().append("Ni= ").append(proyecto.getNumeroEventosPeligorsos().getCalculo_ni().toString()));
                    btnCalcularS4.setEnabled(false);
                    imgVEstatusS4.setImageResource(R.mipmap.estatus_completo);
                } else {
                    Toast.makeText(ContextProvider.getContext(), "Debe cargar todas las opciones", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    public void toggle_s1() {
        linearLayoutS1.setVisibility(linearLayoutS1.isShown() ? View.GONE : View.VISIBLE);
        imageViewArrowS1.setImageResource(linearLayoutS1.isShown() ? R.mipmap.flecha_arriba : R.mipmap.flecha_abajo);

        linearLayoutS2.setVisibility(View.GONE);
        imageViewArrowS2.setImageResource(R.mipmap.flecha_abajo);
        linearLayoutS3.setVisibility(View.GONE);
        imageViewArrowS3.setImageResource(R.mipmap.flecha_abajo);
        linearLayoutS4.setVisibility(View.GONE);
        imageViewArrowS4.setImageResource(R.mipmap.flecha_abajo);
    }

    public void toggle_s2() {
        linearLayoutS2.setVisibility(linearLayoutS2.isShown() ? View.GONE : View.VISIBLE);
        imageViewArrowS2.setImageResource(linearLayoutS2.isShown() ? R.mipmap.flecha_arriba : R.mipmap.flecha_abajo);

        linearLayoutS1.setVisibility(View.GONE);
        imageViewArrowS1.setImageResource(R.mipmap.flecha_abajo);
        linearLayoutS3.setVisibility(View.GONE);
        imageViewArrowS3.setImageResource(R.mipmap.flecha_abajo);
        linearLayoutS4.setVisibility(View.GONE);
        imageViewArrowS4.setImageResource(R.mipmap.flecha_abajo);
    }

    public void toggle_s3() {
        linearLayoutS3.setVisibility(linearLayoutS3.isShown() ? View.GONE : View.VISIBLE);
        imageViewArrowS3.setImageResource(linearLayoutS3.isShown() ? R.mipmap.flecha_arriba : R.mipmap.flecha_abajo);

        linearLayoutS1.setVisibility(View.GONE);
        imageViewArrowS1.setImageResource(R.mipmap.flecha_abajo);
        linearLayoutS2.setVisibility(View.GONE);
        imageViewArrowS2.setImageResource(R.mipmap.flecha_abajo);
        linearLayoutS4.setVisibility(View.GONE);
        imageViewArrowS4.setImageResource(R.mipmap.flecha_abajo);
    }

    public void toggle_s4(){
        linearLayoutS4.setVisibility(linearLayoutS4.isShown() ? View.GONE : View.VISIBLE);
        imageViewArrowS4.setImageResource(linearLayoutS4.isShown() ? R.mipmap.flecha_arriba : R.mipmap.flecha_abajo);

        linearLayoutS1.setVisibility(View.GONE);
        imageViewArrowS1.setImageResource(R.mipmap.flecha_abajo);
        linearLayoutS2.setVisibility(View.GONE);
        imageViewArrowS2.setImageResource(R.mipmap.flecha_abajo);
        linearLayoutS3.setVisibility(View.GONE);
        imageViewArrowS3.setImageResource(R.mipmap.flecha_abajo);

    }

    public void dialogoDimensiones() {
        customDialog.show();
    }

    public void crearDialogoDimensiones() {
        customDialog = new Dialog(this);
        customDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        customDialog.setCancelable(false);
        customDialog.setContentView(R.layout.dialogo_dimensiones_estructura);
        largo = (EditText) customDialog.findViewById(R.id.largo);
        ancho = (EditText) customDialog.findViewById(R.id.ancho);
        alto = (EditText) customDialog.findViewById(R.id.alto);
        if (proyecto.getNumeroEventosPeligorsos().getDimensionesEstructura() != null && proyecto.getNumeroEventosPeligorsos().getDimensionesEstructura().getAncho() != null) {
            largo.setText(proyecto.getNumeroEventosPeligorsos().getDimensionesEstructura().getLargo().toString());
            ancho.setText(proyecto.getNumeroEventosPeligorsos().getDimensionesEstructura().getAncho().toString());
            alto.setText(proyecto.getNumeroEventosPeligorsos().getDimensionesEstructura().getAlto().toString());
        }
        ((Button) customDialog.findViewById(R.id.aceptarDimensiones)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!alto.getText().toString().equals("") && !ancho.getText().toString().equals("") && !largo.getText().toString().equals("")) {
                    proyecto.getNumeroEventosPeligorsos().setDimensionesEstructura(new DimensionesEstructura());
                    proyecto.getNumeroEventosPeligorsos().getDimensionesEstructura().setAlto(Float.valueOf(alto.getText().toString()));
                    proyecto.getNumeroEventosPeligorsos().getDimensionesEstructura().setAncho(Float.valueOf(ancho.getText().toString()));
                    proyecto.getNumeroEventosPeligorsos().getDimensionesEstructura().setLargo(Float.valueOf(largo.getText().toString()));
                    guardarProyecto();
                    cargarDimensiones();
                    linearDimensionesS1.setVisibility(View.VISIBLE);
                    customDialog.dismiss();
                } else {
                    Toast.makeText(ContextProvider.getContext(), "Debe llenar todos los campos", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void cargarDimensiones() {
        if (proyecto.getNumeroEventosPeligorsos().getDimensionesEstructura() != null && proyecto.getNumeroEventosPeligorsos().getDimensionesEstructura().getLargo() != null) {
            valorLargoS1.setText(String.valueOf(proyecto.getNumeroEventosPeligorsos().getDimensionesEstructura().getLargo()));
            varlosAnchoS1.setText(String.valueOf(proyecto.getNumeroEventosPeligorsos().getDimensionesEstructura().getAncho()));
            varlosAltoS1.setText(String.valueOf(proyecto.getNumeroEventosPeligorsos().getDimensionesEstructura().getAlto()));
            valorLargoS2.setText(String.valueOf(proyecto.getNumeroEventosPeligorsos().getDimensionesEstructura().getLargo()));
            varlosAnchoS2.setText(String.valueOf(proyecto.getNumeroEventosPeligorsos().getDimensionesEstructura().getAncho()));
            linearDimensionesS1.setVisibility(View.VISIBLE);
        }
    }

    public void cargarEstructuraEnEvaluacion() {
        if (proyecto.getNumeroEventosPeligorsos().getEstructuraEnEvaluacion() != null) {
            estructuraEnEvaluacionDescripcion.setText(proyecto.getNumeroEventosPeligorsos().getEstructuraEnEvaluacion().getdescripcion());
            linearEstructuraEnEvaluacion.setVisibility(View.VISIBLE);
        }

    }

    public void cargarAmbiente() {
        if (proyecto.getNumeroEventosPeligorsos().getAmbiente() != null) {
            ambienteDescripcion.setText(proyecto.getNumeroEventosPeligorsos().getAmbiente().getdescripcion());
            linearAmbiente.setVisibility(View.VISIBLE);
        }
    }


    public void cargarEnrutamiento() {
        if (proyecto.getNumeroEventosPeligorsos().getEnrutamientoDeAcometida() != null) {
            enrutamientoDescripcion.setText(proyecto.getNumeroEventosPeligorsos().getEnrutamientoDeAcometida().getdescripcion());
            linearEnrutamiento.setVisibility(View.VISIBLE);
        }
    }

    public void cargarTransformador() {
        if (proyecto.getNumeroEventosPeligorsos().getTransformadorEnAcometida() != null) {
            transformadorDescripcion.setText(proyecto.getNumeroEventosPeligorsos().getTransformadorEnAcometida().getdescripcion());
            linearTransformador.setVisibility(View.VISIBLE);
        }
    }

    public void listaDeEnum(Map<String, String[]> map, List<String> list, String c, Class a, String titulo, String calculo) {
        Intent intentEnums = new Intent(this, ListaDeEnums.class);
        intentEnums.putExtra("proyecto", proyecto);
        intentEnums.putExtra("titulo", titulo);
        intentEnums.putExtra("enumSeleccionado", (Serializable) map);
        intentEnums.putExtra("titulosEnum", (Serializable) list);
        intentEnums.putExtra("clase", c);
        intentEnums.putExtra("actividad", (Serializable) a);
        intentEnums.putExtra("calculo", calculo);
        startActivity(intentEnums);
    }

    public void abrirCalculo(String calculo) {
        if (calculo != null) {
            switch (calculo) {
                case "s1":
                    linearLayoutS1.setVisibility(View.VISIBLE);
                    break;
                case "s2":
                    linearLayoutS2.setVisibility(View.VISIBLE);
                    break;
                case "s3":
                    linearLayoutS3.setVisibility(View.VISIBLE);
                    break;
            }
        }
    }

    public void cargarEstatus() {
        if (proyecto.getNumeroEventosPeligorsos().getCalculo_np() != null) {
            imgVEstatusS1.setImageResource(R.mipmap.estatus_completo);
            btnCalcularS1.setText(new StringBuilder().append("Np= ").append(proyecto.getNumeroEventosPeligorsos().getCalculo_np().toString()));
            btnCalcularS1.setEnabled(false);
        }

        if (proyecto.getNumeroEventosPeligorsos().getCalculo_nm() != null) {
            imgVEstatusS2.setImageResource(R.mipmap.estatus_completo);
            btnCalcularS2.setText(new StringBuilder().append("Nm= ").append(proyecto.getNumeroEventosPeligorsos().getCalculo_nm().toString()));
            btnCalcularS2.setEnabled(false);
        }

        if(proyecto.getNumeroEventosPeligorsos().getCalculo_nl() != null) {
            imgVEstatusS3.setImageResource(R.mipmap.estatus_completo);
            btnCalcularS3.setText(new StringBuilder().append("Nl= ").append(proyecto.getNumeroEventosPeligorsos().getCalculo_nl().toString()));
            btnCalcularS3.setEnabled(false);
        }

        if(proyecto.getNumeroEventosPeligorsos().getCalculo_ni() != null) {
            imgVEstatusS4.setImageResource(R.mipmap.estatus_completo);
            btnCalcularS4.setText(new StringBuilder().append("Ni= ").append(proyecto.getNumeroEventosPeligorsos().getCalculo_ni().toString()));
            btnCalcularS4.setEnabled(false);
        }
    }

    public void cargarLogintud() {
        if (proyecto.getNumeroEventosPeligorsos().getLongitud_de_la_acometida() != null) {
            longitudS3.setText(proyecto.getNumeroEventosPeligorsos().getLongitud_de_la_acometida().toString());
        }
    }

    public void reinicarCalculoS1yS2(boolean dimension) {
        imgVEstatusS1.setImageResource(R.mipmap.estatus_incompleto);
        btnCalcularS1.setText("Calcular");
        btnCalcularS1.setEnabled(true);
        if (dimension) {
            imgVEstatusS2.setImageResource(R.mipmap.estatus_incompleto);
            btnCalcularS2.setText("Calcular");
            btnCalcularS2.setEnabled(true);
            proyecto.getNumeroEventosPeligorsos().setCalculo_nm(null);
        }
        proyecto.getNumeroEventosPeligorsos().setCalculo_np(null);
        guardarProyecto();
    }

    public void instanciar() {
        proyectoFacadeLocal = new ProyectoFacade();
        dimensionFacadeLocal = new DimensionFacade();
        numeroEventosPeligrososFacadeLocal = new NumeroEventosPeligrososFacade();
        expandibleS1 = (LinearLayout) findViewById(R.id.expandible_s1);
        expandibleS2 = (LinearLayout) findViewById(R.id.expandible_s2);
        expandibleS3 = (LinearLayout) findViewById(R.id.expandible_s3);
        expandibleS4 = (LinearLayout) findViewById(R.id.expandible_s4);
        linearLayoutS1 = (LinearLayout) findViewById(R.id.toogleS1);
        linearLayoutS2 = (LinearLayout) findViewById(R.id.toogleS2);
        linearLayoutS3 = (LinearLayout) findViewById(R.id.toogleS3);
        linearLayoutS4= (LinearLayout) findViewById(R.id.toogleS4);
        imgBDimensionS1 = (ImageButton) findViewById(R.id.imgBDimensionEstructura);
        varlosAltoS1 = (TextView) findViewById(R.id.ValorAltoS1);
        varlosAnchoS1 = (TextView) findViewById(R.id.ValorAnchoS1);
        valorLargoS1 = (TextView) findViewById(R.id.ValorLargoS1);
        varlosAnchoS2 = (TextView) findViewById(R.id.ValorAnchoS2);
        valorLargoS2 = (TextView) findViewById(R.id.ValorLargoS2);
        imgBEnumS1 = (ImageButton) findViewById(R.id.imgBEnumS1);
        imgBEnumS3Ambiente = (ImageButton) findViewById(R.id.imgBEnumS3Ambiente);
        imgBEnumS3Enrutamiento = (ImageButton) findViewById(R.id.imgBEnumS3Enrutamiento);
        imgBEnumS3Transformador = (ImageButton) findViewById(R.id.imgBEnumS3Transformador);
        imageViewArrowS1 = (ImageView) findViewById(R.id.imageViewArrowS1);
        imageViewArrowS2 = (ImageView) findViewById(R.id.imageViewArrowS2);
        imageViewArrowS3 = (ImageView) findViewById(R.id.imageViewArrowS3);
        imageViewArrowS4 = (ImageView) findViewById(R.id.imageViewArrowS4);
        imgVEstatusS1 = (ImageView) findViewById(R.id.imgVEstatusS1);
        imgVEstatusS2 = (ImageView) findViewById(R.id.imgVEstatusS2);
        imgVEstatusS3 = (ImageView) findViewById(R.id.imgVEstatusS3);
        imgVEstatusS4 = (ImageView) findViewById(R.id.imgVEstatusS4);
        linearDimensionesS1 = (LinearLayout) findViewById(R.id.linearDimensionesS1);
        linearEstructuraEnEvaluacion = (LinearLayout) findViewById(R.id.linearEstructuraEnEvaluacion);
        linearAmbiente = (LinearLayout) findViewById(R.id.linearAmbiente);
        linearEnrutamiento = (LinearLayout) findViewById(R.id.linearEnrutamiento);
        linearTransformador = (LinearLayout) findViewById(R.id.linearTransformador);
        estructuraEnEvaluacionDescripcion = (TextView) findViewById(R.id.estructuraEnEvaluacionDescripcion);
        btnCalcularS1 = (Button) findViewById(R.id.btnCalcularS1);
        btnCalcularS2 = (Button) findViewById(R.id.btnCalcularS2);
        btnCalcularS3 = (Button) findViewById(R.id.btnCalcularS3);
        btnCalcularS4 = (Button) findViewById(R.id.btnCalcularS4);
        longitudS3 = (EditText) findViewById(R.id.longitudS3);
        ambienteDescripcion = (TextView) findViewById(R.id.ambienteDescripcion);
        enrutamientoDescripcion = (TextView) findViewById(R.id.EnrutamientoDescripcion);
        transformadorDescripcion = (TextView) findViewById(R.id.transformadorDescripcion);
    }

    public void inicializar() {
        btnCalcularS1.setOnClickListener(this);
        btnCalcularS2.setOnClickListener(this);
        btnCalcularS3.setOnClickListener(this);
        btnCalcularS4.setOnClickListener(this);
        imgBDimensionS1.setOnClickListener(this);
        expandibleS1.setOnClickListener(this);
        expandibleS2.setOnClickListener(this);
        expandibleS3.setOnClickListener(this);
        expandibleS4.setOnClickListener(this);
        imgBEnumS1.setOnClickListener(this);
        imgBEnumS3Ambiente.setOnClickListener(this);
        imgBEnumS3Enrutamiento.setOnClickListener(this);
        imgBEnumS3Transformador.setOnClickListener(this);
        linearEstructuraEnEvaluacion.setVisibility(View.GONE);
        linearDimensionesS1.setVisibility(View.GONE);
        linearLayoutS1.setVisibility(View.GONE);
        linearLayoutS2.setVisibility(View.GONE);
        linearLayoutS3.setVisibility(View.GONE);
        linearLayoutS4.setVisibility(View.GONE);
        linearAmbiente.setVisibility(View.GONE);
        linearTransformador.setVisibility(View.GONE);
        linearEnrutamiento.setVisibility(View.GONE);
    }

    public void guardarProyecto() {
        try {
            proyectoFacadeLocal.crear(proyecto);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

