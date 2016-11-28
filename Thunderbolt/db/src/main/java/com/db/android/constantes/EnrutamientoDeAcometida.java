package com.db.android.constantes;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Andres y Jess on 23/11/2016.
 */

public enum EnrutamientoDeAcometida {

    ACOMETIDA_AEREA("Acometida Aérea",1.00),
    SUBTERRANEO("Subterránea",0.5),
    ACOMETIDA_SUBTERRANEA("Acometidas subterráneas que se encuentran en su totalidad dentro de un sistema de puesta a tierra tipo malla.",0.01);


    private final String descripcion;
    private final Double valor;
    EnrutamientoDeAcometida(String descripcion, Double valor) {
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
        for(EnrutamientoDeAcometida vEnum: EnrutamientoDeAcometida.values()){
            String[] valuesEnum= new String[2];
            valuesEnum[0]= vEnum.descripcion;
            valuesEnum[1]= vEnum.valor.toString();
            values.put(vEnum.name(), valuesEnum);
        }
        return values;
    }
}
