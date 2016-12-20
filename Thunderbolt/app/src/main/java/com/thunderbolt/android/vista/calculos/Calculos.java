package com.thunderbolt.android.vista.calculos;

import android.support.annotation.Dimension;

import com.db.android.model.DimensionesEstructura;
import com.db.android.model.Proyecto;

/**
 * Created by Andres y Jess on 23/11/2016.
 */

public class Calculos {

    public static Double s1AD(DimensionesEstructura dimension) {
        return  (dimension.getLargo() * dimension.getAncho()) + (2 * (3 * dimension.getAlto()) * (dimension.getLargo() + dimension.getAncho())) + (3.1416 * Math.pow(3 * dimension.getAlto(), 2));
    }

    public static Double ng(Double valor){
        return  0.1*valor;
    }

    public static Double s1Nd(Proyecto proyecto){
        return ng((double) proyecto.getEstado().getValor())*s1AD(proyecto.getNumeroEventosPeligorsos().getDimensionesEstructura())*proyecto.getNumeroEventosPeligorsos().getEstructuraEnEvaluacion().getValor()* Math.pow(10, -6);
    }

    public static Double s2Am(DimensionesEstructura dimension){
        return (dimension.getLargo()*dimension.getAncho())+2*(500*dimension.getLargo())*3.1416*Math.pow(500,2);
    }

    public static Double S2Nm(Proyecto proyecto){
        return ng((double) proyecto.getEstado().getValor())*s2Am(proyecto.getNumeroEventosPeligorsos().getDimensionesEstructura())*Math.pow(10,-6);
    }

    public static Double s3Al(Double l){
        return 40*l;
    }

   public static Double s3Nl(Proyecto proyecto){
       return ng((double) proyecto.getEstado().getValor())*s3Al(Double.valueOf(proyecto.getNumeroEventosPeligorsos().getLongitud_de_la_acometida()))*proyecto.getNumeroEventosPeligorsos().getAmbiente().getValor()*proyecto.getNumeroEventosPeligorsos().getEnrutamientoDeAcometida().getValor()*proyecto.getNumeroEventosPeligorsos().getTransformadorEnAcometida().getValor()*Math.pow(10,-6) ;
   }

    public static Double s4Al(Double l){
        return 4000*l;
    }

    public static Double s4Ni(Proyecto proyecto){
        return ng((double) proyecto.getEstado().getValor())*s4Al(Double.valueOf(proyecto.getNumeroEventosPeligorsos().getLongitud_de_la_acometida()))*proyecto.getNumeroEventosPeligorsos().getAmbiente().getValor()*proyecto.getNumeroEventosPeligorsos().getEnrutamientoDeAcometida().getValor()*proyecto.getNumeroEventosPeligorsos().getTransformadorEnAcometida().getValor()*Math.pow(10,-6);
    }
}

