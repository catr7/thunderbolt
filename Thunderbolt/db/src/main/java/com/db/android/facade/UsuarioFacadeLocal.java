package com.db.android.facade;

import com.db.android.model.Usuario;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Andres y Jess on 20/11/2016.
 */

public interface UsuarioFacadeLocal {

    void crear(Usuario usuario) throws SQLException;
    Usuario buscarPorNombre(String nombre) throws SQLException;
    List<Usuario> buscarUsuarios() throws SQLException;
}
