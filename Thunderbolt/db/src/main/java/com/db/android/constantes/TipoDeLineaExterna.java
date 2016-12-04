package com.db.android.constantes;

/**
 * Created by Andres y Jess on 23/11/2016.
 */

public enum TipoDeLineaExterna {

    LINEA_AEREA_SIN_BLINDAJE("Linea aérea sin blindaje", ConexionEnLaEntrada.INDEFINIDO, 1.00, 1.00),
    LINEA_SUBTERRANEA_SIN_BLINDAJE("Linea subterranea sin blindaje", ConexionEnLaEntrada.INDEFINIDO, 1.00, 1.00),
    LINEA_ELECTRICA_CON_CONDUCTOR_NUETRO_PUESTO_A_TIERRA("Linea eléctrica con conductor nuetro puesto a tierra", ConexionEnLaEntrada.NINGUNO, 1.00, 0.2),
    LINEA_SUBTERRANEA_CON_BLINDAJE_QUE_NO_SE_UNE("Linea subterranea con blindaje (Energía o de telecomunicaciones)", ConexionEnLaEntrada.BLINDAJE_QUE_NO_SE_UNE, 1.00, 0.3),
    LINEA_AREA_APANTALLADA_CON_BLINDAJE_QUE_NO_SE_UNE("Linea aárea apantallada (Energía o de telecomunicaciones)", ConexionEnLaEntrada.BLINDAJE_QUE_NO_SE_UNE, 1.00, 0.1),
    LINEA_SUBTERRANEA_CON_BLINDAJE_QUE_SE_UNE("Linea aárea apantallada (Energía o de telecomunicaciones)", ConexionEnLaEntrada.BLINDAJE_QUE_SE_UNE, 1.00, 0.0),
    LINEA_AREA_APANTALLADA_CON_BLINDAJE_QUE_SE_UNE("Linea aárea apantallada (Energía o de telecomunicaciones)", ConexionEnLaEntrada.BLINDAJE_QUE_SE_UNE, 1.00, 0.0),
    CABLE_DE_PROTECCION_CONTRA_RAYOS("Cable de protección contra rayos o cableado en conductos de proteccion contra rayos", ConexionEnLaEntrada.BLINDAJE_QUE_SE_UNE, 0.0, 0.0),
    SIN_LINEA_EXTERNA("Sin linea externa", ConexionEnLaEntrada.SIN_CONEXION_CON_LINEAS_EXTERNAS, 0.0, 0.0),
    CUALQUIER_TIPO("Cualquier tipo", ConexionEnLaEntrada.AISLAMIENTO_IEC, 0.0, 0.0);

    private final String descripcion;
    private final ConexionEnLaEntrada conexionEnLaEntrada;
    private final Double valorCLD;
    private final Double valorCLI;

    TipoDeLineaExterna(String descripcion, ConexionEnLaEntrada conexionEnLaEntrada, Double valorCLD, Double valorCLI) {
        this.descripcion = descripcion;
        this.conexionEnLaEntrada = conexionEnLaEntrada;
        this.valorCLD = valorCLD;
        this.valorCLI = valorCLI;
    }

    public String getdescripcion() {
        return descripcion;
    }

    public ConexionEnLaEntrada getConexionEnLaEntrada() {
        return conexionEnLaEntrada;
    }

    public Double getValorCLD() {
        return valorCLD;
    }

    public Double getValorCLI() {
        return valorCLI;
    }
}
