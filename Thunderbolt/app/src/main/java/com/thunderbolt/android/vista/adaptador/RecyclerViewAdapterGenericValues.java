package com.thunderbolt.android.vista.adaptador;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.base.android.ContextProvider;
import com.db.android.constantes.EstructuraEnEvaluacion;
import com.db.android.facade.ProyectoFacade;
import com.db.android.facade.ProyectoFacadeLocal;
import com.db.android.model.Proyecto;
import com.thunderbolt.android.R;
import com.thunderbolt.android.vista.CrearProyectoActivity;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Carlos Torres on 23/11/16.
 */
public class RecyclerViewAdapterGenericValues extends RecyclerView.Adapter<ViewHolderGenericValues> implements View.OnClickListener {

    private Map<String, String[]> enumSeleccionado = new HashMap<>();
    private List<String> titulosEnum = new ArrayList<>();
    private String c;
    private Class a;
    private Proyecto proyecto;
    String calculo;
    private ProyectoFacadeLocal proyectoFacadeLocal;


    public RecyclerViewAdapterGenericValues(Map<String, String[]> enumSeleccionado, List<String> titulosEnum,Proyecto proyecto, String c, Class a) {
        this.enumSeleccionado = enumSeleccionado;
        this.titulosEnum = titulosEnum;
        this.proyecto= proyecto;
        this.c=c;
        this.a=a;
        this.proyectoFacadeLocal= new ProyectoFacade();
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
        holder.setClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                if (isLongClick) {
                    //aca se puede colocar un dialogo para elimar
                } else {
                    cargarEmun(proyecto, titulosEnum.get(position));
                    try {
                        proyectoFacadeLocal.crear(proyecto);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    Intent intent= new Intent(ContextProvider.getContext(), a);
                    intent.putExtra("proyecto",proyecto);
                    intent.putExtra("calculo",calculo);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    ContextProvider.getContext().startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return titulosEnum.size();
    }

    @Override
    public void onClick(View v) {

    }

    public void cargarEmun(Proyecto proyecto, String name){
        switch (c){
            case "EstructuraEnEvaluacion":
                proyecto.getNumeroEventosPeligorsos().setEstructuraEnEvaluacion(EstructuraEnEvaluacion.valueOf(name));
                calculo="s1";
        }
    }
}
