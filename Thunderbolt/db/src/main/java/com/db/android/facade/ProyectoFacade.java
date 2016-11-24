package com.db.android.facade;

import com.base.android.ContextProvider;
import com.db.android.conection.DBHelper;
import com.db.android.model.Proyecto;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andres y Jess on 20/11/2016.
 */

public class ProyectoFacade implements ProyectoFacadeLocal {
    @Override
    public void crear(Proyecto proyecto) throws SQLException {
        Dao dao = null;
        DBHelper dbHelper= new DBHelper(ContextProvider.getContext());
        dao = dbHelper.getHelper().getProyectoDao();
        dao.createOrUpdate(proyecto);
    }

    @Override
    public Proyecto buscarPorId(Long id) throws SQLException {
        Dao dao = null;
        DBHelper dbHelper= new DBHelper(ContextProvider.getContext());
        dao = dbHelper.getProyectoDao();
        QueryBuilder queryBuilder = dao.queryBuilder();
        return (Proyecto) queryBuilder.where().eq("id", id).queryForFirst();
    }

    @Override
    public List<Proyecto> buscarProyectos() throws SQLException {
        Dao dao = null;
        DBHelper dbHelper= new DBHelper(ContextProvider.getContext());
        dao = dbHelper.getProyectoDao();
        QueryBuilder queryBuilder = dao.queryBuilder();
        if(queryBuilder.query()!=null){
            return (List<Proyecto>) queryBuilder.query();
        }else{
            List<Proyecto> proyectos = new ArrayList<>();
            return proyectos;
        }

    }
}
