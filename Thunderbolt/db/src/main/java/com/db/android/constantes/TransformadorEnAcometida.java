package com.db.android.constantes;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Andres y Jess on 23/11/2016.
 */

public enum TransformadorEnAcometida {

    DE_ENERGIA_EN_BAJA_TENSION_TELECOMUNICACIONES_O_LINEA_DE_DATOS("De energía en baja tensión, telecomunicaciones o línea de datos",1.00),
    LINEA_ELECTRICA_DE_ALTO_VOLTAJE_CON_TRANSFORMACION_DE_ALTA_A_BAJA_TENSION("Línea eléctrica de alto voltaje con transformación de alta a baja tensión",0.2);

    private final String descripcion;
    private final Double valor;
    TransformadorEnAcometida(String descripcion, Double valor) {
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
        for(TransformadorEnAcometida vEnum: TransformadorEnAcometida.values()){
            String[] valuesEnum= new String[2];
            valuesEnum[0]= vEnum.descripcion;
            valuesEnum[1]= vEnum.valor.toString();
            values.put(vEnum.name(), valuesEnum);
        }
        return values;
    }
}
