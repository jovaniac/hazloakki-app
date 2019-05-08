package com.herprogramacion.hazloakki.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.google.gson.Gson;
import com.herprogramacion.hazloakki.R;
import com.herprogramacion.hazloakki.adaptador.AdaptadorOfertaDetalle;

import com.herprogramacion.hazloakki.modelo.OfertaComentariosDto;
import com.herprogramacion.hazloakki.modelo.OfertaDescripcionDto;
import com.herprogramacion.hazloakki.modelo.OfertaDetalleDto;
import com.herprogramacion.hazloakki.modelo.OfertaInsightsDto;
import com.herprogramacion.hazloakki.modelo.OfertaNegocioDto;
import com.herprogramacion.hazloakki.modelo.OfertaValoracionDto;
import com.herprogramacion.hazloakki.modelo.RecyclerViewItem;

import com.herprogramacion.hazloakki.network.AppController;
import com.herprogramacion.hazloakki.utils.EspacioInfoNegocio;

import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;


public class ActividadOfertaDetalle extends AppCompatActivity {

    private AdaptadorOfertaDetalle adaptadorOfertaDetalle;
    private RecyclerView recyclerView;
    private OfertaDetalleDto ofertaDetalleDto;
    private String REQUEST_OFERTAS_DETALLE ="http://192.168.0.6:8089/api/v1/ofertas/";
    private Gson gsonConvert = new Gson();
    private String idOferta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_oferta_detalle);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_oferta_detalle);


        Bundle bundle = getIntent().getExtras();
        Bundle data = new Bundle();//Use bundle to pass data

        if (!bundle.isEmpty()) {
            idOferta = bundle.getString("idOferta");
        }

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new EspacioInfoNegocio(10));

        adaptadorOfertaDetalle = new AdaptadorOfertaDetalle(getApplicationContext());
        sendRequestJsonGet(idOferta);

    }

    public void sendRequestJsonGet(String idOferta) {

        StringBuilder urlMoreParameters = new StringBuilder(REQUEST_OFERTAS_DETALLE);
        urlMoreParameters.append(idOferta);

        try {
            JsonRequest jsonRequest = new JsonObjectRequest(Request.Method.GET, urlMoreParameters.toString(), null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {

                    try {
                        ofertaDetalleDto = gsonConvert.fromJson(response.toString(),OfertaDetalleDto.class);
                        adaptadorOfertaDetalle.setRecyclerViewItems(cargarDetalleOferta(ofertaDetalleDto));

                        recyclerView.setAdapter(adaptadorOfertaDetalle);
                        Toast.makeText(getApplicationContext(), "Response toString ActividadOfertaDetalle: " +ofertaDetalleDto.toString(), Toast.LENGTH_LONG).show();
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


    public List<RecyclerViewItem> cargarDetalleOferta(OfertaDetalleDto ofertaDetalleDto){


        List<RecyclerViewItem> recyclerViewItems = new ArrayList<>();

            OfertaNegocioDto ofertaNegocioDto = new OfertaNegocioDto();
            ofertaNegocioDto.setFechaExpiracion("2019-05-01");
            ofertaNegocioDto.setFechaPublicacion("2018-04-28");

        recyclerViewItems.add(ofertaNegocioDto);

            OfertaValoracionDto ofertaValoracionDto = new OfertaValoracionDto();
            ofertaValoracionDto.setCalificacion("7");
            ofertaValoracionDto.setRating("3");
            ofertaValoracionDto.setVistas("180000");

        recyclerViewItems.add(ofertaValoracionDto);

            OfertaInsightsDto ofertaInsightsDto = new OfertaInsightsDto();

        recyclerViewItems.add(ofertaInsightsDto);

            OfertaDescripcionDto ofertaDescripcionDto = new OfertaDescripcionDto();
            ofertaDescripcionDto.setDescripcionOferta("Dos cartones de 12 coronitas de 210ml por solo $160.\n" +
                    "\n" +
                    "Muy buena oferta considerando que directamente en la corona vale $184 el 24(eso si llevas el envase).\n" +
                    "También aplica en victoria pero marca no disponible.\n");
            ofertaDescripcionDto.setImagen(new String[1]);
            ofertaDescripcionDto.setImagenPerfil("imagen del perfil de la oferta");
            ofertaDescripcionDto.setTiempoPublicacion("12 Minutos");

        recyclerViewItems.add(ofertaDescripcionDto);

        OfertaComentariosDto ofertaComentariosDto = new OfertaComentariosDto();
         ofertaComentariosDto.setComentario("la oferta esta bien chingona");
         ofertaComentariosDto.setFechaPublicacion("2019-08-12");
         ofertaComentariosDto.setNumeroMeGusta("10");
         ofertaComentariosDto.setUsuario("pepito");
            recyclerViewItems.add(ofertaComentariosDto);

        ofertaComentariosDto = new OfertaComentariosDto();
        ofertaComentariosDto.setComentario("la oferta esta bien chingona 2");
        ofertaComentariosDto.setFechaPublicacion("2019-08-12");
        ofertaComentariosDto.setNumeroMeGusta("10");
        ofertaComentariosDto.setUsuario("pedrito");
        recyclerViewItems.add(ofertaComentariosDto);


        ofertaComentariosDto = new OfertaComentariosDto();
        ofertaComentariosDto.setComentario("la oferta esta bien chingona 3 ");
        ofertaComentariosDto.setFechaPublicacion("2019-08-12");
        ofertaComentariosDto.setNumeroMeGusta("10");
        ofertaComentariosDto.setUsuario("juanito");
            recyclerViewItems.add(ofertaComentariosDto);

        return recyclerViewItems;
    }


}
