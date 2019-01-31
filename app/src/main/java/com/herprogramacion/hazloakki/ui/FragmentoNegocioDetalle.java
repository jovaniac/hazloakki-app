package com.herprogramacion.hazloakki.ui;


import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.herprogramacion.hazloakki.R;
import com.herprogramacion.hazloakki.adaptador.AdaptadorNegocio;
import com.herprogramacion.hazloakki.ui.FragmentoDirecciones;
import com.herprogramacion.hazloakki.ui.FragmentoPerfil;
import com.herprogramacion.hazloakki.ui.FragmentoTarjetas;

import java.util.ArrayList;
import java.util.List;


/**
 * Fragmento de la sección "Mi Cuenta"
 */
public class FragmentoNegocioDetalle extends Fragment {

    private AppBarLayout appBar;
    private TabLayout pestanas;
    private ViewPager viewPager;

    private RecyclerView listaUI;
    private LinearLayoutManager linearLayoutManager;
    private AdaptadorNegocio adaptadorNegocio;
    private String REQUEST_NEGOCIOS = "http://192.168.0.3:8086/api/v1/negocios/acciones/";
    private static String TAG = NegociosRecyclerView.class.getSimpleName();
    private static Context ctx;
    private static AdaptadorNegocio.OnItemClickListener escucha;

    public FragmentoNegocioDetalle() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmento_paginado, container, false);

        if (savedInstanceState == null) {
            insertarTabs(container);

            // Setear adaptador al viewpager.
            viewPager = (ViewPager) view.findViewById(R.id.pager);
            poblarViewPager(viewPager);
            pestanas.setupWithViewPager(viewPager);
        }



        return view;
    }

    private void insertarTabs(ViewGroup container) {
        View padre = (View) container.getParent();
        appBar = (AppBarLayout) padre.findViewById(R.id.appbar);
        pestanas = new TabLayout(getActivity());
        pestanas.setTabTextColors(Color.parseColor("#FFFFFF"), Color.parseColor("#FFFFFF"));
        appBar.addView(pestanas);
    }

    private void poblarViewPager(ViewPager viewPager) {
        AdaptadorSecciones adapter = new AdaptadorSecciones(getFragmentManager());
        adapter.addFragment(new FragmentoPerfil(), getString(R.string.titulo_tab_perfil));
        adapter.addFragment(new FragmentoDirecciones(), getString(R.string.titulo_tab_direcciones));
        adapter.addFragment(new FragmentoTarjetas(), getString(R.string.titulo_tab_tarjetas));
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        appBar.removeView(pestanas);
    }

    /**
     * Un {@link FragmentStatePagerAdapter} que gestiona las secciones, fragmentos y
     * títulos de las pestañas
     */
    public class AdaptadorSecciones extends FragmentStatePagerAdapter {
        private final List<Fragment> fragmentos = new ArrayList<>();
        private final List<String> titulosFragmentos = new ArrayList<>();

        public AdaptadorSecciones(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentos.get(position);
        }

        @Override
        public int getCount() {
            return fragmentos.size();
        }

        public void addFragment(Fragment fragment, String title) {
            fragmentos.add(fragment);
            titulosFragmentos.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titulosFragmentos.get(position);
        }
    }
}
