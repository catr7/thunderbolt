package com.thunderbolt.android.vista.adaptador;

import android.content.ContentProvider;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.base.android.ContextProvider;
import com.db.android.facade.UsuarioFacade;
import com.db.android.facade.UsuarioFacadeLocal;
import com.db.android.model.Proyecto;
import com.db.android.model.Usuario;
import com.thunderbolt.android.R;
import com.thunderbolt.android.vista.CrearProyectoActivity;
import com.thunderbolt.android.vista.CrearUsuarioActivity;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Andres y Jess on 23/11/2016.
 */

public class RecyclerViewAdapterUsuarios extends RecyclerView.Adapter<ViewHolderUsuario> implements View.OnClickListener {
    private List<Usuario> usuarios;
    private Proyecto proyecto;
    private boolean editar, crear;

    public RecyclerViewAdapterUsuarios(List<Usuario> usuarios, Proyecto proyecto, boolean editar,boolean crear) {
        this.usuarios = usuarios;
        this.proyecto = proyecto;
        this.editar= editar;
        this.crear=crear;
    }
    private UsuarioFacadeLocal usuarioFacadeLocal;
    private int posicion;
    @Override
    public ViewHolderUsuario onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cv_usuario, parent, false);
        ViewHolderUsuario pvh = new ViewHolderUsuario(v);
        usuarioFacadeLocal= new UsuarioFacade();
        return pvh;
    }

    @Override
    public void onBindViewHolder(ViewHolderUsuario holder, int position) {
        posicion=position;
        holder.nombre.setText(this.usuarios.get(position).getNombre());
        holder.apellido.setText(this.usuarios.get(position).getApellido());
        holder.correo.setText(this.usuarios.get(position).getCorreo());
        holder.direccion.setText(this.usuarios.get(position).getDireccion());
        holder.telefono.setText(this.usuarios.get(position).getTelefono());
        holder.editar.setOnClickListener(this);
        holder.eliminar.setOnClickListener(this);
        holder.setClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                if (isLongClick) {
                   //aca se puede colocar un dialogo para elimar
                    } else {
                    Intent intent= new Intent(ContextProvider.getContext(), CrearProyectoActivity.class);
                    intent.putExtra("usuario",usuarios.get(position));
                    intent.putExtra("proyecto",proyecto);
                    intent.putExtra("editar",editar);
                    intent.putExtra("crear",crear);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    ContextProvider.getContext().startActivity(intent);
                    }
            }
        });

    }

    @Override
    public int getItemCount() {
         return usuarios.size() ;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.imgBEditarUsuario:
                Intent intent= new Intent(ContextProvider.getContext(), CrearUsuarioActivity.class);
                intent.putExtra("usuario",usuarios.get(posicion));
                intent.putExtra("proyecto",proyecto);
                intent.putExtra("editar",editar);
                intent.putExtra("crear",crear);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                ContextProvider.getContext().startActivity(intent);
                break;
            case R.id.imgBBorrarUsuario:
                try {
                    usuarioFacadeLocal.eliminar(usuarios.get(posicion));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
        }
    }


}
