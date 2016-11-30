package com.thunderbolt.android.vista.adaptador;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.thunderbolt.android.R;

/**
 * Created by Andres y Jess on 29/11/2016.
 */

public class ViewHolderCalculosPrincipales extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

    private ItemClickListener clickListener;
    CardView cv;
    TextView nombreCalculo;
    TextView valorCalculo;
    ImageView estatus;

    ViewHolderCalculosPrincipales(View itemView) {
        super(itemView);
        cv = (CardView) itemView.findViewById(R.id.cvProyectos);
        estatus = (ImageView) itemView.findViewById(R.id.imgVEstatusCalculo);
        nombreCalculo = (TextView) itemView.findViewById(R.id.textViewNombreCalculo);
        valorCalculo = (TextView) itemView.findViewById(R.id.textViewValorCalculo);
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
        return true;
    }
}