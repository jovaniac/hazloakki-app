package com.herprogramacion.hazloakki.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
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
import com.google.gson.Gson;
import com.herprogramacion.hazloakki.R;
import com.herprogramacion.hazloakki.modelo.AccionesDto;
import com.herprogramacion.hazloakki.network.AppController;
import com.herprogramacion.hazloakki.notifications.Alertas;
import com.herprogramacion.hazloakki.utils.RecyclerItemClickListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Fragmento que representa el contenido de cada tab de los diferentes modulos de hazloakki
 */
public class FragmentTabServicios extends Fragment {

    private static final String INDICE_SECCION = "com.restaurantericoparico.FragmentoCategoriasTab.extra.INDICE_SECCION";

    private RecyclerView reciclador;
    private GridLayoutManager layoutManager;


    // This tag will be used to cancel the request
    private String tag_string_req = "string_req";
    private String REQUEST_CATEGORIAS = "http://192.168.0.7:8091/api/v1/acciones";
    private String TAG = FragmentTabServicios.class.getSimpleName();
    private Gson gson = new Gson();
    private String resultCategorias;
    private Alertas alertas = Alertas.getIntance(getActivity());
    private AdaptadorAcciones adaptadorCatInicial;

    public static FragmentTabServicios nuevaInstancia(int indiceSeccion) {
        FragmentTabServicios fragment = new FragmentTabServicios();
        Bundle args = new Bundle();
        args.putInt(INDICE_SECCION, indiceSeccion);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmento_grupo_items, container, false);

        reciclador = (RecyclerView) view.findViewById(R.id.reciclador);
        reciclador.setHasFixedSize(true);
        layoutManager = new GridLayoutManager(getActivity(), 2);
        reciclador.setLayoutManager(layoutManager);

        sendRequest();
        selectAdapter();
        seleccionarAccion();

        return view;
    }

    public void seleccionarAccion(){
        reciclador.addOnItemTouchListener(
                new RecyclerItemClickListener(getActivity(), new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View v, int position) {

                        Toast.makeText(getActivity(), adaptadorCatInicial.getItems().get(position).getNombre()+" IdAccion "+adaptadorCatInicial.getItems().get(position).getIdAccion(), Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(getActivity().getApplicationContext(),NegociosRecyclerView.class);
                        intent.putExtra("idAccion",adaptadorCatInicial.getItems().get(position).getIdAccion());
                        startActivity(intent);
                    }
                })
        );
    }


    public void selectAdapter(){
        int indiceSeccion = getArguments().getInt(INDICE_SECCION);
        sendRequest();

        switch (indiceSeccion) {
            case 0:
                //  adaptadorCatInicial.setItems(listCategorias);
                //reciclador.setAdapter(adaptadorCatInicial);
                break;
           /* case 1:
                adaptador = new AdaptadorCategorias(Comida.BEBIDAS);
                break;
            case 2:
                adaptador = new AdaptadorCategorias(Comida.POSTRES);
                break;*/
        }

    }

    public void sendRequest(){
        JsonArrayRequest req = new JsonArrayRequest(REQUEST_CATEGORIAS,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, response.toString());
                        adaptadorCatInicial = new AdaptadorAcciones(getContext(),parseJsonToAcciones(response));
                        reciclador.setAdapter(adaptadorCatInicial);

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

    public List<AccionesDto> parseJsonToAcciones(JSONArray response){
        List<AccionesDto> listaDeAcciones = new ArrayList<AccionesDto>();
        try {
            // Parsing json array response
            // loop through each json object
            String jsonResponse ="";
            for (int i = 0; i < response.length(); i++) {

                JSONObject acciones = (JSONObject) response.get(i);


                AccionesDto accionesDto = new AccionesDto();
                    accionesDto.setIdAccion(acciones.getString("idAccion"));
                    accionesDto.setNombre(acciones.getString("nombre"));
                    accionesDto.setDescripcion(acciones.getString("descripcion"));
                    accionesDto.setEstatus(acciones.getBoolean("estatus"));

                listaDeAcciones.add(accionesDto);
            }
            Toast.makeText(getActivity().getApplicationContext(), "Tamaño de datos: "+listaDeAcciones.size(), Toast.LENGTH_SHORT).show();

        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(getActivity().getApplicationContext(),
                    "Error: " + e.getMessage(),
                    Toast.LENGTH_LONG).show();
        }
        return listaDeAcciones;
    }

}