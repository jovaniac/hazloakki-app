package com.herprogramacion.hazloakki.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.herprogramacion.hazloakki.adaptador.AdaptadorOfertas2;
import com.herprogramacion.hazloakki.adaptador.ProductsAdapter;
import com.herprogramacion.hazloakki.modelo.OfertasDto;
import com.herprogramacion.hazloakki.modelo.Product;
import com.herprogramacion.hazloakki.network.AppController;
import com.herprogramacion.hazloakki.utils.Space;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class FragmentoOfertasNegocio extends Fragment implements AdaptadorOfertas2.OnItemClickListener{

    private RecyclerView listaUI;
    private LinearLayoutManager linearLayoutManager;
    private String REQUEST_OFERTAS = "http://192.168.0.6:8089/api/v1/ofertas/negocios/";
    private static String TAG = NegociosRecyclerView.class.getSimpleName();
    private static Context ctx;
    private ProductsAdapter ofertasAdaptador;
    private String idNegocio  = null;
    private RecyclerView recyclerViewProducts;


    public FragmentoOfertasNegocio() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_main_fashion, container, false);
        recyclerViewProducts = view.findViewById(R.id.recyclerViewProducts);

        ofertasAdaptador = new ProductsAdapter(getContext());
        idNegocio = getArguments().getString("idNegocio");



        //Create new GridLayoutManager
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),
                2,//span count no of items in single row
                GridLayoutManager.VERTICAL,//Orientation
                false);//reverse scrolling of recyclerview
        //set layout manager as gridLayoutManager

        recyclerViewProducts.setLayoutManager(gridLayoutManager);


        //Crete new EndlessScrollListener fo endless recyclerview loading
        /*EndlessScrollListener endlessScrollListener = new EndlessScrollListener(gridLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount) {
                if (!productsAdapter.loading)
                    feedData();
            }
        };*/

        //to give loading item full single row
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                switch (ofertasAdaptador.getItemViewType(position)) {
                    case ProductsAdapter.PRODUCT_ITEM:
                        return 1;
                    case ProductsAdapter.LOADING_ITEM:
                        return 2; //number of columns of the grid
                    default:
                        return -1;
                }
            }
        });



        ctx = getContext();

        //feedData();

        // recyclerViewProducts.addOnScrollListener(endlessScrollListener);
        //add space between cards
        recyclerViewProducts.addItemDecoration(new Space(2, 20, true, 0));

        //recyclerViewProducts.setAdapter(productsAdapter);

        //load first page of recyclerview
        //endlessScrollListener.onLoadMore(0, 0);


        initOfertas();

        return view;
    }


    //Load Data from your server here
    // loading data from server will make it very large
    // that's why i created data locally
    private void feedData() {
        //show loading in recyclerview
        //productsAdapter.showLoading();

        final List<Product> products = new ArrayList<>();
        int[] imageUrls = {R.drawable.img1, R.drawable.img2, R.drawable.img3, R.drawable.img4};
        String[] ProductName = {"Kingsmon Top", "Adidas Top", "Butterfly Top", "White Top"};
        String[] ProductPrice = {"₹594", "₹5000", "₹200", "₹1999"};

        boolean[] isNew = {true, false, false, true};

        for (int i = 0; i < imageUrls.length; i++) {
            Product product = new Product(imageUrls[i],
                    ProductName[i],
                    ProductPrice[i],
                    isNew[i]);

            products.add(product);
        }

        //productsAdapter.addProducts(products);



    }

    public void initOfertas(){

        try {
            Gson gson = new Gson();
            Map<String, String> params = new HashMap<String, String>();
            params.put("idNegocio", idNegocio);
            sendRequestJsonGet(REQUEST_OFERTAS,params);
        } catch (Exception e) {
            Toast.makeText(getContext(), "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

    public  void sendRequestJsonGet(String url, final Map<String, String> params ) {

        String idNegocio = params.get("idNegocio");

        StringBuilder urlMoreParameters = new StringBuilder(url);
        urlMoreParameters.append(idNegocio);

        try {

            JsonRequest<JSONArray> request  = new JsonRequest<JSONArray>(Request.Method.GET, urlMoreParameters.toString(), null,
                    new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {
                            //Toast.makeText(getContext(), "Response toString FragmentoOfertasNegocio: " +response.toString(), Toast.LENGTH_LONG).show();

                            ofertasAdaptador.addOfertas(parseJson(response));
                            // productsAdapter.setListaOfertas(parseJson(response));
                            recyclerViewProducts.setAdapter(ofertasAdaptador);
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getContext(), "No hay ofertas cargas para este negocio: " + error.getMessage(), Toast.LENGTH_LONG).show();
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

                JSONObject data = (JSONObject) response.get(i);

                OfertasDto ofertas = new OfertasDto();
                ofertas.setId(data.getString("id"));
                ofertas.setIdNegocio(data.getString("idNegocio"));
                ofertas.setTitulo(data.getString("titulo"));
                ofertas.setMensaje(data.getString("mensaje"));
                ofertas.setImagen(data.getString("imagen"));
                ofertas.setFecha(data.getString("fecha"));
                ofertas.setDuracion(data.getString("duracion"));
                ofertas.setPrecio(data.getString("precio"));
                ofertas.setEstatus(data.getBoolean("estatus"));

                listOfertas.add(ofertas);
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
    public void onClick(AdaptadorOfertas2.ViewHolder holder, String idAlquiler) {
        Toast.makeText(getContext(),"Oferta Seleccionada:"+idAlquiler, Toast.LENGTH_LONG).show();

        Intent detalleNegocio = new Intent(getActivity().getApplicationContext(), ActividadDetalleNegocio.class);

        startActivity(detalleNegocio);
    }

    public String getIdNegocio() {
        return idNegocio;
    }

    public void setIdNegocio(String idNegocio) {
        this.idNegocio = idNegocio;
    }
}
