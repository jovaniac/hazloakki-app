package com.herprogramacion.hazloakki.ui.spash.viewholder;

import android.content.Context;

import com.herprogramacion.hazloakki.ui.spash.listener.FormItemEditTextListener;
import com.herprogramacion.hazloakki.ui.spash.model.BaseFormElement;

/**
 * Base ViewHolder method instance
 * Created by Riddhi - Rudra on 30-Jul-17.
 */

public interface BaseViewHolderInterface {
    FormItemEditTextListener getListener();
    void bind(int position, BaseFormElement formElement, Context context);
}
