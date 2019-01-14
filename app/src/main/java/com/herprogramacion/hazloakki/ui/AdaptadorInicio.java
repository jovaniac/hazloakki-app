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
import com.herprogramacion.hazloakki.modelo.Comida;

import java.util.List;

/**
 * Adaptador para mostrar las comidas más pedidas en la sección "Inicio"
 */
public class AdaptadorInicio
        extends RecyclerView.Adapter<AdaptadorInicio.ViewHolder> {


    private List<AccionesDto> items;
    private Context mCtx;

    public AdaptadorInicio(Context mCtx, List<AccionesDto> productList) {
        this.mCtx = mCtx;
        this.items = productList;
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // Campos respectivos de un item
        public TextView nombre;
        public TextView precio;
        public ImageView imagen;

        public ViewHolder(View v) {
            super(v);
            nombre = (TextView) v.findViewById(R.id.nombre_comida);
            precio = (TextView) v.findViewById(R.id.precio_comida);
            imagen = (ImageView) v.findViewById(R.id.miniatura_comida);
        }
    }

    public AdaptadorInicio() {
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_lista_inicio, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        AccionesDto item = items.get(i);

        Glide.with(viewHolder.itemView.getContext())
                .load(item.getIdAccion())
                .centerCrop()
                .into(viewHolder.imagen);
        viewHolder.nombre.setText(item.getDescripcion());
        viewHolder.precio.setText(item.getNombre());

    }

    public void onBindViewHolder(AdaptadorAcciones.AccionesViewHolder viewHolder, int i) {
        AccionesDto item = items.get(i);

        Glide.with(viewHolder.itemView.getContext()).load(item.getIdAccion()).centerCrop().into(viewHolder.imagen);
        viewHolder.nombre.setText(item.getNombre());
        viewHolder.descripcion.setText(item.getDescripcion());

    }

    public List<AccionesDto> getItems() {
        return items;
    }

    public void setItems(List<AccionesDto> items) {
        this.items = items;
    }
}