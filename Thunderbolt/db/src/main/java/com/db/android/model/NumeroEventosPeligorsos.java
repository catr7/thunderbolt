package com.db.android.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Andres y Jess on 21/11/2016.
 */
@DatabaseTable(tableName="numeroEventosPeligrosos")
public class NumeroEventosPeligorsos {

    @DatabaseField(generatedId = true, allowGeneratedIdInsert = true)
    private Long id;
    @DatabaseField(foreign = true, foreignAutoCreate = true , foreignAutoRefresh = true, columnName = "fk_dimension_estructura")
     private DimensionesEstructura  dimensionesEstructura;
    estructura_en_evaluacion
            enrutamiento_de_acometida
    transformador_en_acometida
            ambiente
    longitud_de_la_acometida
    @DatabaseField
     private float calculo_np;
    @DatabaseField
    private float calculo_nm;
    @DatabaseField
    private float calculo_nl;
    @DatabaseField
    private float calculo_ni;

}
