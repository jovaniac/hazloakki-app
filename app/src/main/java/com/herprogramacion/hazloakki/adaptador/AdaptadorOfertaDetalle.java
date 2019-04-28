package com.herprogramacion.hazloakki.adaptador;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.herprogramacion.hazloakki.R;
import com.herprogramacion.hazloakki.modelo.OfertaComentariosDto;
import com.herprogramacion.hazloakki.modelo.OfertaDescripcionDto;
import com.herprogramacion.hazloakki.modelo.OfertaInsightsDto;
import com.herprogramacion.hazloakki.modelo.OfertaNegocioDto;
import com.herprogramacion.hazloakki.modelo.OfertaValoracionDto;
import com.herprogramacion.hazloakki.modelo.RecyclerViewItem;

import java.util.List;

public class AdaptadorOfertaDetalle extends RecyclerView.Adapter {

    private static final int ITEM_OFERTA_NEGOCIO = 0;
    private static final int ITEM_OFERTA_VALORACION = 1;
    private static final int ITEM_OFERTA_INSIGHTS = 2;
    private static final int ITEM_OFERTA_DESCRIPCION = 3;
    private static final int ITEM_OFERTA_COMENTARIOS= 4;
    List<RecyclerViewItem> recyclerViewItems;
    private Context mContext;

    public AdaptadorOfertaDetalle(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View row;
        //Check fot view Type inflate layout according to it

        if (viewType == ITEM_OFERTA_NEGOCIO) {
            row = inflater.inflate(R.layout.content_detalle_oferta_negocio, parent, false);
            return new OfertaNegocio(row);
        }else if (viewType == ITEM_OFERTA_VALORACION) {
            row = inflater.inflate(R.layout.content_detalle_oferta_valoracion, parent, false);
            return new ValoracionOferta(row);
        }else if (viewType == ITEM_OFERTA_INSIGHTS) {
            row = inflater.inflate(R.layout.content_detalle_oferta_insights, parent, false);
            return new OfertaInsights(row);
        }else if (viewType == ITEM_OFERTA_DESCRIPCION) {
            row = inflater.inflate(R.layout.content_detalle_oferta_descripcion, parent, false);
            return new OfertaDescripcion(row);
        }else if (viewType == ITEM_OFERTA_COMENTARIOS) {
            row = inflater.inflate(R.layout.content_detalle_oferta_comentarios_top, parent, false);
            return new OfertaComentarios(row);
        }
        return null;

        }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        RecyclerViewItem recyclerViewItem = recyclerViewItems.get(position);
        //Check holder instance to populate data  according to it
        if (holder instanceof AdaptadorOfertaDetalle.OfertaNegocio) {
            AdaptadorOfertaDetalle.OfertaNegocio datosOfertaInit = (AdaptadorOfertaDetalle.OfertaNegocio) holder;
            OfertaNegocioDto header = (OfertaNegocioDto) recyclerViewItem;

        }

    }


    @Override
        public int getItemViewType(int position) {
            //here we can set view type
            RecyclerViewItem recyclerViewItem = recyclerViewItems.get(position);
            //if its header then return header item
            if (recyclerViewItem instanceof OfertaNegocioDto)
                return ITEM_OFERTA_NEGOCIO;
            else if(recyclerViewItem instanceof OfertaValoracionDto)
                return ITEM_OFERTA_VALORACION;
            else if(recyclerViewItem instanceof OfertaInsightsDto)
                return ITEM_OFERTA_INSIGHTS;
            else if(recyclerViewItem instanceof OfertaDescripcionDto)
                return ITEM_OFERTA_DESCRIPCION;
            else if(recyclerViewItem instanceof OfertaComentariosDto)
                return ITEM_OFERTA_COMENTARIOS;
            else
                return super.getItemViewType(position);

        }

        @Override
        public int getItemCount () {
            return recyclerViewItems.size();
        }

        private class OfertaNegocio extends RecyclerView.ViewHolder {

            public OfertaNegocio(View v) {
                super(v);
            }
        }

        private class ValoracionOferta extends RecyclerView.ViewHolder {

            public ValoracionOferta(View v) {
                super(v);
            }
        }

        private class OfertaInsights extends RecyclerView.ViewHolder {

            public OfertaInsights(View v) {
                super(v);
            }
        }

        private class OfertaDescripcion extends RecyclerView.ViewHolder {

            public OfertaDescripcion(View v) {
                super(v);
            }
        }

        private class OfertaComentarios extends RecyclerView.ViewHolder {

            public OfertaComentarios(View v) {
                super(v);
            }
        }

    public void setRecyclerViewItems(List<RecyclerViewItem> recyclerViewItems) {
        this.recyclerViewItems = recyclerViewItems;
    }
}
