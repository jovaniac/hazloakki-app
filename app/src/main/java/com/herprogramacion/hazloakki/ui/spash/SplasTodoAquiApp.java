package com.herprogramacion.hazloakki.ui.spash;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.herprogramacion.hazloakki.R;
import com.herprogramacion.hazloakki.ui.ActividadPrincipal;

/**
 * Created by JAarzate on 20/11/2017.
 */

public class SplasTodoAquiApp extends AppCompatActivity {

    private Typeface Font_Light = null;
    private Typeface Font_Regular = null;
    private Typeface Font_Thin = null;
   // private BaseApplicationClass baseApp;
    private Dialog customDialog;
    //private GPSTracker gps;
    private ImageView im_image;
    private Double latitude = Double.valueOf(0.0d);
    private Double longitude = Double.valueOf(0.0d);
    private Runnable m_runnable_thread;
    private TextView tv_appname;
    private TextView tv_splash_msg;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashapp);
       // this.baseApp = (BaseApplicationClass) getApplication();
       // this.tv_appname = (TextView) findViewById(R.id.tv_app_name);
        //this.tv_splash_msg = (TextView) findViewById(R.id.tv_desp);
       // this.im_image = (ImageView) findViewById(R.id.im_splash);
       // Picasso.with(this).load((int) R.drawable.splash).into(this.im_image);

        this.m_runnable_thread = new Runnable() {
            public void run() {
                try {
                    Thread.sleep(3000);
                    SplasTodoAquiApp.this.startActivity(new Intent(SplasTodoAquiApp.this, ActividadPrincipal.class));
                    SplasTodoAquiApp.this.finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        try {
            new Thread(null, this.m_runnable_thread).start();
        } catch (Exception e) {
        }
    }


}
