package com.db.android.facade;

import com.base.android.ContextProvider;
import com.db.android.conection.DBHelper;
import com.db.android.model.Proyecto;
import com.db.android.model.Usuario;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andres y Jess on 20/11/2016.
 */

public class UsuarioFacade implements UsuarioFacadeLocal {


    @Override
    public void crear(Usuario usuario) throws SQLException {
        Dao dao = null;
        DBHelper dbHelper= new DBHelper(ContextProvider.getContext());
        dao = dbHelper.getHelper().getUsuarioDao();
        dao.createOrUpdate(usuario);
    }

    @Override
    public Usuario buscarPorNombre(String nombre) throws SQLException {
        Dao dao = null;
        DBHelper dbHelper= new DBHelper(ContextProvider.getContext());
        dao = dbHelper.getUsuarioDao();
        QueryBuilder queryBuilder = dao.queryBuilder();
        return (Usuario) queryBuilder.where().eq("nombre", nombre).queryForFirst();
    }

    @Override
    public List<Usuario> buscarUsuarios() throws SQLException {
        Dao dao = null;
        DBHelper dbHelper= new DBHelper(ContextProvider.getContext());
        dao = dbHelper.getUsuarioDao();
        QueryBuilder queryBuilder = dao.queryBuilder();
        if(queryBuilder.query()!=null){
            return (List<Usuario>) queryBuilder.query();
        }else{
            List<Usuario> usuarios = new ArrayList<>();
            return usuarios;
        }

    }
}
