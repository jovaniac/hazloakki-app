package com.herprogramacion.hazloakki.adaptador;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.herprogramacion.hazloakki.R;
import com.herprogramacion.hazloakki.modelo.NegocioDto;
import com.herprogramacion.hazloakki.modelo.OfertasDto;

import java.util.List;

public class AdaptadorOfertas extends RecyclerView.Adapter<AdaptadorOfertas.ViewHolder> {
    private final Context contexto;
    private List<OfertasDto> listaOfertas;
    private AdaptadorOfertas.OnItemClickListener escucha;


    public interface OnItemClickListener {
        public void onClick(AdaptadorOfertas.ViewHolder holder, String idAlquiler);
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
                return listaOfertas.get(posicion).getIdOferta();
            }
        }

        return null;
    }

    public AdaptadorOfertas(Context contexto, AdaptadorOfertas.OnItemClickListener escucha) {
        this.contexto = contexto;
        this.escucha = escucha;

    }


    @Override
    public AdaptadorOfertas.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_lista_alquiler, parent, false);
        return new AdaptadorOfertas.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(AdaptadorOfertas.ViewHolder holder, int position) {
        //items.moveToPosition(position);

        String s;

        // Asignación UI
        s = listaOfertas.get(position).getIdOferta();
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
