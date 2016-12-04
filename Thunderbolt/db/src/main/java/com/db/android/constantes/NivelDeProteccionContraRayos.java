package com.db.android.constantes;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Andres y Jess on 23/11/2016.
 */

public enum NivelDeProteccionContraRayos {

    SIN_PROTECCION_COORDINADA_CONTRA_SOBRETENSIONES("Sin protección coordinada contra sobretensiones",1.00),
    III_IV("III-IV", 0.00),
    II("II", 0.02),
    DISPOSITIVO_DE_PROTECCION_CONTRA_SOBRETENSIONES("Dispositivo de protección contra sobretensiones con una característica de protección mejor que LPL 'I' (Capacidad de derivacion de corriente de rayo superior, nivel de protección mas bajo, entre otros)",0.005);

    private final String descripcion;
    private final Double valor;

    NivelDeProteccionContraRayos(String descripcion, Double valor) {
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
        for(NivelDeProteccionContraRayos vEnum: NivelDeProteccionContraRayos.values()){
            String[] valuesEnum= new String[2];
            valuesEnum[0]= vEnum.descripcion;
            valuesEnum[1]= vEnum.valor.toString();
            values.put(vEnum.name(), valuesEnum);
        }
        return values;
    }

}
