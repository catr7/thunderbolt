package com.db.android.constantes;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Andres y Jess on 23/11/2016.
 */

public enum EstructuraEnEvaluacion {

    RODEADA_POR_ESTRUCTURAS_MAS_ALTAS_O_ARBOLES("La estructura está rodeada por estructuras más altas o por arboles",0.25),
    RODEADA_POR_ESTRUCTURAS_MISMA_ALTURA_O_INFERIOR("La estructura está rodeada por otras estructuras de la misma altura o de altura inferior.",0.5),
    NO_HAY_ESTRUCTURAS_EN_LAS_CERCANIAS("Estructura Aislada; no hay otras estructuras en la cercanía (a una distancia de 3H).",1.00),
    ESTRUCTURA_SITUADA_EN_LA_CIMA_DE_UNA_MONTANNA_O_CUPULA("Estructura Aislada; estructura situada en la cima de un monte o una cúpula.",2.00);

    private final String descripcion;
    private final Double valor;
    EstructuraEnEvaluacion(String descripcion, Double valor) {
        this.descripcion = descripcion;
        this.valor = valor;
    }

    public String getdescripcion() {
        return descripcion;
    }
    public Double getValor(){
        return valor;
    }

    public static Map<String, String[]> mapValuesEnum(){
        Map<String, String[]> values= new HashMap<>();
        for(EstructuraEnEvaluacion vEnum: EstructuraEnEvaluacion.values()){
            String[] valuesEnum= new String[2];
            valuesEnum[0]= vEnum.descripcion;
            valuesEnum[1]= String.valueOf(vEnum.valor);
            values.put(vEnum.name(), valuesEnum);
        }
        return values;
    }
}
