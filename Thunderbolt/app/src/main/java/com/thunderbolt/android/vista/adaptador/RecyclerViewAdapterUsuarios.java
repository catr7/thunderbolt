package com.thunderbolt.android.vista.adaptador;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.db.android.model.Usuario;
import com.thunderbolt.android.R;
import com.thunderbolt.android.vista.CrearProyectoActivity;

import java.util.List;

/**
 * Created by Andres y Jess on 23/11/2016.
 */

public class RecyclerViewAdapterUsuarios extends RecyclerView.Adapter<ViewHolderUsuario> {
    private List<Usuario> usuarios;
    private Context context;

    public RecyclerViewAdapterUsuarios(List<Usuario> usuarios, Context context) {
        this.usuarios = usuarios;
        this.context= context;
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
        holder.direccion.setText(this.usuarios.get(position).getDireccion());
        holder.telefono.setText(this.usuarios.get(position).getTelefono());
        holder.setClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                if (isLongClick) {
                   //aca se puede colocar un dialogo para elimar
                    } else {
                    Intent intent= new Intent(context, CrearProyectoActivity.class);
                    intent.putExtra("correo",usuarios.get(position).getCorreo());
                    context.startActivity(intent);
                    }
            }
        });

    }

    @Override
    public int getItemCount() {
         return usuarios.size() ;
    }




}
