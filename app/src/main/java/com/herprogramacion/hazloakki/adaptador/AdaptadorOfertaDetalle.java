package com.herprogramacion.hazloakki.adaptador;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;


import com.herprogramacion.hazloakki.R;
import com.herprogramacion.hazloakki.modelo.OfertaComentariosDto;
import com.herprogramacion.hazloakki.modelo.OfertaDescripcionDto;
import com.herprogramacion.hazloakki.modelo.OfertaInsightsDto;
import com.herprogramacion.hazloakki.modelo.OfertaNegocioDto;
import com.herprogramacion.hazloakki.modelo.OfertaValoracionDto;
import com.herprogramacion.hazloakki.modelo.RecyclerViewItem;

import org.w3c.dom.Text;

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
            AdaptadorOfertaDetalle.OfertaNegocio datosOferta = (AdaptadorOfertaDetalle.OfertaNegocio) holder;
                OfertaNegocioDto datosOfertaIn = (OfertaNegocioDto) recyclerViewItem;
                datosOferta.fechaPublicacion.setText(datosOfertaIn.getFechaPublicacion());
                datosOferta.fechaExpiracion.setText(datosOfertaIn.getFechaExpiracion());

        }else if(holder instanceof AdaptadorOfertaDetalle.ValoracionOferta){
            AdaptadorOfertaDetalle.ValoracionOferta valoracionOferta = (AdaptadorOfertaDetalle.ValoracionOferta) holder;
                OfertaValoracionDto ofertaValoracionDto = (OfertaValoracionDto) recyclerViewItem;
                    String calificacion  = valoracionOferta.calificacion.getText().toString();
                     calificacion = calificacion.replace("%",ofertaValoracionDto.getCalificacion());
                     calificacion = calificacion.replace("#",ofertaValoracionDto.getVistas());

                     valoracionOferta.calificacion.setText(calificacion);
                     valoracionOferta.rating.setRating(Float.parseFloat(ofertaValoracionDto.getRating()));
                     valoracionOferta.vistas.setText(ofertaValoracionDto.getVistas());

        }else if(holder instanceof AdaptadorOfertaDetalle.OfertaDescripcion){
            AdaptadorOfertaDetalle.OfertaDescripcion descripcionOferta = (AdaptadorOfertaDetalle.OfertaDescripcion) holder;
                OfertaDescripcionDto ofertaDescripcion = (OfertaDescripcionDto) recyclerViewItem;
            descripcionOferta.descripcionOferta.setText(ofertaDescripcion.getDescripcionOferta());
            descripcionOferta.tiempoPublicacion.setText(ofertaDescripcion.getTiempoPublicacion());
        }else if(holder instanceof AdaptadorOfertaDetalle.OfertaComentarios){
            AdaptadorOfertaDetalle.OfertaComentarios ofertaComentario = (AdaptadorOfertaDetalle.OfertaComentarios) holder;
                OfertaComentariosDto ofertaComentariosDto = (OfertaComentariosDto) recyclerViewItem;
                    ofertaComentario.numMeGusta.setText(ofertaComentariosDto.getNumeroMeGusta());
                    ofertaComentario.comentarios.setText(ofertaComentariosDto.getComentario());
                    ofertaComentario.usuario.setText(ofertaComentariosDto.getUsuario());
                    ofertaComentario.fechaPublicaComentario.setText(ofertaComentariosDto.getFechaPublicacion());

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

            TextView fechaPublicacion;
            TextView fechaExpiracion;
            Button btnIrNegocio;
            Button btnOpenChat;

            public OfertaNegocio(View v) {
                super(v);
                fechaPublicacion = itemView.findViewById(R.id.textFechaPublicacion);
                fechaExpiracion = itemView.findViewById(R.id.textFechaExpiracion);
                btnIrNegocio = itemView.findViewById(R.id.btnIrNegocio);
                btnOpenChat = itemView.findViewById(R.id.btnChat);
            }
        }

        private class ValoracionOferta extends RecyclerView.ViewHolder {

            TextView calificacion;
            RatingBar rating;
            TextView vistas;

            public ValoracionOferta(View v) {
                super(v);
                calificacion = itemView.findViewById(R.id.textCalificacionOferta);
                rating = itemView.findViewById(R.id.ratingBarOferta);
                vistas = itemView.findViewById(R.id.textNumVistas);
            }
        }

        private class OfertaInsights extends RecyclerView.ViewHolder {

            public OfertaInsights(View v) {
                super(v);
            }
        }

        private class OfertaDescripcion extends RecyclerView.ViewHolder {
            TextView descripcionOferta;
            TextView tiempoPublicacion;


            public OfertaDescripcion(View v) {
                super(v);
                descripcionOferta = itemView.findViewById(R.id.textDescripcionOferta);
                tiempoPublicacion = itemView.findViewById(R.id.textTiempoPublicacion);
            }
        }

        private class OfertaComentarios extends RecyclerView.ViewHolder {

            TextView numMeGusta;
            TextView comentarios;
            TextView usuario;
            TextView fechaPublicaComentario;

            public OfertaComentarios(View v) {
                super(v);
                numMeGusta = itemView.findViewById(R.id.textNumLike);
                comentarios = itemView.findViewById(R.id.textComentarios);
                usuario = itemView.findViewById(R.id.textUsuario);
                fechaPublicaComentario = itemView.findViewById(R.id.textFechaPublicacionComentario);
            }
        }

    public void setRecyclerViewItems(List<RecyclerViewItem> recyclerViewItems) {
        this.recyclerViewItems = recyclerViewItems;
    }
}
