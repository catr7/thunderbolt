package com.db.android.conection;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;


import com.base.android.ContextProvider;
import com.db.android.model.Proyecto;
import com.db.android.model.Usuario;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

/**
 * Created by Andres y Jess on 18/11/2016.
 */

public class DBHelper extends OrmLiteSqliteOpenHelper {

    private static final int DB_SCHEME_VERSION = 1;
    private static final String DB_NAME = "smileMobile.sqlite";
    private static DBHelper mDBHelper;

    private Dao<Usuario, Integer> usuarioDao;
    private Dao<Proyecto, Integer> proyectoDao;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_SCHEME_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, Usuario.class);
            TableUtils.createTable(connectionSource, Proyecto.class);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        onCreate(database, connectionSource);
    }

    public Dao<Usuario, Integer> getUsuarioDao() throws SQLException{
        if(usuarioDao==null){
            usuarioDao= getDao(Usuario.class);
        }
        return usuarioDao;
    }

    public Dao<Proyecto, Integer> getProyectoDao() throws SQLException{
        if(proyectoDao==null){
            proyectoDao= getDao(Proyecto.class);
        }
        return proyectoDao;
    }

    public DBHelper getHelper()  {
        if (mDBHelper == null) {
            mDBHelper = OpenHelperManager.getHelper(ContextProvider.getContext(), DBHelper.class);
        }
        return mDBHelper;
    }

    public void closeHelper() {
        if (mDBHelper != null) {
            OpenHelperManager.releaseHelper();
            mDBHelper = null;
        }
    }

    @Override
    public void close() {
        super.close();
    }
}