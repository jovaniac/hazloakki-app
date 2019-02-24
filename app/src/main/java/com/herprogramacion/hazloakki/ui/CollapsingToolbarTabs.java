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

    private String REQUEST_NEGOCIOS= "http://192.168.0.5:8086/api/v1/negocios/";
    Gson gsonConvert = new Gson();
    NegocioDto negocioDto = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collapsing_toobar_tabs);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        ViewPager mViewPager = (ViewPager) findViewById(R.id.viewpager);
        ViewPagerAdapter mViewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());

        NegocioDto negocioDto = null;
        Bundle bundle = getIntent().getExtras();

        if (!bundle.isEmpty()) {

            Toast.makeText(getApplicationContext(),"Negocio Seleccionado Collapsin:"+bundle.getString("idNegocio"), Toast.LENGTH_LONG).show();

            detalleNegocio(bundle.getString("idNegocio"));
        }


        //FragmentoOfertasNegocio fragmentoOfertasNegocio = new FragmentoOfertasNegocio();
        //mViewPagerAdapter.addFragment(fragmentoOfertasNegocio, "Ofertas");


        FragmentoInfoNegocio fragmentoInfoNegocio = new FragmentoInfoNegocio();

        mViewPagerAdapter.addFragment(fragmentoInfoNegocio, "Info");


        mViewPagerAdapter.addFragment(DemoFragment.newInstance(), "Servicios");
        mViewPagerAdapter.addFragment(DemoFragment.newInstance(), "Chamba");
        mViewPager.setAdapter(mViewPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        ImageView imageViewMusic = findViewById(R.id.imaViewMusic);
        Picasso.with(this).load("https://cdn.pixabay.com/photo/2015/04/13/13/37/dj-720589_640.jpg").fit().into(imageViewMusic);
    }

    public void detalleNegocio(String idNegocio) {
        sendRequestJsonPost(idNegocio);
    }

    public void sendRequestJsonPost(String idNegocio) {


        StringBuilder urlMoreParameters = new StringBuilder(REQUEST_NEGOCIOS);
        urlMoreParameters.append(idNegocio);


        try {
            JsonRequest jsonRequest = new JsonObjectRequest(Request.Method.GET, urlMoreParameters.toString(), null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {

                    try {
                        negocioDto = gsonConvert.fromJson(response.toString(),NegocioDto.class);
                        Toast.makeText(getApplicationContext(), "Response toString Collapsin: " +negocioDto.toString(), Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    Toast.makeText(getApplicationContext(),
                            "Error: " + error.getMessage(),
                            Toast.LENGTH_LONG).show();
                }
            });

            AppController.getInstance(getApplicationContext()).getRequestQueue().add(jsonRequest);

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Upps algo inesperado sucedio, vuelve a intentarlo: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }


}
