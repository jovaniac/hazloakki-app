package com.herprogramacion.hazloakki.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.herprogramacion.hazloakki.R;
import com.herprogramacion.hazloakki.adaptador.AdaptadorInfoNegocio;
import com.herprogramacion.hazloakki.modelo.FoodItem;
import com.herprogramacion.hazloakki.modelo.Footer;
import com.herprogramacion.hazloakki.modelo.NegocioInfoDireccionDto;
import com.herprogramacion.hazloakki.modelo.NegocioInfoHeader;
import com.herprogramacion.hazloakki.modelo.NegocioDto;
import com.herprogramacion.hazloakki.modelo.RecyclerViewItem;
import com.herprogramacion.hazloakki.utils.EspacioInfoNegocio;

import java.util.ArrayList;
import java.util.List;

public class FragmentoInfoNegocio extends Fragment {

    public FragmentoInfoNegocio() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragmento_info_negocio, container, false);
        initRecyclerView(view);

        return view;
    }

    public void initRecyclerView(View view) {
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        //add space item decoration and pass space you want to give
        recyclerView.addItemDecoration(new EspacioInfoNegocio(20));
        //finally set adapter
        recyclerView.setAdapter(new AdaptadorInfoNegocio(createDummyList(), getActivity()));


    }
    //Method to create dummy data
    public List<RecyclerViewItem> createDummyList() {
        List<RecyclerViewItem> recyclerViewItems = new ArrayList<>();

        NegocioDto negocioDto = new NegocioDto();
        negocioDto.setDescripcion("Negocio de Carnitas bien ricas");
        negocioDto.setCategoria("Restaurante");
        negocioDto.setNombre("Carnitas Blaikiriña");

        NegocioInfoHeader header = new NegocioInfoHeader("Welcome To Food Express", "Non-Veg Menu",
                "https://cdn.pixabay.com/photo/2017/09/30/15/10/pizza-2802332_640.jpg",negocioDto);
        //add header
        recyclerViewItems.add(header);


        NegocioInfoDireccionDto negocioInfoDireccionDto = new NegocioInfoDireccionDto();

        negocioInfoDireccionDto.setDireccion("Codigo postal + calle + colonia + estado + municipio");
        negocioInfoDireccionDto.setColonia("Temixco");
        negocioInfoDireccionDto.setHorario("Abierto Hoy cierra a las 9pm");
        negocioInfoDireccionDto.setDistancia("0.3 kilometros cerca de ti");
        negocioInfoDireccionDto.setNumeroOfertasPublicadas("10 ofertas Publicadas");


        //add direccion negocio
        recyclerViewItems.add(negocioInfoDireccionDto);


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

        Footer footer = new Footer("Your diet is a bank account. Good food choices are good investments.",
                "Bethenny Frankel", "https://cdn.pixabay.com/photo/2016/12/26/17/28/background-1932466_640.jpg");
        //add footer
        recyclerViewItems.add(footer);
        return recyclerViewItems;
    }

}
