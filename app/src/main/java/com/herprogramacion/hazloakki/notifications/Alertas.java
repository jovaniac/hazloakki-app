package com.herprogramacion.hazloakki.notifications;

import android.app.Activity;
import android.widget.Toast;

/**
 * Created by JAarzate on 19/11/2017.
 */

public class Alertas  {

    private static Alertas alertas;
    private Activity activity;

    private Alertas(Activity activity){
        this.activity = activity;
    }
    public void alertaToast(String msj){
        Toast.makeText(activity, msj, Toast.LENGTH_SHORT).show();

    }

    public static Alertas getIntance(Activity activity){

        if(alertas == null){
            alertas = new Alertas(activity);
        }
        return alertas;
    }

}
