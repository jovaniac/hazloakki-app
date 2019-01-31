package com.herprogramacion.hazloakki.adaptador;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.herprogramacion.hazloakki.R;
import com.herprogramacion.hazloakki.modelo.NegocioDto;

import java.util.List;


public class AdaptadorNegocio extends RecyclerView.Adapter<AdaptadorNegocio.ViewHolder> {
    private final Context contexto;
    private List<NegocioDto> listaNegocios;
    private OnItemClickListener escucha;


    public interface OnItemClickListener {
        public void onClick(ViewHolder holder, String idAlquiler);
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
            escucha.onClick(this, obtenerIdAlquiler(getAdapterPosition()));
        }
    }

    private String obtenerIdAlquiler(int posicion) {
        if (listaNegocios != null) {
            //if (items.moveToPosition(posicion)) {
            if (!listaNegocios.isEmpty()) {
                return listaNegocios.get(posicion).toString();
            }
        }

        return null;
    }

    public AdaptadorNegocio(Context contexto, OnItemClickListener escucha) {
        this.contexto = contexto;
        this.escucha = escucha;

    }

    public AdaptadorNegocio(Context contexto, List<NegocioDto> listaNegocios) {
        this.contexto = contexto;
        this.listaNegocios = listaNegocios;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_lista_alquiler, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //items.moveToPosition(position);

        String s;

        // Asignación UI
        s = listaNegocios.get(position).getNombre();
        holder.viewNombreNegocio.setText(s);

        s = String.valueOf(listaNegocios.get(position).getCalificacion());
        holder.viewCalificacion.setText(s);

        s = String.valueOf(listaNegocios.get(position).getCategoria());
        holder.viewCategoria.setText(s);

        s =String.valueOf(listaNegocios.get(position).getDistancia());
        holder.viewDistancia.setText(s);

        s =String.valueOf(listaNegocios.get(position).getHorario());
        holder.viewHorario.setText(s);

        s =String.valueOf(listaNegocios.get(position).getDomicilio());
        holder.viewDireccion.setText(s);

        s =String.valueOf(listaNegocios.get(position).getNumeroOfertas());
        holder.viewNumOfertas.setText(s);

        s =String.valueOf(listaNegocios.get(position).getDescripcion());
        holder.viewDescripcion.setText(s);

       // s = items.getString(ConsultaAlquileres.URL);
        //Glide.with(contexto).load(s).centerCrop().into(holder.viewFoto);


    }

    @Override
    public int getItemCount() {
        if (!listaNegocios.isEmpty())
            return listaNegocios.size();
        return 0;
    }

}