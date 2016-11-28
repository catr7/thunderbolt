package com.thunderbolt.android.vista.utils;

import com.db.android.constantes.Ambiente;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Carlos Torres on 27/11/16.
 */
public class EnumAdapter {

    public static List<String> enumKeysValues(Enum[] keys){
        List<String> keysValues= new ArrayList<>();
        for(Enum keyV: keys){
            keysValues.add(keyV.name());
        }
        return keysValues;
    }

}
