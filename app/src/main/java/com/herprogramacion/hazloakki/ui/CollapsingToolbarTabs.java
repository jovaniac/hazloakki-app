package com.herprogramacion.hazloakki.ui;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;


import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.google.gson.Gson;
import com.herprogramacion.hazloakki.R;

import com.herprogramacion.hazloakki.modelo.NegocioDto;
import com.herprogramacion.hazloakki.network.AppController;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;


public class CollapsingToolbarTabs extends AppCompatActivity {

    private String REQUEST_NEGOCIOS= "http://192.168.0.3:8086/api/v1/negocios/";
    Gson gsonConvert = new Gson();
    private NegocioDto negocioDto = new NegocioDto();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collapsing_toobar_tabs);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarCollapsin);
        toolbar.setTitle("Nombre del Negocio");
        setSupportActionBar(toolbar);



        /* FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        */

        ViewPager mViewPager = (ViewPager) findViewById(R.id.viewpager);
        ViewPagerAdapter mViewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());

        NegocioDto negocioDto = null;
        Bundle bundle = getIntent().getExtras();

        Bundle data = new Bundle();//Use bundle to pass data

        if (!bundle.isEmpty()) {
            Toast.makeText(getApplicationContext(),"Negocio Seleccionado Collapsin:"+bundle.getString("idNegocio"), Toast.LENGTH_LONG).show();
            data.putString("idNegocio", bundle.getString("idNegocio"));//put string, int, etc in bundle with a key value
        }


        FragmentoOfertasNegocio fragmentoOfertasNegocio = new FragmentoOfertasNegocio();
        fragmentoOfertasNegocio.setArguments(data);

        FragmentoInfoNegocio fragmentoInfoNegocio = new FragmentoInfoNegocio();
        fragmentoInfoNegocio.setArguments(data);


        mViewPagerAdapter.addFragment(fragmentoOfertasNegocio, "OFERTAS");
        mViewPagerAdapter.addFragment(DemoFragment.newInstance(), "QUE OPINAN");
        mViewPagerAdapter.addFragment(fragmentoInfoNegocio, "INFO");

        mViewPager.setAdapter(mViewPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        ImageView imageViewMusic = findViewById(R.id.imaViewMusic);
        Picasso.with(this).load("http://www.defe.mx/_/rsrc/1399266135277/mexico-df/transporte/autobuses/terminal-central-sur-taxquena/Taxque%C3%B1a.JPG?height=220&width=320+").fit().into(imageViewMusic);
    }


}
