package com.db.android.facade;

import com.db.android.model.DimensionesEstructura;

import java.sql.SQLException;

/**
 * Created by Andres y Jess on 6/12/2016.
 */

public interface DimensionFacadeLocal {
    void crear(DimensionesEstructura dimensionesEstructura) throws SQLException;
    void eliminar(DimensionesEstructura dimensionesEstructura)throws SQLException;
    DimensionesEstructura  buscarPorId(Long id) throws SQLException;
}
