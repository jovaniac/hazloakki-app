package com.herprogramacion.hazloakki.adaptador;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.herprogramacion.hazloakki.modelo.OfertasDto;
import com.herprogramacion.hazloakki.R;
import com.herprogramacion.hazloakki.ui.ActividadOfertaDetalle;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ProductsAdapter extends RecyclerView.Adapter {
    List<OfertasDto> mProducts;
    Context mContext;
    public static final int LOADING_ITEM = 0;
    public static final int PRODUCT_ITEM = 1;
    int LoadingItemPos;
    public boolean loading = false;
    private List<OfertasDto> listaOfertas;

    public ProductsAdapter(Context mContext) {
        mProducts = new ArrayList<>();
        this.mContext = mContext;
    }

    public void addOfertas(List<OfertasDto> listaOfertas) {
        int lastPos = mProducts.size();
        this.mProducts.addAll(listaOfertas);
        notifyItemRangeInserted(lastPos, mProducts.size());
    }


    @Override
    public int getItemViewType(int position) {
        OfertasDto ofertaActual = mProducts.get(position);
        if (ofertaActual.isLoading()) {
            return LOADING_ITEM;
        } else {
            return PRODUCT_ITEM;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        //Check which view has to be populated
        if (viewType == LOADING_ITEM) {
            View row = inflater.inflate(R.layout.custom_row_loading, parent, false);
            return new LoadingHolder(row);
        } else if (viewType == PRODUCT_ITEM) {
            View row = inflater.inflate(R.layout.custom_row_product, parent, false);
            return new ProductHolder(row);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        //get current product
        final OfertasDto currentOferta = mProducts.get(position);
        if (holder instanceof ProductHolder) {
            ProductHolder productHolder = (ProductHolder) holder;
            //bind products information with view
            Picasso.with(mContext).load("https://static.promodescuentos.com/pepperpdimages/threads/thread_large/default/298542_1.jpg").into(productHolder.imageViewProductThumb);
            productHolder.textViewTitulo.setText(currentOferta.getTitulo());
            productHolder.textViewPrecio.setText("Precio : $"+ currentOferta.getPrecio());
            productHolder.textViewCalificacion.setText("Calificacion: "+ String.valueOf(currentOferta.getCalificacion()));
            productHolder.textViewMeGusta.setText("Me Gusta: "+ String.valueOf(currentOferta.getMeGusta()));
            productHolder.textViewTiempoPublicacion.setText(currentOferta.getFecha());
            if (currentOferta.isToday()) {
                productHolder.textViewTiempoPublicacion.setVisibility(View.VISIBLE);
            }
            else
                productHolder.textViewTiempoPublicacion.setVisibility(View.GONE);

            productHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // user selected product now you can show details of that product
                    Toast.makeText(mContext, "Seleccionas la oferta: "+currentOferta.getId(), Toast.LENGTH_SHORT).show();
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return mProducts.size();
    }

    //Holds view of product with information
    private class ProductHolder extends RecyclerView.ViewHolder {
        ImageView imageViewProductThumb;
        TextView textViewTitulo;
        TextView textViewPrecio;
        TextView textViewMeGusta;
        TextView textViewCalificacion;
        TextView textViewTiempoPublicacion;
        private Button irOfertaButton;



        public ProductHolder(View itemView) {
            super(itemView);
            imageViewProductThumb = itemView.findViewById(R.id.imageViewProductThumb);
            textViewTitulo = itemView.findViewById(R.id.tituloOferta);
            textViewPrecio = itemView.findViewById(R.id.precioOferta);
            textViewCalificacion = itemView.findViewById(R.id.calificacionOferta);
            textViewMeGusta = itemView.findViewById(R.id.contadorMeGusta);
            textViewTiempoPublicacion = itemView.findViewById(R.id.textViewTiempoPublicacion);
            irOfertaButton = itemView.findViewById(R.id.irOfertaButton);

            irOfertaButton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    Toast.makeText(mContext, "IR a la oferta", Toast.LENGTH_LONG).show();

                    Intent detalleOferta = new Intent(mContext.getApplicationContext(), ActividadOfertaDetalle.class);
                    detalleOferta.putExtra("idOferta","582291e4-44cf-4341-96b4-fc4a6f7e98f7");

                    mContext.startActivity(detalleOferta);
                }
            });

        }
    }
    //holds view of loading item
    private class LoadingHolder extends RecyclerView.ViewHolder {
        public LoadingHolder(View itemView) {
            super(itemView);
        }
    }

    /*method to show loading
    public void showLoading() {
        Product product = new Product();
        product.setLoading(true);
        mProducts.add(product);
        LoadingItemPos = mProducts.size();
        notifyItemInserted(mProducts.size());
        loading = true;
    }*/

    //method to hide loading
    public void hideLoading() {
        if (LoadingItemPos <= mProducts.size()) {
            mProducts.remove(LoadingItemPos - 1);
            notifyItemRemoved(LoadingItemPos);
            loading = false;
        }

    }

    public List<OfertasDto> getListaOfertas() {
        return listaOfertas;
    }

    public void setListaOfertas(List<OfertasDto> listaOfertas) {
        this.listaOfertas = listaOfertas;
    }
}