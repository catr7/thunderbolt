package com.db.android.constantes;

/**
 * Created by Andres y Jess on 24/11/2016.
 */

public enum Estado {
    ANZOATEGUI("Anzóategui",45),
    APURE("Apure",70),
    BOLIVAR("Bolívar",167),
    CARABOBO("Carabobo",12),
    CARACAS("Caracas",5),
    FALCON("Falcón",34),
    GUARICO("Guárico",60),
    MERIDA("Mérida",19),
    MIRANDA("Miranda",12),
    MONAGAS("Monagas",30),
    NUEVA_ESPARTA("Nueva Esparta",2),
    VARGAS("Vargas",5),
    ZULIA ("Zulia",110),;

    private final String descripcion;
    private final float valor;
    Estado(String descripcion, float valor) {
        this.descripcion = descripcion;
        this.valor = valor;
    }

    public String getdescripcion() {
        return descripcion;
    }
    public  float getValor(){
        return valor;
    }

    @Override
    public String toString(){
        return descripcion;
    }
}


