package com.db.android.constantes;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Andres y Jess on 29/11/2016.
 */

public enum  CalculosPrincipales {

    NUMERO_EVENTOS_PELIGROSOS("Cálculos de números de eventos peligrosos");
    /*PROBABILIDAD_DE_DAÑO("Cálculos de la probabilidad de daño"),
    PERDIDAS_DE_VIDAS_HUMANAS("Cálculos de perdidas consecuentes: Perdidas de vidas humnas"),
    PERDIDAS_SERVICIOS_PUBLICOS("Cálculos de perdidas consecuentes: Perdidas inaceptables de servicios publicos");
*/
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
