package com.db.android.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by Andres y Jess on 21/11/2016.
 */
@DatabaseTable(tableName="dimensiones_estructura")
public class DimensionesEstructura implements Serializable {

    @DatabaseField(generatedId = true, allowGeneratedIdInsert = true)
    private Long id;
    @DatabaseField
    private Float largo;
    @DatabaseField
    private Float ancho;
    @DatabaseField
    private Float alto;

    public DimensionesEstructura() {
    }

    public DimensionesEstructura(Long id, Float largo, Float ancho, Float alto) {
        this.id = id;
        this.largo = largo;
        this.ancho = ancho;
        this.alto = alto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getLargo() {
        return largo;
    }

    public void setLargo(Float largo) {
        this.largo = largo;
    }

    public Float getAncho() {
        return ancho;
    }

    public void setAncho(Float ancho) {
        this.ancho = ancho;
    }

    public Float getAlto() {
        return alto;
    }

    public void setAlto(Float alto) {
        this.alto = alto;
    }
}
