package com.db.android.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Andres y Jess on 21/11/2016.
 */
@DatabaseTable(tableName="dimensiones_estructura")
public class DimensionesEstructura {

    @DatabaseField(generatedId = true, allowGeneratedIdInsert = true)
    private Long id;
    @DatabaseField
    private float largo;
    @DatabaseField
    private float ancho;
    @DatabaseField
    private float alto;

    public DimensionesEstructura() {
    }

    public DimensionesEstructura(Long id, float largo, float ancho, float alto) {
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

    public float getLargo() {
        return largo;
    }

    public void setLargo(float largo) {
        this.largo = largo;
    }

    public float getAncho() {
        return ancho;
    }

    public void setAncho(float ancho) {
        this.ancho = ancho;
    }

    public float getAlto() {
        return alto;
    }

    public void setAlto(float alto) {
        this.alto = alto;
    }
}
