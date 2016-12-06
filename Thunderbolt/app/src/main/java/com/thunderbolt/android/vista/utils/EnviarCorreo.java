package com.thunderbolt.android.vista.utils;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.widget.Toast;
import com.base.android.ContextProvider;
/**
 * Created by Andres y Jess on 29/11/2016.
 */

public class EnviarCorreo {

    public static void enviar(String correo, String nombrePDF){
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{correo});
        intent.putExtra(Intent.EXTRA_SUBJECT, "Evaluacion de nivel de riesgo.");
        intent.putExtra(Intent.EXTRA_TEXT, "A continuacion se adjunta informe final de resultados.");
        intent.putExtra(Intent.EXTRA_STREAM, Uri.parse(new StringBuilder().append("file://").append(Environment.getExternalStorageDirectory()).append("/").append(nombrePDF).append(".pdf").toString()));
        try {
           ContextProvider.getContext().startActivity(Intent.createChooser(intent, "Enviar Correo con:").setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(ContextProvider.getContext(), "No hay clientes de correo instalados", Toast.LENGTH_SHORT).show();
        }
    }
}
