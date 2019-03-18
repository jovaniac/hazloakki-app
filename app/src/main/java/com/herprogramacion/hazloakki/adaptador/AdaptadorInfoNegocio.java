package com.herprogramacion.hazloakki.adaptador;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.herprogramacion.hazloakki.modelo.FoodItem;
import com.herprogramacion.hazloakki.modelo.Footer;
import com.herprogramacion.hazloakki.modelo.NegocioInfoDatosContacto;
import com.herprogramacion.hazloakki.modelo.NegocioInfoDireccionDto;
import com.herprogramacion.hazloakki.modelo.NegocioInfoHeader;
import com.herprogramacion.hazloakki.modelo.NegocioInfoHeaderDatosContacto;
import com.herprogramacion.hazloakki.modelo.NegocioInfoHeaderDireccion;
import com.herprogramacion.hazloakki.modelo.NegocioInfoHeaderServicios;
import com.herprogramacion.hazloakki.modelo.NegocioInfoServicios;
import com.herprogramacion.hazloakki.modelo.RecyclerViewItem;
import com.herprogramacion.hazloakki.R;
import com.herprogramacion.hazloakki.ui.AdaptadorDirecciones;

import java.util.ArrayList;
import java.util.List;


public class AdaptadorInfoNegocio extends RecyclerView.Adapter {
    //Declare List of Recyclerview Items
    List<RecyclerViewItem> recyclerViewItems;
    //Header Item Type
    private static final int HEADER_ITEM = 0;

    //Footer Item Type
    private static final int DIRECCION_NEGOCIO_TITULO_ITEM = 1;

    //Footer Item Type
    private static final int DIRECCION_NEGOCIO_ITEM = 2;
    private static final int DATOS_CONTACTO_TITULO_ITEM = 3;

    private static final int DATOS_CONTACTO_ITEM = 4;

    private static final int DATOS_SERVICIOS_TITULO_ITEM =5;
    private static final int DATOS_SERVICIOS_ITEM = 6;

    //Footer Item Type
    private static final int FOOTER_ITEM = 7;
    ////Food Item Type
    private static final int FOOD_ITEM = 8;




    Context mContext;

    public AdaptadorInfoNegocio(Context mContext) {
        this.mContext = mContext;
    }


  /*  public AdaptadorInfoNegocio(List<RecyclerViewItem> recyclerViewItems, Context mContext) {
        this.recyclerViewItems = recyclerViewItems;
        this.mContext = mContext;
    }
    */

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View row;
        //Check fot view Type inflate layout according to it

        if (viewType == HEADER_ITEM) {
            row = inflater.inflate(R.layout.info_negocio_row_header, parent, false);
            return new HeaderHolder(row);
        } else if (viewType == DIRECCION_NEGOCIO_TITULO_ITEM) {
            row = inflater.inflate(R.layout.info_negocio_direccion_row_header, parent, false);
            return new AdvertisementHolder(row);
        }else if (viewType == DIRECCION_NEGOCIO_ITEM) {
            row = inflater.inflate(R.layout.info_negocio_row_direccion, parent, false);
            return new DireccionHolder(row);
        }else if (viewType == DATOS_CONTACTO_TITULO_ITEM) {
            row = inflater.inflate(R.layout.info_negocio_contacto_row_header, parent, false);
            return new ContactoHeaderHolder(row);
        }else if (viewType == DATOS_CONTACTO_ITEM) {
            row = inflater.inflate(R.layout.info_negocio_row_contacto, parent, false);
            return new ContactoHolder(row);
        }else if (viewType == DATOS_SERVICIOS_TITULO_ITEM) {
            row = inflater.inflate(R.layout.info_negocio_servicios_row_header, parent, false);
            return new ServiciosHeaderHolder(row);
        }else if (viewType == DATOS_SERVICIOS_ITEM) {
            row = inflater.inflate(R.layout.item_lista_direccion, parent, false);
            return new ServiciosHolder(row);
        } else if (viewType == FOOTER_ITEM) {
            row = inflater.inflate(R.layout.custom_row_footer, parent, false);
            return new FooterHolder(row);
        } else if (viewType == FOOD_ITEM) {
            row = inflater.inflate(R.layout.custom_row_food, parent, false);
            return new FoodItemHolder(row);

        }

        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        RecyclerViewItem recyclerViewItem = recyclerViewItems.get(position);
        //Check holder instance to populate data  according to it
        if (holder instanceof HeaderHolder) {
            HeaderHolder headerHolder = (HeaderHolder) holder;
            NegocioInfoHeader header = (NegocioInfoHeader) recyclerViewItem;
            //set data
            headerHolder.infoNombre.setText(header.getNegocioDto().getNombre());
            headerHolder.infoDescripcion.setText(header.getNegocioDto().getDescripcion());
            headerHolder.infoCategoria.setText(header.getNegocioDto().getCategoria());
            Glide.with(mContext).load(header.getImageUrl()).into(headerHolder.imageViewHeader);

        }else if (holder instanceof DireccionHolder) {
            DireccionHolder footerHolder = (DireccionHolder) holder;
            NegocioInfoDireccionDto negocioDireccion = (NegocioInfoDireccionDto) recyclerViewItem;
            //set data
            footerHolder.infoDireccion.setText(negocioDireccion.getDireccion());
            footerHolder.infoDistancia.setText(negocioDireccion.getDistancia());
            footerHolder.infoColonia.setText(negocioDireccion.getColonia());
            footerHolder.infoHorario.setText(negocioDireccion.getHorario());
           // Glide.with(mContext).load(footer.getImageUrl()).into(footerHolder.imageViewHeader);

        } else if (holder instanceof FooterHolder) {
            FooterHolder footerHolder = (FooterHolder) holder;
            Footer footer = (Footer) recyclerViewItem;
            //set data
            footerHolder.texViewQuote.setText(footer.getQuote());
            footerHolder.textViewAuthor.setText(footer.getAuthor());
            Glide.with(mContext).load(footer.getImageUrl()).into(footerHolder.imageViewFooter);
        }else if (holder instanceof ContactoHolder) {
            ContactoHolder footerHolder = (ContactoHolder) holder;
        NegocioInfoDatosContacto datosContacto = (NegocioInfoDatosContacto) recyclerViewItem;
        //set data
        footerHolder.infoMail.setText(datosContacto.getMail());
        footerHolder.infoSitioWeb.setText(datosContacto.getSitioWeb());
        footerHolder.infoTelefonoLocal.setText(datosContacto.getTelefonoCelular());
        footerHolder.infoTelefonoMovil1.setText(datosContacto.getTelefonoCel1());
        footerHolder.infoTelefonoMovil2.setText(datosContacto.getTelefonoCel2());
        footerHolder.infoResponsable.setText(datosContacto.getResponsable());

        // Glide.with(mContext).load(footer.getImageUrl()).into(footerHolder.imageViewHeader);

        } else if(holder instanceof ServiciosHolder){

            ServiciosHolder serviciosHolder = (ServiciosHolder) holder;
            NegocioInfoServicios negocioInfoServicios = (NegocioInfoServicios) recyclerViewItem;

            serviciosHolder.infoNombre.setText(negocioInfoServicios.getNombre());
            serviciosHolder.infoDescripcion.setText(negocioInfoServicios.getDescripcion());
        }

        /*
        } else if (holder instanceof FoodItemHolder) {
            FoodItemHolder foodItemHolder = (FoodItemHolder) holder;
            FoodItem foodItem = (FoodItem) recyclerViewItem;
            //set data
            foodItemHolder.texViewFoodTitle.setText(foodItem.getTitle());
            foodItemHolder.texViewDescription.setText(foodItem.getDescription());
            foodItemHolder.textViewPrice.setText(foodItem.getPrice());
            Glide.with(mContext).load(foodItem.getImageUrl()).into(foodItemHolder.imageViewFood);
            //check food item is hot or not to set visibility of hot text on image
            if (foodItem.isHot())
                foodItemHolder.textViewIsHot.setVisibility(View.VISIBLE);
            else
                foodItemHolder.textViewIsHot.setVisibility(View.GONE);

        }*/

    }

    @Override
    public int getItemViewType(int position) {
        //here we can set view type
        RecyclerViewItem recyclerViewItem = recyclerViewItems.get(position);
        //if its header then return header item
        if (recyclerViewItem instanceof NegocioInfoHeader)
            return HEADER_ITEM;
        else if(recyclerViewItem instanceof NegocioInfoHeaderDireccion)
            return DIRECCION_NEGOCIO_TITULO_ITEM;
        else if(recyclerViewItem instanceof NegocioInfoDireccionDto)
            return DIRECCION_NEGOCIO_ITEM;
        else if(recyclerViewItem instanceof NegocioInfoHeaderDatosContacto)
            return DATOS_CONTACTO_TITULO_ITEM;
        else if(recyclerViewItem instanceof NegocioInfoDatosContacto)
            return DATOS_CONTACTO_ITEM;
        else if(recyclerViewItem instanceof NegocioInfoHeaderServicios)
            return DATOS_SERVICIOS_TITULO_ITEM;
        else if(recyclerViewItem instanceof NegocioInfoServicios)
            return DATOS_SERVICIOS_ITEM;
        else if (recyclerViewItem instanceof Footer)
            return FOOTER_ITEM;
        //if its FoodItem then return Food item
        else if (recyclerViewItem instanceof FoodItem)
            return FOOD_ITEM;

        else
            return super.getItemViewType(position);

    }

    @Override
    public int getItemCount() {
        return recyclerViewItems.size();
    }
    //Food item holder
    private class FoodItemHolder extends RecyclerView.ViewHolder {
        TextView texViewFoodTitle, texViewDescription, textViewPrice, textViewIsHot;
        ImageView imageViewFood;

        FoodItemHolder(View itemView) {
            super(itemView);
            texViewFoodTitle = itemView.findViewById(R.id.texViewFoodTitle);
            texViewDescription = itemView.findViewById(R.id.texViewDescription);
            imageViewFood = itemView.findViewById(R.id.imageViewFood);
            textViewPrice = itemView.findViewById(R.id.textViewPrice);
            textViewIsHot = itemView.findViewById(R.id.textViewIsHot);
        }
    }
    //header holder
    private class HeaderHolder extends RecyclerView.ViewHolder {
        TextView infoNombre;
        TextView infoDescripcion;
        TextView infoCategoria;
        ImageView imageViewHeader;

        HeaderHolder(View itemView) {
            super(itemView);
            infoNombre = itemView.findViewById(R.id.infoNombre);
            infoDescripcion = itemView.findViewById(R.id.infoDescripcion);
            infoCategoria = itemView.findViewById(R.id.infoCategoria);
            imageViewHeader = itemView.findViewById(R.id.imageViewHeader);
        }
    }

    //direccion< holder
    private class DireccionHolder extends RecyclerView.ViewHolder {

        public ImageView imageViewHeader;
        public TextView infoDireccion;
        public TextView infoDistancia;
        public TextView infoHorario;
        public TextView infoColonia;

        public DireccionHolder(View view) {
            super(view);
            infoDireccion = itemView.findViewById(R.id.input_direccion);
            infoDistancia = itemView.findViewById(R.id.input_distancia);
            infoHorario = itemView.findViewById(R.id.input_horario);
            infoColonia = itemView.findViewById(R.id.input_colonia);
        }
    }

    //footer holder
    private class FooterHolder extends RecyclerView.ViewHolder {
        TextView texViewQuote, textViewAuthor;
        ImageView imageViewFooter;

        FooterHolder(View itemView) {
            super(itemView);
            texViewQuote = itemView.findViewById(R.id.texViewQuote);
            textViewAuthor = itemView.findViewById(R.id.textViewAuthor);
            imageViewFooter = itemView.findViewById(R.id.imageViewFooter);
        }
    }

    private class AdvertisementHolder extends RecyclerView.ViewHolder{
        public ImageView imageViewAdvertisementBanner;
        public TextView textViewAdvertMessage;

        public AdvertisementHolder(View view){
            super(view);
            //imageViewAdvertisementBanner = (ImageView)view.findViewById(R.id.imageViewAdvertisementBanner);
            textViewAdvertMessage = (TextView)view.findViewById(R.id.textViewAdvertMessage);
        }
    }

    private class ContactoHeaderHolder extends RecyclerView.ViewHolder{
        public ImageView imageViewAdvertisementBanner;
        public TextView textViewAdvertMessage;

        public ContactoHeaderHolder(View view){
            super(view);
            //imageViewAdvertisementBanner = (ImageView)view.findViewById(R.id.imageViewAdvertisementBanner);
            textViewAdvertMessage = (TextView)view.findViewById(R.id.datosContacto);
        }
    }

    private class ContactoHolder extends RecyclerView.ViewHolder{
        public ImageView imageViewHeader;
        public TextView infoSitioWeb;
        public TextView infoMail;
        public TextView infoTelefonoLocal;
        public TextView infoTelefonoMovil1;
        public TextView infoTelefonoMovil2;
        public TextView infoResponsable;


        public ContactoHolder(View view){
            super(view);
            infoSitioWeb = itemView.findViewById(R.id.input_sitioWeb);
            infoMail = itemView.findViewById(R.id.input_correo);
            infoTelefonoLocal = itemView.findViewById(R.id.input_telefonoLocal);
            infoTelefonoMovil1 = itemView.findViewById(R.id.input_telefonoCelular1);
            infoTelefonoMovil2 = itemView.findViewById(R.id.input_telefonoCelular2);
            infoResponsable = itemView.findViewById(R.id.input_responsable);
        }
    }

    private class ServiciosHeaderHolder extends RecyclerView.ViewHolder{
        public ImageView imageViewAdvertisementBanner;
        public TextView textViewAdvertMessage;

        public ServiciosHeaderHolder(View view){
            super(view);
            //imageViewAdvertisementBanner = (ImageView)view.findViewById(R.id.imageViewAdvertisementBanner);
            textViewAdvertMessage = (TextView)view.findViewById(R.id.datosServicios);
        }
    }

/*    private class ServiciosHolder extends RecyclerView.ViewHolder{
        public ImageView imageViewAdvertisementBanner;
        public TextView textViewAdvertMessage;

        public ServiciosHolder(View view){
            super(view);
            //imageViewAdvertisementBanner = (ImageView)view.findViewById(R.id.imageViewAdvertisementBanner);
            textViewAdvertMessage = (TextView)view.findViewById(R.id.input_direccion2);
        }
    }*/

    private class ServiciosHolder extends RecyclerView.ViewHolder {
        // Campos respectivos de un item
        public TextView infoNombre;
        public TextView infoDescripcion;

        public ServiciosHolder(View v) {
            super(v);
            infoNombre = (TextView) v.findViewById(R.id.info_servicio);
            infoDescripcion = (TextView) v.findViewById(R.id.info_descripcion);

        }
    }

    public List<RecyclerViewItem> getRecyclerViewItems() {
        return recyclerViewItems;
    }

    public void setRecyclerViewItems(List<RecyclerViewItem> recyclerViewItems) {
        this.recyclerViewItems = recyclerViewItems;
    }


}



