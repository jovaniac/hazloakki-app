package com.herprogramacion.hazloakki.ui;


import android.annotation.SuppressLint;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.gson.Gson;
import com.herprogramacion.hazloakki.R;
import com.herprogramacion.hazloakki.modelo.AccionesDto;
import com.herprogramacion.hazloakki.network.AppController;

import com.herprogramacion.hazloakki.utils.RecyclerItemClickListener;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class FragmentoInicio extends Fragment {

    private RecyclerView reciclador;
    private LinearLayoutManager layoutManager;
    private AdaptadorInicio adaptador;
    private String REQUEST_ACCIONES = "http://192.168.0.7:8091/api/v1/acciones";
    private String TAG = FragmentTabServicios.class.getSimpleName();
    private Gson gson = new Gson();
    private static final String INDICE_SECCION = "com.restaurantericoparico.FragmentoCategoriasTab.extra.INDICE_SECCION";
    private static String latitudFragment;
    private static String longitudFragment;
    private FusedLocationProviderClient mFusedLocationClient;

    public FragmentoInicio() {

    }

    public static FragmentoInicio nuevaInstancia(int indiceSeccion) {
        FragmentoInicio fragment = new FragmentoInicio();
        Bundle args = new Bundle();
        args.putInt(INDICE_SECCION, indiceSeccion);
        fragment.setArguments(args);
        return fragment;
    }

    @SuppressLint("MissingPermission")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmento_inicio, container, false);

        reciclador = (RecyclerView) view.findViewById(R.id.reciclador);
        layoutManager = new LinearLayoutManager(getActivity());
        reciclador.setLayoutManager(layoutManager);

        sendRequest();
        adaptador = new AdaptadorInicio();
        //reciclador.setAdapter(adaptador);

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(getActivity());
        mFusedLocationClient.getLastLocation()
                .addOnSuccessListener(getActivity(), new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        latitudFragment = String.valueOf(location.getLatitude());
                        longitudFragment = String.valueOf(location.getLongitude());
                    }
                })
                .addOnFailureListener(getActivity(), new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getActivity().getApplicationContext(),"onFailure...", Toast.LENGTH_LONG).show();
                    }
                });


        seleccionarAccion();

        return view;
    }

    public void sendRequest() {
        JsonArrayRequest req = new JsonArrayRequest(REQUEST_ACCIONES,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, response.toString());
                        adaptador = new AdaptadorInicio(getContext(), parseJsonToAcciones(response));
                        reciclador.setAdapter(adaptador);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                Toast.makeText(getActivity().getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        AppController.getInstance(getActivity().getApplicationContext()).getRequestQueue().add(req);
    }

    public List<AccionesDto> parseJsonToAcciones(JSONArray response) {
        List<AccionesDto> listaDeAcciones = new ArrayList<AccionesDto>();
        try {
            for (int i = 0; i < response.length(); i++) {

                JSONObject acciones = (JSONObject) response.get(i);


                AccionesDto accionesDto = new AccionesDto();
                accionesDto.setIdAccion(acciones.getString("idAccion"));
                accionesDto.setNombre(acciones.getString("nombre"));
                accionesDto.setDescripcion(acciones.getString("descripcion"));
                accionesDto.setEstatus(acciones.getBoolean("estatus"));

                listaDeAcciones.add(accionesDto);
            }
            Toast.makeText(getActivity().getApplicationContext(), "Tamaño de datos: " + listaDeAcciones.size(), Toast.LENGTH_SHORT).show();

        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(getActivity().getApplicationContext(),
                    "Error: " + e.getMessage(),
                    Toast.LENGTH_LONG).show();
        }
        return listaDeAcciones;
    }

    public void seleccionarAccion() {
        reciclador.addOnItemTouchListener(
                new RecyclerItemClickListener(getActivity(), new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View v, int position) {

                        Toast.makeText(getActivity(),"onSuccess...latitud: "+latitudFragment + " Longitud: "+longitudFragment, Toast.LENGTH_LONG).show();

                       // Toast.makeText(getActivity(), adaptador.getItems().get(position).getNombre() + " IdAccion " + adaptador.getItems().get(position).getIdAccion(), Toast.LENGTH_SHORT).show();


                        Fragment fragmentoGenerico = null;
                        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();

                        Bundle bundle=new Bundle();
                            bundle.putString("idAccion", adaptador.getItems().get(position).getIdAccion());
                            bundle.putString("latitud", "19.3277");
                            bundle.putString("longitud", "-99.1517");
                            bundle.putInt("distancia", 1);
                            bundle.putBoolean("estatus", true);

                        fragmentoGenerico = new FragmentoNegocioDetalle();
                        fragmentoGenerico.setArguments(bundle);

                        if (fragmentoGenerico != null) {
                            fragmentManager.beginTransaction().replace(R.id.contenedor_principal, fragmentoGenerico).commit();
                        }

                       /* Intent intent = new Intent(getActivity().getApplicationContext(), NegociosRecyclerView.class);
                        intent.putExtra("idAccion", adaptador.getItems().get(position).getIdAccion());
                        intent.putExtra("latitud",latitudFragment);
                        intent.putExtra("longitud",longitudFragment);
                        intent.putExtra("distancia",1);
                        intent.putExtra("estatus",true);

                        startActivity(intent);*/

                    }
                })
        );
    }

}