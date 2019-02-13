package com.herprogramacion.hazloakki.ui;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonRequest;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.gson.Gson;
import com.herprogramacion.hazloakki.R;

import com.herprogramacion.hazloakki.adaptador.AdaptadorNegocio;
import com.herprogramacion.hazloakki.modelo.BuildConfig;
import com.herprogramacion.hazloakki.modelo.NegocioDto;
import com.herprogramacion.hazloakki.network.AppController;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NegociosRecyclerView extends AppCompatActivity implements AdaptadorNegocio.OnItemClickListener{

    private RecyclerView listaUI;
    private LinearLayoutManager linearLayoutManager;
    private AdaptadorNegocio adaptadorNegocio;
    private String REQUEST_NEGOCIOS = "http://192.168.0.3:8086/api/v1/negocios/acciones/";
    private static String TAG = NegociosRecyclerView.class.getSimpleName();
    private static Context ctx;
    private static AdaptadorNegocio.OnItemClickListener escucha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_lista_alquileres);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Filtro...", Snackbar.LENGTH_LONG)
                        .setAction("Acción", null).show();
            }
        });

        // Preparar lista
        listaUI = (RecyclerView) findViewById(R.id.lista);
        listaUI.setHasFixedSize(true);

        linearLayoutManager = new LinearLayoutManager(this);
        listaUI.setLayoutManager(linearLayoutManager);

        ctx = getApplicationContext();

        //adaptadorNegocio = new AdaptadorNegocio(this, this);
        //listaUI.setAdapter(adaptadorNegocio);

        initNegocios();

        // Iniciar loader
       // getSupportLoaderManager().restartLoader(1, null, this);

    }

    public void initNegocios(){

        try {
        Gson gson = new Gson();
            Intent intent  = getIntent();
            Map<String, String> params = new HashMap<String, String>();

            Bundle bundle = getIntent().getExtras();
            if (bundle != null) {
                params.put("idAccion", String.valueOf(bundle.get("idAccion")));
                params.put("latitud", String.valueOf(bundle.get("latitud")));
                params.put("longitud", String.valueOf(bundle.get("longitud")));
                params.put("distancia", String.valueOf(bundle.get("distancia")));
                params.put("estatus", String.valueOf(Boolean.parseBoolean(String.valueOf(bundle.get("estatus")))));
            }

            Toast.makeText(getApplicationContext(), "Data Param - accion:"+params.get("idAccion")+ " latitud: "+params.get("latitud") + " longitud: "+params.get("longitud")+ " Distancia: "+params.get("distancia")+ " Estatus: "+params.get("estatus"),Toast.LENGTH_LONG).show();


            sendRequestJsonPost(REQUEST_NEGOCIOS,params);

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_actividad_lista_alquileres, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(AdaptadorNegocio.ViewHolder holder, String idAlquiler) {
        Snackbar.make(findViewById(android.R.id.content), ":id = " + idAlquiler,
                Snackbar.LENGTH_LONG).show();
    }


/*    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(this, Contrato.Alquileres.URI_CONTENIDO, null, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        if (adaptadorNegocio != null) {
            adaptadorNegocio.swapCursor(data);
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
    }
*/
    public  void sendRequestJsonPost(String url, final Map<String, String> params ) {

        String idAccion = params.get("idAccion");
        String latitud= params.get("latitud");
        String longitud= params.get("longitud");
        String distancia= params.get("distancia");
        Boolean estatus = Boolean.parseBoolean(params.get("estatus"));

        StringBuilder urlMoreParameters = new StringBuilder(url);
        urlMoreParameters.append(idAccion);
        urlMoreParameters.append("/");
        urlMoreParameters.append(latitud);
        urlMoreParameters.append("/");
        urlMoreParameters.append(longitud);
        urlMoreParameters.append("/");
        urlMoreParameters.append(distancia);
        urlMoreParameters.append("/");
        urlMoreParameters.append(estatus);;
        try {

           JsonRequest<JSONArray> request  = new JsonRequest<JSONArray>(Request.Method.GET, urlMoreParameters.toString(), null,
                    new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {
                            //Toast.makeText(ctx, "Response toString: " + response.toString(), Toast.LENGTH_LONG).show();

//                            adaptadorNegocio = new AdaptadorNegocio(getApplicationContext(),parseJsonNegocio(response));
                            listaUI.setAdapter(adaptadorNegocio);
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(), "Response toString: " + error.getMessage(), Toast.LENGTH_LONG).show();
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

            AppController.getInstance(getApplicationContext()).getRequestQueue().add(request);

        }catch(Exception e){
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Upps algo inesperado sucedio, vuelve a intentarlo: " + e.getMessage(), Toast.LENGTH_SHORT).show();        }


    }

    public List<NegocioDto> parseJsonNegocio(JSONArray response){
        List<NegocioDto> listNegocio = new ArrayList<NegocioDto>();
        try {

            for (int i = 0; i < response.length(); i++) {

                JSONObject negocio = (JSONObject) response.get(i);

                NegocioDto negocioDto = new NegocioDto();
                negocioDto.setIdNegocio(negocio.getString("idNegocio"));
                negocioDto.setNombre(negocio.getString("nombre"));
                negocioDto.setDescripcion(negocio.getString("descripcion"));
                negocioDto.setEstatus(negocio.getBoolean("estatus"));
                negocioDto.setDomicilio(negocio.getString("domicilio"));
                negocioDto.setSitioWeb(negocio.getString("sitioWeb"));
                negocioDto.setCategoria(negocio.getString("nombreCategoria"));
                negocioDto.setCalificacion(negocio.getString("calificacion"));
                negocioDto.setDistancia(negocio.getString("distancia"));
                negocioDto.setNumeroOfertas(negocio.getInt("numeroOfertas"));
                negocioDto.setHorario(negocio.getString("horarioDia"));

                listNegocio.add(negocioDto);
            }
            Toast.makeText(getApplicationContext(), " Negocios : "+listNegocio.size(), Toast.LENGTH_SHORT).show();

        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(),
                    "Error: " + e.getMessage(),
                    Toast.LENGTH_LONG).show();
        }
        return listNegocio;
    }


}
