package com.db.android.model;

import com.db.android.constantes.Ambiente;
import com.db.android.constantes.EnrutamientoDeAcometida;
import com.db.android.constantes.EstructuraEnEvaluacion;
import com.db.android.constantes.TransformadorEnAcometida;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Andres y Jess on 21/11/2016.
 */
@DatabaseTable(tableName="numeroEventosPeligrosos")
public class NumeroEventosPeligorsos {

    @DatabaseField(generatedId = true, allowGeneratedIdInsert = true)
    private Long id;
    @DatabaseField(foreign = true, foreignAutoCreate = true , foreignAutoRefresh = true, columnName = "fk_dimension_estructura")
     private DimensionesEstructura  dimensionesEstructura;
    @DatabaseField (dataType = DataType.ENUM_STRING)
    private EstructuraEnEvaluacion estructuraEnEvaluacion;
    @DatabaseField (dataType = DataType.ENUM_STRING)
    private EnrutamientoDeAcometida enrutamientoDeAcometida;
    @DatabaseField (dataType = DataType.ENUM_STRING)
    private TransformadorEnAcometida transformadorEnAcometida;
    @DatabaseField (dataType = DataType.ENUM_STRING)
    private Ambiente ambiente;
    @DatabaseField
    private Integer longitud_de_la_acometida;
    @DatabaseField
     private float calculo_np;
    @DatabaseField
    private float calculo_nm;
    @DatabaseField
    private float calculo_nl;
    @DatabaseField
    private float calculo_ni;

    public NumeroEventosPeligorsos() {
    }

    public NumeroEventosPeligorsos(Long id, DimensionesEstructura dimensionesEstructura, EstructuraEnEvaluacion estructuraEnEvaluacion, EnrutamientoDeAcometida enrutamientoDeAcometida, TransformadorEnAcometida transformadorEnAcometida, Ambiente ambiente, Integer longitud_de_la_acometida, float calculo_np, float calculo_nm, float calculo_nl, float calculo_ni) {
        this.id = id;
        this.dimensionesEstructura = dimensionesEstructura;
        this.estructuraEnEvaluacion = estructuraEnEvaluacion;
        this.enrutamientoDeAcometida = enrutamientoDeAcometida;
        this.transformadorEnAcometida = transformadorEnAcometida;
        this.ambiente = ambiente;
        this.longitud_de_la_acometida = longitud_de_la_acometida;
        this.calculo_np = calculo_np;
        this.calculo_nm = calculo_nm;
        this.calculo_nl = calculo_nl;
        this.calculo_ni = calculo_ni;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DimensionesEstructura getDimensionesEstructura() {
        return dimensionesEstructura;
    }

    public void setDimensionesEstructura(DimensionesEstructura dimensionesEstructura) {
        this.dimensionesEstructura = dimensionesEstructura;
    }

    public EstructuraEnEvaluacion getEstructuraEnEvaluacion() {
        return estructuraEnEvaluacion;
    }

    public void setEstructuraEnEvaluacion(EstructuraEnEvaluacion estructuraEnEvaluacion) {
        this.estructuraEnEvaluacion = estructuraEnEvaluacion;
    }

    public EnrutamientoDeAcometida getEnrutamientoDeAcometida() {
        return enrutamientoDeAcometida;
    }

    public void setEnrutamientoDeAcometida(EnrutamientoDeAcometida enrutamientoDeAcometida) {
        this.enrutamientoDeAcometida = enrutamientoDeAcometida;
    }

    public TransformadorEnAcometida getTransformadorEnAcometida() {
        return transformadorEnAcometida;
    }

    public void setTransformadorEnAcometida(TransformadorEnAcometida transformadorEnAcometida) {
        this.transformadorEnAcometida = transformadorEnAcometida;
    }

    public Ambiente getAmbiente() {
        return ambiente;
    }

    public void setAmbiente(Ambiente ambiente) {
        this.ambiente = ambiente;
    }

    public Integer getLongitud_de_la_acometida() {
        return longitud_de_la_acometida;
    }

    public void setLongitud_de_la_acometida(Integer longitud_de_la_acometida) {
        this.longitud_de_la_acometida = longitud_de_la_acometida;
    }

    public float getCalculo_np() {
        return calculo_np;
    }

    public void setCalculo_np(float calculo_np) {
        this.calculo_np = calculo_np;
    }

    public float getCalculo_nm() {
        return calculo_nm;
    }

    public void setCalculo_nm(float calculo_nm) {
        this.calculo_nm = calculo_nm;
    }

    public float getCalculo_nl() {
        return calculo_nl;
    }

    public void setCalculo_nl(float calculo_nl) {
        this.calculo_nl = calculo_nl;
    }

    public float getCalculo_ni() {
        return calculo_ni;
    }

    public void setCalculo_ni(float calculo_ni) {
        this.calculo_ni = calculo_ni;
    }
}
