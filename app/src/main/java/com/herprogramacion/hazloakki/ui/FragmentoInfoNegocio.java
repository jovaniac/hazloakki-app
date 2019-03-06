package com.herprogramacion.hazloakki.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.google.gson.Gson;
import com.herprogramacion.hazloakki.R;
import com.herprogramacion.hazloakki.adaptador.AdaptadorInfoNegocio;
import com.herprogramacion.hazloakki.modelo.FoodItem;
import com.herprogramacion.hazloakki.modelo.Footer;
import com.herprogramacion.hazloakki.modelo.NegocioInfoDatosContacto;
import com.herprogramacion.hazloakki.modelo.NegocioInfoDireccionDto;
import com.herprogramacion.hazloakki.modelo.NegocioInfoHeader;
import com.herprogramacion.hazloakki.modelo.NegocioDto;
import com.herprogramacion.hazloakki.modelo.NegocioInfoHeaderDatosContacto;
import com.herprogramacion.hazloakki.modelo.NegocioInfoHeaderDireccion;
import com.herprogramacion.hazloakki.modelo.RecyclerViewItem;
import com.herprogramacion.hazloakki.network.AppController;
import com.herprogramacion.hazloakki.utils.EspacioInfoNegocio;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class FragmentoInfoNegocio extends Fragment {

    private String REQUEST_NEGOCIOS= "http://192.168.0.5:8086/api/v1/negocios/";
    Gson gsonConvert = new Gson();
    NegocioDto negocioDtoConDatos = new NegocioDto();
    RecyclerView recyclerView;
    AdaptadorInfoNegocio adaptadorInfoNegocio;

    public FragmentoInfoNegocio() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragmento_info_negocio, container, false);

        String idNegocio = getArguments().getString("idNegocio");

        adaptadorInfoNegocio = new AdaptadorInfoNegocio(getContext());

       //initRecyclerView(view,idNegocio);
        recyclerView =  view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        //add space item decoration and pass space you want to give
        recyclerView.addItemDecoration(new EspacioInfoNegocio(20));

        detalleNegocio(idNegocio);

        return view;
    }

    //Method to create dummy data
    public List<RecyclerViewItem> cargarDatosNegocio(NegocioDto negocioDtIn) {
        List<RecyclerViewItem> recyclerViewItems = new ArrayList<>();


        NegocioDto negocioDto = new NegocioDto();
        negocioDto.setDescripcion(negocioDtoConDatos.getDescripcion());
        negocioDto.setCategoria(negocioDtoConDatos.getCategoria());
        negocioDto.setNombre(negocioDtoConDatos.getNombre());

        NegocioInfoHeader header = new NegocioInfoHeader("https://cdn.pixabay.com/photo/2017/09/30/15/10/pizza-2802332_640.jpg",negocioDto);
        //add header
        recyclerViewItems.add(header);


        NegocioInfoHeaderDireccion negocioInfoHeaderDireccion = new NegocioInfoHeaderDireccion();


        recyclerViewItems.add(negocioInfoHeaderDireccion);


        NegocioInfoDireccionDto negocioInfoDireccionDto = new NegocioInfoDireccionDto();

        negocioInfoDireccionDto.setDireccion(negocioDtoConDatos.getDomicilio());
        negocioInfoDireccionDto.setColonia(negocioDtoConDatos.getColonia());
        negocioInfoDireccionDto.setHorario(negocioDtoConDatos.getHorario());
        negocioInfoDireccionDto.setDistancia(negocioDtoConDatos.getDistancia());
        negocioInfoDireccionDto.setNumeroOfertasPublicadas(String.valueOf(negocioDtoConDatos.getNumeroOfertas()));


        //add direccion negocio
        recyclerViewItems.add(negocioInfoDireccionDto);

        NegocioInfoHeaderDatosContacto infoHeaderDatosContacto  =new NegocioInfoHeaderDatosContacto();
        recyclerViewItems.add(infoHeaderDatosContacto);

        NegocioInfoDatosContacto negocioInfoDatosContacto = new NegocioInfoDatosContacto();
        recyclerViewItems.add(negocioInfoDatosContacto);
/*

        String[] imageUrls = {"https://cdn.pixabay.com/photo/2016/11/18/17/42/barbecue-1836053_640.jpg",
                "https://cdn.pixabay.com/photo/2016/07/11/03/23/chicken-rice-1508984_640.jpg",
                "https://cdn.pixabay.com/photo/2017/03/30/08/10/chicken-intestine-2187505_640.jpg",
                "https://cdn.pixabay.com/photo/2017/02/15/15/17/meal-2069021_640.jpg",
                "https://cdn.pixabay.com/photo/2017/06/01/07/15/food-2362678_640.jpg"};
        String[] titles = {"5 in 1 Chicken Zinger Box",
                "Paneer Butter Masala",
                "Chicken Lollipop Masala", "Paneer Manchurian", "Non-Veg. Lemon & Coriander Soup"};
        String[] descriptions = {"Chicken zinger+hot wings [2 pieces]+veg strip [1 piece]+Pillsbury cookie cake+Pepsi [can]",
                "A spicy North Indian dish made from cottage cheese, cream, butter and select spices",
                "Chicken wings coated with batter of flour",
                "Deep-fried cottage cheese balls sautéed with ginger", "Meat shreds, lime juice and coriander"};
        String[] price = {"₹220", "₹530", "₹400", "₹790", "₹150"};

        boolean[] isHot = {true, false, true, true, false};

        for (int i = 0; i < imageUrls.length; i++) {
            FoodItem foodItem = new FoodItem(titles[i], descriptions[i], imageUrls[i], price[i],isHot[i]);
            //add food items
            recyclerViewItems.add(foodItem);
        }
        */

        Footer footer = new Footer("Your diet is a bank account. Good food choices are good investments.",
                "Bethenny Frankel", "https://cdn.pixabay.com/photo/2016/12/26/17/28/background-1932466_640.jpg");
        //add footer


        recyclerViewItems.add(footer);


        return recyclerViewItems;
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
                        negocioDtoConDatos = gsonConvert.fromJson(response.toString(),NegocioDto.class);
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


    public NegocioDto getNegocioDtoConDatos() {
        return negocioDtoConDatos;
    }

    public void setNegocioDtoConDatos(NegocioDto negocioDtoConDatos) {
        this.negocioDtoConDatos = negocioDtoConDatos;
    }
}
