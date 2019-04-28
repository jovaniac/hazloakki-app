package com.herprogramacion.hazloakki.adaptador;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.herprogramacion.hazloakki.R;
import com.herprogramacion.hazloakki.modelo.OfertasDto;

import java.util.List;

public class AdaptadorOfertas2 extends RecyclerView.Adapter<AdaptadorOfertas2.ViewHolder> {
    private final Context contexto;
    private List<OfertasDto> listaOfertas;
    private AdaptadorOfertas2.OnItemClickListener escucha;


    public interface OnItemClickListener {
        public void onClick(AdaptadorOfertas2.ViewHolder holder, String idAlquiler);
    }

    public class ViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {
        // Referencias UI
        public TextView viewNombreNegocio;
        public TextView viewCalificacion;
        public TextView viewCategoria;
        public TextView viewDistancia;
        public TextView viewHorario;
        public TextView viewDireccion;
        public TextView viewNumOfertas;
        public TextView viewUltimoComentario;
        public TextView viewDescripcion;


        public ViewHolder(View v) {
            super(v);
            viewNombreNegocio = (TextView) v.findViewById(R.id.nombreNegocio);
            viewCalificacion = (TextView) v.findViewById(R.id.calificacion);
            viewCategoria = (TextView) v.findViewById(R.id.categoria);
            viewDistancia = (TextView) v.findViewById(R.id.distancia);
            viewHorario = (TextView) v.findViewById(R.id.horario);
            viewDireccion = (TextView) v.findViewById(R.id.direccion);
            viewNumOfertas = (TextView) v.findViewById(R.id.ofertasNegocio);
            viewDescripcion = (TextView) v.findViewById(R.id.descripcionNegocio);

            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            escucha.onClick(this, obtenerNegocioId(getAdapterPosition()));
        }
    }

    private String obtenerNegocioId(int posicion) {
        if (listaOfertas != null) {
            //if (items.moveToPosition(posicion)) {
            if (!listaOfertas.isEmpty()) {
                return listaOfertas.get(posicion).getId();
            }
        }

        return null;
    }

    public AdaptadorOfertas2(Context contexto, AdaptadorOfertas2.OnItemClickListener escucha) {
        this.contexto = contexto;
        this.escucha = escucha;

    }


    @Override
    public AdaptadorOfertas2.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_lista_alquiler, parent, false);
        return new AdaptadorOfertas2.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(AdaptadorOfertas2.ViewHolder holder, int position) {
        //items.moveToPosition(position);

        String s;

        // Asignación UI
        s = listaOfertas.get(position).getId();
        holder.viewNombreNegocio.setText(s);


        // s = items.getString(ConsultaAlquileres.URL);
        //Glide.with(contexto).load(s).centerCrop().into(holder.viewFoto);


    }

    @Override
    public int getItemCount() {
        if (!listaOfertas.isEmpty())
            return listaOfertas.size();
        return 0;
    }

    public List<OfertasDto> getListaOfertas() {
        return listaOfertas;
    }

    public void setListaOfertas(List<OfertasDto> listaOfertas) {
        this.listaOfertas = listaOfertas;
    }
}