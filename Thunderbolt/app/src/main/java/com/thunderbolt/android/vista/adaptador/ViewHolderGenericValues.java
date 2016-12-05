package com.thunderbolt.android.vista.adaptador;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.thunderbolt.android.R;

/**
 * Created by Carlos Torres on 23/11/16.
 */
public class ViewHolderGenericValues extends RecyclerView.ViewHolder {

    CardView cv;
    TextView valorEnum;
    TextView descripcionEnum;

    public ViewHolderGenericValues(View itemView) {
        super(itemView);
        cv = (CardView) itemView.findViewById(R.id.cvGenericValues);
        valorEnum= (TextView) itemView.findViewById(R.id.textViewValorEnum);
        descripcionEnum =(TextView) itemView.findViewById(R.id.textViewDescripcionEnum);
    }
}

