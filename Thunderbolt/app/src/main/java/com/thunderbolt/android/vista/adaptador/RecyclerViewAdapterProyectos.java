package com.thunderbolt.android.vista.adaptador;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.base.android.ContextProvider;
import com.db.android.constantes.Estatus;
import com.db.android.facade.ProyectoFacade;
import com.db.android.facade.ProyectoFacadeLocal;
import com.db.android.model.Proyecto;
import com.thunderbolt.android.R;
import com.thunderbolt.android.vista.CrearProyectoActivity;

import java.sql.SQLException;
import java.util.List;


/**
 * Created by conamerica36 on 23/10/16.
 */
public class RecyclerViewAdapterProyectos extends RecyclerView.Adapter<ViewHolderProyecto> implements View.OnClickListener{
    private List<Proyecto> proyectos;
    private ProyectoFacadeLocal proyectoFacadeLocal;
   private int posicion;
    public RecyclerViewAdapterProyectos(List<Proyecto> proyectos) {
        this.proyectos = proyectos;
    }

    @Override
    public ViewHolderProyecto onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cv_proyecto, parent, false);
        ViewHolderProyecto pvh = new ViewHolderProyecto(v);
        proyectoFacadeLocal= new ProyectoFacade();
        return pvh;
    }

    @Override
    public void onBindViewHolder(ViewHolderProyecto holder, int position) {
        posicion=position;
        if(proyectos.get(position).getEstatus().equals(Estatus.CULMINADO)){
            holder.estatus.setImageResource(R.mipmap.estatus_completo);
        }else{
            holder.estatus.setImageResource(R.mipmap.estatus_incompleto);
        }
        holder.nombreEstructura.setText(proyectos.get(position).getNombreEstructura());
        holder.estado.setText(proyectos.get(position).getEstado().getdescripcion());
        holder.usuario.setText(proyectos.get(position).getUsuario().getCorreo());
        holder.editar.setOnClickListener(this);
        holder.eliminar.setOnClickListener(this);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.imgBEditarProyecto:
                Intent intent= new Intent(ContextProvider.getContext(), CrearProyectoActivity.class);
                intent.putExtra("proyecto",proyectos.get(posicion));
                intent.putExtra("editar",true);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                ContextProvider.getContext().startActivity(intent);
                break;
            case R.id.imgBBorrarProyecto:
                try {
                    proyectoFacadeLocal.eliminar(proyectos.get(posicion));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
        }
    }
}
