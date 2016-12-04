package com.db.android.constantes;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Andres y Jess on 23/11/2016.
 */

public enum PropiedadesDeLaEstructura {

    ESTRUCTURA_NO_PROTEGIDA_POR_UN_LPS("Estructura no protegida por un LPS. Clase de LPS: -", 1.0),
    ESTRUCTURA_PROTEGIDA_POR_UN_LPS_CLASE_IV("Estructura protegida por un LPS. Clase de LPS: IV", 1.0),
    ESTRUCTURA_PROTEGIDA_POR_UN_LPS_CLASE_III("Estructura protegida por un LPS. Clase de LPS: III", 0.1),
    ESTRUCTURA_PROTEGIDA_POR_UN_LPS_CLASE_II("Estructura protegida por un LPS. Clase de LPS: II", 0.05),
    ESTRUCTURA_PROTEGIDA_POR_UN_LPS_CLASE_I("Estructura protegida por un LPS. Clase de LPS: I", 0.02),
    EDIFICIO_O_ESTRUCTURA_CON_DISPOSITIVO_CAPTADOR("Edificio o estrcutura con dispositivo captador  según clase de LPS y fachada de metal o armadura de hormigón como dispoaitivo natural de derivación", 0.01),
    EDIFICIO_O_ESTRUCTURA_CON_TEJADO_METALICO("Edificio o estrcutura con tejado metálico o con dispositivos captadores, a ser posible utilizando componentes naturales, que protejan todas las supra-estructuras de tejado conta descargas directass de rayo,y una fachada de metal o armadura de hormigón como dispositivo natural  de derivación", 0.001);

    private final String descripcion;
    private final Double valor;

    PropiedadesDeLaEstructura(String descripcion, Double valor) {
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
        for(PropiedadesDeLaEstructura vEnum: PropiedadesDeLaEstructura.values()){
            String[] valuesEnum= new String[2];
            valuesEnum[0]= vEnum.descripcion;
            valuesEnum[1]= vEnum.valor.toString();
            values.put(vEnum.name(), valuesEnum);
        }
        return values;
    }
}
