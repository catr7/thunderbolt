package com.thunderbolt.android.vista.adaptador;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.db.android.model.Usuario;
import com.thunderbolt.android.R;

import java.util.List;

/**
 * Created by Andres y Jess on 23/11/2016.
 */

public class RecyclerViewAdapterUsuarios extends RecyclerView.Adapter<ViewHolderUsuario> {
    private List<Usuario> usuarios;

    public RecyclerViewAdapterUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    @Override
    public ViewHolderUsuario onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cv_usuario, parent, false);
        ViewHolderUsuario pvh = new ViewHolderUsuario(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(ViewHolderUsuario holder, int position) {
        holder.nombre.setText(this.usuarios.get(position).getNombre());
        holder.apellido.setText(this.usuarios.get(position).getApellido());
        holder.correo.setText(this.usuarios.get(position).getCorreo());
        holder.direccion.setText(this.usuarios.get(position).getCorreo());
        holder.telefono.setText(this.usuarios.get(position).getTelefono());
    }

    @Override
    public int getItemCount() {
         return usuarios.size() ;
    }
}
