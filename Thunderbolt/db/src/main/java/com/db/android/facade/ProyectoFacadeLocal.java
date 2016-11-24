package com.db.android.facade;

import com.db.android.model.Proyecto;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Andres y Jess on 20/11/2016.
 */

public interface ProyectoFacadeLocal {

    void crear(Proyecto proyecto) throws SQLException;
    Proyecto buscarPorId(Long id) throws SQLException;
    List<Proyecto> buscarProyectos() throws SQLException;
}
