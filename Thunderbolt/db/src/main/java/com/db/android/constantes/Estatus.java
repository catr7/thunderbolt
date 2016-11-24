package com.db.android.constantes;

/**
 * Created by Andres y Jess on 21/11/2016.
 */

public enum Estatus {

    EN_PROCESO("en proceso"),
    CULMINADO("Culminado");

    private final String descripcion;

    Estatus(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getdescripcion() {
        return descripcion;
    }

}
