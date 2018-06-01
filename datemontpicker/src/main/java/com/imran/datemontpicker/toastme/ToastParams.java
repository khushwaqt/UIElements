package com.imran.datemontpicker.toastme;

import android.graphics.drawable.Drawable;

/**
 * Created by imran.ali on 12/8/2017.
 */

public class ToastParams {
    int textColor;
    boolean withIcon;
    Drawable iconDrawable;
    boolean tintDrawable;
    int tintDrawableColor;
    int backGroundColor;

    public ToastParams(int textColor, boolean withIcon, Drawable iconDrawable, boolean tintDrawable, int tintDrawableColor, int backGroundColor) {
        this.textColor = textColor;
        this.withIcon = withIcon;
        this.iconDrawable = iconDrawable;
        this.tintDrawable = tintDrawable;
        this.tintDrawableColor = tintDrawableColor;
        this.backGroundColor = backGroundColor;
    }
}
