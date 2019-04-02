package com.herprogramacion.hazloakki.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.google.gson.Gson;
import com.herprogramacion.hazloakki.R;
import com.herprogramacion.hazloakki.modelo.NegocioDto;
import com.herprogramacion.hazloakki.modelo.OfertasDto;
import com.herprogramacion.hazloakki.network.AppController;

import org.json.JSONObject;

public class ActividadInfoOferta extends AppCompatActivity {

    /*private String REQUEST_OFERTA_BY_ID="http://192.168.0.8:8089/api/v1/ofertas/";
    private OfertasDto ofertasDtoConDatos = null;
    Gson gsonConvert = new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_oferta);
    }

    public void detalleNegocio(String idNegocio) {
        sendRequestJsonPost(idNegocio);
    }

    public void sendRequestJsonPost(String idNegocio) {


        StringBuilder urlMoreParameters = new StringBuilder(REQUEST_OFERTA_BY_ID);
        urlMoreParameters.append(idNegocio);


        try {
            JsonRequest jsonRequest = new JsonObjectRequest(Request.Method.GET, urlMoreParameters.toString(), null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {

                    try {
                        ofertasDtoConDatos = gsonConvert.fromJson(response.toString(),OfertasDto.class);
                        adaptadorInfoNegocio.setRecyclerViewItems(cargarDatosNegocio(negocioDtoConDatos));

                        recyclerView.setAdapter(adaptadorInfoNegocio);
                        Toast.makeText(getContext(), "Response toString FragmentoInfoNegocio: " +negocioDtoConDatos.toString(), Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    Toast.makeText(getContext(),
                            "Error: " + error.getMessage(),
                            Toast.LENGTH_LONG).show();
                }
            });

            AppController.getInstance(getContext()).getRequestQueue().add(jsonRequest);

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getContext(), "Upps algo inesperado sucedio, vuelve a intentarlo: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    */
}
