package com.thunderbolt.android.vista.adaptador;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.base.android.ContextProvider;
import com.db.android.constantes.Estatus;
import com.db.android.model.Proyecto;
import com.thunderbolt.android.R;
import com.thunderbolt.android.vista.CrearProyectoActivity;

import java.util.List;


/**
 * Created by conamerica36 on 23/10/16.
 */
public class RecyclerViewAdapterProyectos extends RecyclerView.Adapter<ViewHolderProyecto>{
    private List<Proyecto> proyectos;

    public RecyclerViewAdapterProyectos(List<Proyecto> proyectos) {
        this.proyectos = proyectos;
    }

    @Override
    public ViewHolderProyecto onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cv_proyecto, parent, false);
        ViewHolderProyecto pvh = new ViewHolderProyecto(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(ViewHolderProyecto holder, int position) {
        if(proyectos.get(position).getEstatus().equals(Estatus.CULMINADO)){
            holder.estatus.setImageResource(R.mipmap.estatus_completo);
        }else{
            holder.estatus.setImageResource(R.mipmap.estatus_incompleto);
        }
        holder.nombreEstructura.setText(proyectos.get(position).getNombreEstructura());
        holder.estado.setText(proyectos.get(position).getEstado().getdescripcion());
        holder.usuario.setText(proyectos.get(position).getUsuario().getCorreo());
        holder.setClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                if (isLongClick) {
                    //aca se puede colocar un dialogo para elimar
                } else {
                    Intent intent= new Intent(ContextProvider.getContext(), CrearProyectoActivity.class);
                    intent.putExtra("proyecto",proyectos.get(position));
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    ContextProvider.getContext().startActivity(intent);
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return proyectos.size();
    }
}
