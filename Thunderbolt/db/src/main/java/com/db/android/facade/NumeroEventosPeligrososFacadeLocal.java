package com.db.android.facade;

import com.base.android.ContextProvider;
import com.db.android.conection.DBHelper;
import com.db.android.model.NumeroEventosPeligorsos;
import com.db.android.model.Usuario;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andres y Jess on 6/12/2016.
 */

public interface NumeroEventosPeligrososFacadeLocal {
    void crear(NumeroEventosPeligorsos numeroEventosPeligorsos) throws SQLException;
    void eliminar(NumeroEventosPeligorsos numeroEventosPeligorsos)throws SQLException;
    NumeroEventosPeligorsos buscarPorId(Long id) throws SQLException;
    }