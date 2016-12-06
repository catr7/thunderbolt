package com.db.android.facade;

import com.base.android.ContextProvider;
import com.db.android.conection.DBHelper;
import com.db.android.model.DimensionesEstructura;
import com.db.android.model.NumeroEventosPeligorsos;
import com.db.android.model.Proyecto;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;

/**
 * Created by Andres y Jess on 6/12/2016.
 */

public class NumeroEventosPeligrososFacade  implements NumeroEventosPeligrososFacadeLocal{

    @Override
    public void crear(NumeroEventosPeligorsos numeroEventosPeligorsos) throws SQLException {
        Dao dao = null;
        DBHelper dbHelper = new DBHelper(ContextProvider.getContext());
        dao = dbHelper.getHelper().getNumeroEventosPeligorsosesDao();
        if(numeroEventosPeligorsos.getDimensionesEstructura()!=null) {
            DimensionFacadeLocal dimensionFacadeLocal = new DimensionFacade();
            dimensionFacadeLocal.crear(numeroEventosPeligorsos.getDimensionesEstructura());
        }
        dao.createOrUpdate(numeroEventosPeligorsos);
    }

    @Override
    public void eliminar(NumeroEventosPeligorsos numeroEventosPeligorsos) throws SQLException {
        Dao dao = null;
        DBHelper dbHelper = new DBHelper(ContextProvider.getContext());
        dao = dbHelper.getHelper().getNumeroEventosPeligorsosesDao();
        dao.delete(numeroEventosPeligorsos);
    }

    @Override
    public NumeroEventosPeligorsos buscarPorId(Long id) throws SQLException {
        Dao dao = null;
        DBHelper dbHelper = new DBHelper(ContextProvider.getContext());
        dao = dbHelper.getNumeroEventosPeligorsosesDao();
        QueryBuilder queryBuilder = dao.queryBuilder();
        return (NumeroEventosPeligorsos) queryBuilder.where().eq("id", id).queryForFirst();
    }
}