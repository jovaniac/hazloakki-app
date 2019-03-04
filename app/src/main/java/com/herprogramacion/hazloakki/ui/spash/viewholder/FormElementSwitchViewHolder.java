package com.herprogramacion.hazloakki.ui.spash.viewholder;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.widget.CompoundButton;

import com.herprogramacion.hazloakki.ui.spash.listener.ReloadListener;
import com.herprogramacion.hazloakki.ui.spash.model.BaseFormElement;
import com.herprogramacion.hazloakki.ui.spash.model.FormElementSwitch;

import com.herprogramacion.hazloakki.R;
/**
 * Created by Riddhi - Rudra on 30-Jul-17.
 */

public class FormElementSwitchViewHolder extends BaseViewHolder {

    public AppCompatTextView mTextViewTitle, mTextViewPositive, mTextViewNegative;
    public SwitchCompat mSwitch;
    private ReloadListener mReloadListener;
    private BaseFormElement mFormElement;
    private FormElementSwitch mFormElementSwitch;
    private int mPosition;

    public FormElementSwitchViewHolder(View v, Context context, ReloadListener reloadListener) {
        super(v);
        mTextViewTitle = (AppCompatTextView) v.findViewById(R.id.formElementTitle);
        mTextViewPositive = (AppCompatTextView) v.findViewById(R.id.formElementPositiveText);
        mTextViewNegative = (AppCompatTextView) v.findViewById(R.id.formElementNegativeText);
        mSwitch = (SwitchCompat) v.findViewById(R.id.formElementSwitch);
        mReloadListener = reloadListener;
    }

    @Override
    public void bind(final int position, BaseFormElement formElement, final Context context) {
        mFormElement = formElement;
        mPosition = position;
        mFormElementSwitch = (FormElementSwitch) mFormElement;

        mTextViewTitle.setText(mFormElementSwitch.getTitle());
        mTextViewPositive.setText(mFormElementSwitch.getPositiveText());
        mTextViewNegative.setHint(mFormElementSwitch.getNegativeText());
        mSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                mReloadListener.updateValue(position, b ? mFormElementSwitch.getPositiveText() : mFormElementSwitch.getNegativeText());
            }
        });
    }

}
