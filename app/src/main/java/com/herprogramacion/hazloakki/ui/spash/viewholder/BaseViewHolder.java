package com.herprogramacion.hazloakki.ui.spash.viewholder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.herprogramacion.hazloakki.ui.spash.listener.FormItemEditTextListener;
import com.herprogramacion.hazloakki.ui.spash.model.BaseFormElement;

/**
 * Base ViewHolder for all other viewholders
 * Created by Riddhi - Rudra on 30-Jul-17.
 */

public class BaseViewHolder extends RecyclerView.ViewHolder implements BaseViewHolderInterface {

    public BaseViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public FormItemEditTextListener getListener() {
        return null;
    }


    public void bind(int position, BaseFormElement formElement, Context context) {

    }

}
