package com.db.android.constantes;

/**
 * Created by Andres y Jess on 23/11/2016.
 */

public enum Ambiente {

    RURAL("Rural",1.00),
    SUBURBANO("Suburbano",0.5),
    URBANO("Urbano",0.1),
    URBANO_CON_EDIFICIOS_ALTOS("Urbano con edificios altos (Mas de 20m)",0.01);


    private final String descripcion;
    private final Double valor;

    Ambiente(String descripcion, Double valor) {
        this.descripcion = descripcion;
        this.valor = valor;
    }

    public String getdescripcion() {
        return descripcion;
    }
    public Double getValor(){
        return valor;
    }
}
