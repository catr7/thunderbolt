package com.thunderbolt.android.vista.adaptador;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.thunderbolt.android.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Carlos Torres on 23/11/16.
 */
public class RecyclerViewAdapterGenericValues extends RecyclerView.Adapter<ViewHolderGenericValues> {

    private Map<String, String[]> enumSeleccionado = new HashMap<>();
    private List<String> titulosEnum = new ArrayList<>();

    public RecyclerViewAdapterGenericValues(Map<String, String[]> enumSeleccionado, List<String> titulosEnum) {
        this.enumSeleccionado = enumSeleccionado;
        this.titulosEnum = titulosEnum;
    }

    @Override
    public ViewHolderGenericValues onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cv_generic_values, parent, false);
        ViewHolderGenericValues pvh = new ViewHolderGenericValues(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(ViewHolderGenericValues holder, int position) {
        holder.descripcionEnum.setText(this.enumSeleccionado.get(this.titulosEnum.get(position))[0]);
        holder.valorEnum.setText(this.enumSeleccionado.get(this.titulosEnum.get(position))[1]);
    }

    @Override
    public int getItemCount() {
        return titulosEnum.size();
    }
}
