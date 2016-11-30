package com.thunderbolt.android.vista.adaptador;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.base.android.ContextProvider;
import com.db.android.constantes.CalculosPrincipales;
import com.db.android.model.NumeroEventosPeligorsos;
import com.db.android.model.Proyecto;
import com.thunderbolt.android.R;
import com.thunderbolt.android.vista.CrearProyectoActivity;
import com.thunderbolt.android.vista.NumeroEventosPeligrososActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Andres y Jess on 29/11/2016.
 */

public class RecyclerViewAdapterCalculosPrincipales extends RecyclerView.Adapter<ViewHolderCalculosPrincipales> {

    private Proyecto proyecto;
    private List<String> calculos = new ArrayList<>();

    public RecyclerViewAdapterCalculosPrincipales(Proyecto proyecto, List<String> calculos) {
        this.proyecto = proyecto;
        this.calculos.addAll(calculos) ;
    }

    @Override
    public ViewHolderCalculosPrincipales onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cv_calculos_princiaples, parent, false);
        ViewHolderCalculosPrincipales pvh = new ViewHolderCalculosPrincipales(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(ViewHolderCalculosPrincipales holder, int position) {
        holder.nombreCalculo.setText(CalculosPrincipales.valueOf(calculos.get(position)).getdescripcion());
        switch (position){
            case 0:
                if(this.proyecto.getNumeroEventosPeligorsos()!=null && this.proyecto.getNumeroEventosPeligorsos().getCalculo_ni()!=null && this.proyecto.getNumeroEventosPeligorsos().getCalculo_nl()!=null&& this.proyecto.getNumeroEventosPeligorsos().getCalculo_nm()!=null&& this.proyecto.getNumeroEventosPeligorsos().getCalculo_np()!=null){
                    holder.estatus.setImageResource(R.mipmap.estatus_completo);
                }else{
                    holder.estatus.setImageResource(R.mipmap.estatus_incompleto);
                }
                holder.setClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        if (isLongClick) {
                            //aca se puede colocar un dialogo para elimar
                        } else {
                            Intent intent= new Intent(ContextProvider.getContext(), NumeroEventosPeligrososActivity.class);
                            intent.putExtra("proyecto",proyecto);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            ContextProvider.getContext().startActivity(intent);
                        }
                    }
                });
                break;
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
        }

    }

    @Override
    public int getItemCount() {
        return calculos.size();
    }
}
