package com.db.android.model;

import com.db.android.constantes.Estado;
import com.db.android.constantes.Estatus;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Andres y Jess on 20/11/2016.
 */
@DatabaseTable(tableName="proyecto")
public class Proyecto implements Serializable {

    @DatabaseField(generatedId = true, allowGeneratedIdInsert = true)
    private Long id;
    @DatabaseField
    private String nombreEstructura;
    @DatabaseField
    private String pais;
    @DatabaseField (dataType = DataType.ENUM_STRING)
    private Estado estado;
    @DatabaseField
    private String direccion;
    @DatabaseField(foreign = true, foreignAutoCreate = true , foreignAutoRefresh = true, columnName = "fk_usuario")
    private Usuario usuario;
    @DatabaseField
    private Date fechaCreacion;
    @DatabaseField (dataType = DataType.ENUM_STRING)
    private Estatus estatus;
    @DatabaseField(foreign = true, foreignAutoCreate = true , foreignAutoRefresh = true, columnName = "fk_numero_eventos_peligrosos")
    private  NumeroEventosPeligorsos numeroEventosPeligorsos;
    @DatabaseField
    private String nombrePDF;



    public  Proyecto(){
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreEstructura() {
        return nombreEstructura;
    }

    public void setNombreEstructura(String nombreEstructura) {
        this.nombreEstructura = nombreEstructura;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Estatus getEstatus() {
        return estatus;
    }

    public void setEstatus(Estatus estatus) {
        this.estatus = estatus;
    }

    public NumeroEventosPeligorsos getNumeroEventosPeligorsos() {
        return numeroEventosPeligorsos;
    }

    public void setNumeroEventosPeligorsos(NumeroEventosPeligorsos numeroEventosPeligorsos) {
        this.numeroEventosPeligorsos = numeroEventosPeligorsos;
    }

    public String getNombrePDF() {
        return nombrePDF;
    }

    public void setNombrePDF(String nombrePDF) {
        this.nombrePDF = nombrePDF;
    }
}
