package com.thunderbolt.android.vista.adaptador;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.thunderbolt.android.R;

/**
 * Created by Carlos Torres on 23/11/16.
 */
public class ViewHolderGenericValues extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{

    private ItemClickListener clickListener;
    CardView cv;
    TextView descripcionEnum;

    public ViewHolderGenericValues(View itemView) {
        super(itemView);
        cv = (CardView) itemView.findViewById(R.id.cvGenericValues);
        descripcionEnum =(TextView) itemView.findViewById(R.id.textViewDescripcionEnum);
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

