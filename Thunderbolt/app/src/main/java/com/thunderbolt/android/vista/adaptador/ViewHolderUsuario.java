package com.thunderbolt.android.vista.adaptador;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.thunderbolt.android.R;

/**
 * Created by Andres y Jess on 23/11/2016.
 */

public class ViewHolderUsuario extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

    private ItemClickListener clickListener;
    CardView cv;
    TextView nombre;
    TextView apellido;
    TextView correo;
    TextView direccion;
    TextView telefono;

    public ViewHolderUsuario(View itemView) {
        super(itemView);
        cv = (CardView) itemView.findViewById(R.id.cvUsuarios);
        nombre = (TextView) itemView.findViewById(R.id.txtVnombreUsuario);
        apellido= (TextView) itemView.findViewById(R.id.txtVApellidoUsuario);
        correo =(TextView) itemView.findViewById(R.id.txtVCorreoUsuario);
        direccion=(TextView) itemView.findViewById(R.id.txtVDireccionUsuario);
        telefono=(TextView) itemView.findViewById(R.id.txtVTelefonoUsuario);
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
