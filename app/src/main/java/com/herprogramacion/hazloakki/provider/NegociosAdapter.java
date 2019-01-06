package com.herprogramacion.hazloakki.provider;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.herprogramacion.hazloakki.R;

import com.herprogramacion.hazloakki.modelo.NegocioDto;

import java.util.ArrayList;
import java.util.List;

/**
 * Adaptador con un cursor para poblar la lista de alquileres desde SQLite
 */
public class NegociosAdapter extends RecyclerView.Adapter<NegociosAdapter.ViewHolder> {
    private final Context contexto;
    private Cursor items;
    private List<NegocioDto> listNegocio=new ArrayList<NegocioDto>();
    private OnItemClickListener escucha;
    private String TAG = NegociosAdapter.class.getSimpleName();

    public NegociosAdapter(Context contexto, List<NegocioDto>  listNegocio){

        Log.d(TAG, "Datos NegociosAdapter: "+listNegocio.toString());

        this.contexto = contexto;
        this.listNegocio = listNegocio;

    }


    public NegociosAdapter(Context contexto, OnItemClickListener escucha) {
        this.contexto = contexto;
        this.escucha = escucha;

    }

    interface OnItemClickListener {
        public void onClick(ViewHolder holder, String idAlquiler);
    }

    public class ViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {
        // Referencias UI
        public TextView viewNombre;
        public TextView viewUbicacion;
        public TextView viewDescripcion;
        public TextView viewClasificacion;
        public ImageView viewFoto;

        public ViewHolder(View v) {
            super(v);
            viewNombre = (TextView) v.findViewById(R.id.nombre);
            viewUbicacion = (TextView) v.findViewById(R.id.ubicacion);
            viewDescripcion = (TextView) v.findViewById(R.id.descripcion);
            viewClasificacion = (TextView) v.findViewById(R.id.clasificacion);
            viewFoto = (ImageView) v.findViewById(R.id.foto);
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            escucha.onClick(this, obtenerIdAlquiler(getAdapterPosition()));
        }
    }

    private String obtenerIdAlquiler(int posicion) {
        if (items != null) {
            if (items.moveToPosition(posicion)) {
                return items.getString(ConsultaAlquileres.ID_ALQUILER);
            }
        }

        return null;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_lista_alquiler, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        items.moveToPosition(position);

        String s;

        // Asignación UI
        s = listNegocio.get(position).getNombre();
        holder.viewNombre.setText(s);

     /*   s = items.getString(ConsultaAlquileres.UBICACION);
        holder.viewUbicacion.setText(s);

        s = items.getString(ConsultaAlquileres.DESCRIPCION);
        holder.viewDescripcion.setText(s);

        s = items.getString(ConsultaAlquileres.PRECIO);
        holder.viewClasificacion.setText(s);

        s = items.getString(ConsultaAlquileres.URL);
        Glide.with(contexto).load(s).centerCrop().into(holder.viewFoto);
*/

    }

    @Override
    public int getItemCount() {
        if (items != null)
            return items.getCount();
        return 0;
    }

    public void swapCursor(Cursor nuevoCursor) {
        if (nuevoCursor != null) {
            items = nuevoCursor;
            notifyDataSetChanged();
        }
    }

    public Cursor getCursor() {
        return items;
    }

    interface ConsultaAlquileres {
        int ID_ALQUILER = 1;
        int NOMBRE = 2;
        int UBICACION = 3;
        int DESCRIPCION = 4;
        int PRECIO = 5;
        int URL = 6;
    }
}
