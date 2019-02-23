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
import com.herprogramacion.hazloakki.R;

import com.squareup.picasso.Picasso;

public class CollapsingToolbarTabs extends AppCompatActivity {

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


        //FragmentoOfertasNegocio fragmentoOfertasNegocio = new FragmentoOfertasNegocio();

        Bundle bundle = getIntent().getExtras();
            if(!bundle.isEmpty()){

                //fragmentoOfertasNegocio.setIdNegocio(String.valueOf(bundle.get("idNegocio")));
            }


        //mViewPagerAdapter.addFragment(fragmentoOfertasNegocio, "Ofertas");

        FragmentoInfoNegocio fragmentoInfoNegocio  =new FragmentoInfoNegocio();

        mViewPagerAdapter.addFragment(fragmentoInfoNegocio, "Info");


        mViewPagerAdapter.addFragment(DemoFragment.newInstance(), "Servicios");
        mViewPagerAdapter.addFragment(DemoFragment.newInstance(), "Chamba");
        mViewPager.setAdapter(mViewPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        ImageView imageViewMusic = findViewById(R.id.imaViewMusic);
        Picasso.with(this).load("https://cdn.pixabay.com/photo/2015/04/13/13/37/dj-720589_640.jpg").fit().into(imageViewMusic);
    }

}
