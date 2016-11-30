package com.db.android.constantes;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Andres y Jess on 29/11/2016.
 */

public enum  CalculosPrincipales {

    NUMERO_EVENTOS_PELIGROSOS("Números de Eventos Peligrosos"),
    PROBABILIDAD_DE_DAÑO("Probabilidad de Daño"),
    PERDIDAS_DE_VIDAS_HUMANAS("Perdidas Consecuentes: Perdidas de Vidas Humnas"),
    PERDIDAS_SERVICIOS_PUBLICOS("Perdidas Consecuentes: Perdidas Inaceptables de Servicios Publicos");

    private final String descripcion;

    CalculosPrincipales(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getdescripcion() {
        return descripcion;
    }

    @Override
    public String toString(){
        return descripcion;
    }

    public static Map<String, String[]> mapValuesEnum(){
        Map<String, String[]> values= new HashMap<>();
        for(CalculosPrincipales vEnum: CalculosPrincipales.values()){
            String[] valuesEnum= new String[2];
            valuesEnum[0]= vEnum.descripcion;
            values.put(vEnum.name(), valuesEnum);
        }
        return values;
    }
}
