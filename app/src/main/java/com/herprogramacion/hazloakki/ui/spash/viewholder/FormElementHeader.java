package com.herprogramacion.hazloakki.ui.spash.viewholder;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import com.herprogramacion.hazloakki.ui.spash.model.BaseFormElement;
import com.herprogramacion.hazloakki.R;


/**
 * ViewHolder for Header
 * Created by Riddhi - Rudra on 30-Jul-17.
 */

public class FormElementHeader extends BaseViewHolder {

    public AppCompatTextView mTextViewTitle;

    public FormElementHeader(View v) {
        super(v);
        mTextViewTitle = (AppCompatTextView) v.findViewById(R.id.formElementTitle);
    }

    public void bind(int position, BaseFormElement formElement, final Context context) {
        mTextViewTitle.setText(formElement.getTitle());
    }

}
