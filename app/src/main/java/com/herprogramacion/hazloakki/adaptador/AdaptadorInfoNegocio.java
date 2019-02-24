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
import com.herprogramacion.hazloakki.modelo.NegocioInfoDireccionDto;
import com.herprogramacion.hazloakki.modelo.NegocioInfoHeader;
import com.herprogramacion.hazloakki.modelo.RecyclerViewItem;
import com.herprogramacion.hazloakki.R;

import java.util.List;


public class AdaptadorInfoNegocio extends RecyclerView.Adapter {
    //Declare List of Recyclerview Items
    List<RecyclerViewItem> recyclerViewItems;
    //Header Item Type
    private static final int HEADER_ITEM = 0;

    //Footer Item Type
    private static final int DIRECCION_NEGOCIO_ITEM = 1;

    //Footer Item Type
    private static final int FOOTER_ITEM = 2;
    ////Food Item Type
    private static final int FOOD_ITEM = 3;
    Context mContext;

    public AdaptadorInfoNegocio(List<RecyclerViewItem> recyclerViewItems, Context mContext) {
        this.recyclerViewItems = recyclerViewItems;
        this.mContext = mContext;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View row;
        //Check fot view Type inflate layout according to it
        if (viewType == HEADER_ITEM) {
            row = inflater.inflate(R.layout.info_negocio_row_header, parent, false);
            return new HeaderHolder(row);
        }else if (viewType == DIRECCION_NEGOCIO_ITEM) {
            row = inflater.inflate(R.layout.info_negocio_row_direccion, parent, false);
            return new DireccionHolder(row);
        }else if (viewType == FOOTER_ITEM) {
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
            NegocioInfoDireccionDto footer = (NegocioInfoDireccionDto) recyclerViewItem;
            //set data
            footerHolder.infoDireccion.setText(footer.getDireccion());
            footerHolder.infoDistancia.setText(footer.getDistancia());
            footerHolder.infoNumeroOfertasPublicadas.setText(footer.getNumeroOfertasPublicadas());
            footerHolder.infoColonia.setText(footer.getColonia());
            Glide.with(mContext).load(footer.getImageUrl()).into(footerHolder.imageViewHeader);

        }
        else if (holder instanceof FooterHolder) {
            FooterHolder footerHolder = (FooterHolder) holder;
            Footer footer = (Footer) recyclerViewItem;
            //set data
            footerHolder.texViewQuote.setText(footer.getQuote());
            footerHolder.textViewAuthor.setText(footer.getAuthor());
            Glide.with(mContext).load(footer.getImageUrl()).into(footerHolder.imageViewFooter);

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

        }

    }

    @Override
    public int getItemViewType(int position) {
        //here we can set view type
        RecyclerViewItem recyclerViewItem = recyclerViewItems.get(position);
        //if its header then return header item
        if (recyclerViewItem instanceof NegocioInfoHeader)
            return HEADER_ITEM;
            //if its Footer then return Footer item
        else if (recyclerViewItem instanceof Footer)
            return FOOTER_ITEM;
        //if its FoodItem then return Food item
        else if (recyclerViewItem instanceof FoodItem)
            return FOOD_ITEM;
        else if(recyclerViewItem instanceof NegocioInfoDireccionDto)
            return DIRECCION_NEGOCIO_ITEM;
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

        private ImageView imageViewHeader;
        private TextView infoDireccion;
        private TextView infoDistancia;
        private TextView infHorario;
        private TextView infoNumeroOfertasPublicadas;
        private TextView infoColonia;

        DireccionHolder(View itemView) {
            super(itemView);
            infoDireccion = itemView.findViewById(R.id.infoDireccion);
            infoDistancia = itemView.findViewById(R.id.infoDistancia);
            infHorario = itemView.findViewById(R.id.infoHorario);
            infoNumeroOfertasPublicadas = itemView.findViewById(R.id.infoNumeroOfertasPublicadas);
            infoColonia = itemView.findViewById(R.id.infoColonia);
            imageViewHeader = itemView.findViewById(R.id.imageViewHeader);
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
}