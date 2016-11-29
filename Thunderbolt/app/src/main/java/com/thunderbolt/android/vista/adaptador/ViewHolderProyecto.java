package com.thunderbolt.android.vista.adaptador;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.thunderbolt.android.R;


/**
 * Created by conamerica36 on 23/10/16.
 */
public class ViewHolderProyecto extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{

    private ItemClickListener clickListener;
    CardView cv;
    TextView nombreEstructura;
    TextView estado;
    TextView usuario;
    ImageView estatus;
    ImageButton eliminar;
    ImageButton editar;

    ViewHolderProyecto(View itemView) {
        super(itemView);
        cv = (CardView) itemView.findViewById(R.id.cvProyectos);
        estatus= (ImageView) itemView.findViewById(R.id.imgVEstatus);
        nombreEstructura = (TextView) itemView.findViewById(R.id.txtVnombreEstructura);
        estado= (TextView)itemView.findViewById(R.id.txtVnombreEstado);
        usuario= (TextView)itemView.findViewById(R.id.txtVnombreCorreo);
        eliminar= (ImageButton) itemView.findViewById(R.id.imgBBorrarProyecto);
        editar=(ImageButton) itemView.findViewById(R.id.imgBEditarProyecto);
        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);
    }

    public void setClickListener(ItemClickListener clickListener) {
        this.clickListener = clickListener;
    }

    @Override
    public void onClick(View v) {
        clickListener.onClick(v, getPosition(), false);
    }

    @Override
    public boolean onLongClick(View v) {
        clickListener.onClick(v, getPosition(), true);
        return  true;
    }
}
