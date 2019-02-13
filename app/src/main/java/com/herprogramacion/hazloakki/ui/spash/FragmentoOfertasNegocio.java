package com.herprogramacion.hazloakki.ui.spash;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonRequest;
import com.google.gson.Gson;
import com.herprogramacion.hazloakki.R;
import com.herprogramacion.hazloakki.adaptador.AdaptadorOfertas;
import com.herprogramacion.hazloakki.modelo.OfertasDto;
import com.herprogramacion.hazloakki.network.AppController;
import com.herprogramacion.hazloakki.ui.CollapsingToolbarTabs;
import com.herprogramacion.hazloakki.ui.NegociosRecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FragmentoOfertasNegocio extends Fragment implements AdaptadorOfertas.OnItemClickListener {

    private RecyclerView listaUI;
    private LinearLayoutManager linearLayoutManager;
    private AdaptadorOfertas adaptadorNegocio;
    private String REQUEST_OFERTAS = "http://192.168.0.8:8089/api/v1/ofertas/negocios/";
    private static String TAG = NegociosRecyclerView.class.getSimpleName();
    private static Context ctx;
    private String idNegocio;


    public FragmentoOfertasNegocio() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.actividad_lista_ofertas, container, false);

        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        //((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Filtro...", Snackbar.LENGTH_LONG)
                        .setAction("Acción", null).show();
            }
        });

        adaptadorNegocio = new AdaptadorOfertas(getContext(), this);
        // Preparar lista
        listaUI = (RecyclerView) view.findViewById(R.id.lista);
        listaUI.setHasFixedSize(true);

        linearLayoutManager = new LinearLayoutManager(getActivity());
        listaUI.setLayoutManager(linearLayoutManager);

        ctx = getContext();

        initOfertas();

        return view;
    }


    public void initOfertas(){

        try {
            Gson gson = new Gson();
            //  Intent intent  = getIntent();
            Map<String, String> params = new HashMap<String, String>();

            params.put("idNegocio", idNegocio);


            sendRequestJsonPost(REQUEST_OFERTAS,params);

        } catch (Exception e) {
            Toast.makeText(getContext(), "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

    public  void sendRequestJsonPost(String url, final Map<String, String> params ) {

        String idNegocio = params.get("idNegocio");

        StringBuilder urlMoreParameters = new StringBuilder(url);
        urlMoreParameters.append(idNegocio);

        try {

            JsonRequest<JSONArray> request  = new JsonRequest<JSONArray>(Request.Method.GET, urlMoreParameters.toString(), null,
                    new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {
                            //Toast.makeText(ctx, "Response toString: " + response.toString(), Toast.LENGTH_LONG).show();

                            adaptadorNegocio.setListaOfertas(parseJson(response));
                            listaUI.setAdapter(adaptadorNegocio);
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getContext(), "Response toString: " + error.getMessage(), Toast.LENGTH_LONG).show();
                }
            }) {
                @Override
                protected Map<String, String> getParams() {
                    return params;
                }

                @Override
                protected Response<JSONArray> parseNetworkResponse(
                        NetworkResponse response) {
                    try {
                        String jsonString = new String(response.data,
                                HttpHeaderParser
                                        .parseCharset(response.headers));
                        return Response.success(new JSONArray(jsonString),
                                HttpHeaderParser
                                        .parseCacheHeaders(response));
                    } catch (UnsupportedEncodingException e) {
                        return Response.error(new ParseError(e));
                    } catch (JSONException je) {
                        return Response.error(new ParseError(je));
                    }
                }
            };

            AppController.getInstance(getContext()).getRequestQueue().add(request);

        }catch(Exception e){
            e.printStackTrace();
            Toast.makeText(getContext(), "Upps algo inesperado sucedio, vuelve a intentarlo: " + e.getMessage(), Toast.LENGTH_SHORT).show();        }


    }
    public List<OfertasDto> parseJson(JSONArray response){
        List<OfertasDto> listOfertas = new ArrayList<OfertasDto>();
        try {

            for (int i = 0; i < response.length(); i++) {

                JSONObject negocio = (JSONObject) response.get(i);

                OfertasDto negocioDto = new OfertasDto();
                negocioDto.setIdOferta(negocio.getString("id"));


                listOfertas.add(negocioDto);
            }
            Toast.makeText(getContext(), " Negocios : "+listOfertas.size(), Toast.LENGTH_SHORT).show();

        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(getContext(),
                    "Error: " + e.getMessage(),
                    Toast.LENGTH_LONG).show();
        }
        return listOfertas;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }


    @Override
    public void onClick(AdaptadorOfertas.ViewHolder holder, String idAlquiler) {
        Toast.makeText(getContext(),"Oferta Seleccionada:"+idAlquiler, Toast.LENGTH_LONG).show();

        Intent detalleNegocio = new Intent(getActivity().getApplicationContext(), CollapsingToolbarTabs.class);

        startActivity(detalleNegocio);
    }

    public String getIdNegocio() {
        return idNegocio;
    }

    public void setIdNegocio(String idNegocio) {
        this.idNegocio = idNegocio;
    }
}
