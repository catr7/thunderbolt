package com.thunderbolt.android.vista.adaptador;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import com.thunderbolt.android.R;


/**
 * Created by conamerica36 on 23/10/16.
 */
public class ViewHolderProyecto extends RecyclerView.ViewHolder{

    CardView cv;
    TextView nombreEstructura;
    TextView estado;
    TextView usuario;

    ViewHolderProyecto(View itemView) {
        super(itemView);
        cv = (CardView) itemView.findViewById(R.id.cvProyectos);
        nombreEstructura = (TextView) itemView.findViewById(R.id.txtVnombreEstructura);
        estado= (TextView)itemView.findViewById(R.id.txtVnombreEstado);
        usuario= (TextView)itemView.findViewById(R.id.txtVnombreCorreo);
    }
}
