package com.thunderbolt.android.vista.calculos;

import android.support.annotation.Dimension;

import com.db.android.model.DimensionesEstructura;

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

    public static Double nd(DimensionesEstructura dimension, Double estado,Double estructuraEnEvaluacion){
        return ng(estado)*s1AD(dimension)*estructuraEnEvaluacion* Math.pow(10, -6);
    }

    public static Double am(DimensionesEstructura dimension){
        return (dimension.getLargo()*dimension.getAncho())+2*(500*dimension.getLargo())*3.1416*Math.pow(500,2);
    }

    public static Double nm(Double estado,DimensionesEstructura dimension){
        return ng(estado)*am(dimension)*Math.pow(10,-6);
    }
}
