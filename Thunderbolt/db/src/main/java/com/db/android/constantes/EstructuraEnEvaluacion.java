package com.db.android.constantes;

/**
 * Created by Andres y Jess on 23/11/2016.
 */

public enum EstructuraEnEvaluacion {

    RODEADA_POR_ESTRUCTURAS_MAS_ALTAS_O_ARBOLES("",2),
    RODEADA_POR_ESTRUCTURAS_MISMA_ALTURA_O_INFERIOR("",2),
    NO_HAY_ESTRUCTURAS_EN_LAS_CERCANIAS("",2),
    ESTRUCTURA_SITUADA_EN_LA_CIMA_DE_UNA_MONTANNA_O_CUPULA("",2);

    private final String descripcion;
    private final float valor;
    EstructuraEnEvaluacion(String descripcion, float valor) {
        this.descripcion = descripcion;
        this.valor = valor;
    }

    public String getdescripcion() {
        return descripcion;
    }
    public  float getValor(){
        return valor;
    }
}
