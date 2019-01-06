package com.herprogramacion.hazloakki.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.herprogramacion.hazloakki.R;
import com.herprogramacion.hazloakki.modelo.AccionesDto;

import java.util.List;

/**
 * Created by JAarzate on 19/11/2017.
 */

public class AdaptadorAcciones extends RecyclerView.Adapter<AdaptadorAcciones.AccionesViewHolder> {

    private List<AccionesDto> items;
    private Context mCtx;

    public AdaptadorAcciones(Context mCtx, List<AccionesDto> productList) {
        this.mCtx = mCtx;
        this.items = productList;
    }

    @Override
    public AccionesViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_lista_categorias, viewGroup, false);
        return new AccionesViewHolder(v);
    }

    public void onBindViewHolder(AccionesViewHolder viewHolder, int i) {
        AccionesDto item = items.get(i);

        Glide.with(viewHolder.itemView.getContext()).load(item.getIdAccion()).centerCrop().into(viewHolder.imagen);
        viewHolder.nombre.setText(item.getNombre());
        viewHolder.descripcion.setText(item.getDescripcion());

    }

    public  class AccionesViewHolder extends RecyclerView.ViewHolder{
        // Campos respectivos de un item
        public TextView nombre;
        public TextView descripcion;
        public ImageView imagen;

        public AccionesViewHolder(View v) {
            super(v);

            nombre = (TextView) v.findViewById(R.id.nombre_comida);
            descripcion = (TextView) v.findViewById(R.id.precio_comida);
            imagen = (ImageView) v.findViewById(R.id.miniatura_comida);
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public List<AccionesDto> getItems() {
        return items;
    }

}
