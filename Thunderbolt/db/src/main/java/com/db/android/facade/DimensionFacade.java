package com.db.android.facade;

import com.base.android.ContextProvider;
import com.db.android.conection.DBHelper;
import com.db.android.model.DimensionesEstructura;
import com.db.android.model.Proyecto;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;

/**
 * Created by Andres y Jess on 6/12/2016.
 */

public class DimensionFacade implements DimensionFacadeLocal {
    @Override
    public void crear(DimensionesEstructura dimensionesEstructura) throws SQLException {
        Dao dao = null;
        DBHelper dbHelper= new DBHelper(ContextProvider.getContext());
        dao = dbHelper.getHelper().getDimensionesEstructurasDao();
        dao.createOrUpdate(dimensionesEstructura);
    }

    @Override
    public void eliminar(DimensionesEstructura dimensionesEstructura) throws SQLException {
        Dao dao = null;
        DBHelper dbHelper= new DBHelper(ContextProvider.getContext());
        dao = dbHelper.getHelper().getDimensionesEstructurasDao();
        dao.delete(dimensionesEstructura);
    }

    @Override
    public DimensionesEstructura buscarPorId(Long id) throws SQLException {
        Dao dao = null;
        DBHelper dbHelper= new DBHelper(ContextProvider.getContext());
        dao = dbHelper.getDimensionesEstructurasDao();
        QueryBuilder queryBuilder = dao.queryBuilder();
        return (DimensionesEstructura) queryBuilder.where().eq("id", id).queryForFirst();
    }
}
