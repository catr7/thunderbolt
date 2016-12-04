package com.db.android.constantes;

/**
 * Created by Andres y Jess on 23/11/2016.
 */

public enum ConexionEnLaEntrada {

    INDEFINIDO("Indefinido"),
    NINGUNO("Ninguno"),
    BLINDAJE_QUE_NO_SE_UNE("El blindaje no se une a la misma barra equipotencial de equipos"),
    BLINDAJE_QUE_SE_UNE("El blindaje se une a la misma barra equipotencial de equipos"),
    SIN_CONEXION_CON_LINEAS_EXTERNAS("Sin conexión con lineas externas"),
    AISLAMIENTO_IEC("El aislamiento de las interfaces según IEC 62305-4");

    private final String descripcion;

    ConexionEnLaEntrada(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getdescripcion() {
        return descripcion;
    }
}
