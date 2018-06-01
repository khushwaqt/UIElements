package com.imran.datemontpicker.toastme;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.imran.datemontpicker.R;

import java.util.List;

/**
 * Created by imran.ali on 9/3/2017.
 */

public class CustomToast {

    public static final int TOAST_DEFAULT = 1;
    public static final int TOAST_ERROR = 2;
    public static final int TOAST_SUCCESS = 3;
    public static final int TOAST_INFO = 4;
    public static final int TOAST_WARNING = 5;

    public static Toast ShowDefault(Context context, String message) {
        Toast mToast = new Toast(context);
        final View toastLayout = ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.customtoast, null);
        TextView messageTextView = toastLayout.findViewById(R.id.toast_text);
        messageTextView.setText(message);
        ImageView toastImageView = toastLayout.findViewById(R.id.toast_image);
        toastImageView.setVisibility(View.GONE);
        mToastView(toastLayout, ContextCompat.getColor(context, R.color.grey));
        mToast.setView(toastLayout);
        mToast.setDuration(Toast.LENGTH_LONG);
        return mToast;
    }


    public static Toast ShowToastError(Context context, String message) {
        Toast mToast = new Toast(context);
        final View toastLayout = ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.customtoast, null);
        TextView messageTextView = toastLayout.findViewById(R.id.toast_text);
        messageTextView.setText(message);
        ImageView toastImageView = toastLayout.findViewById(R.id.toast_image);
        toastImageView.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.error));
        mToastView(toastLayout, ContextCompat.getColor(context, R.color.red));
        mToast.setView(toastLayout);
        mToast.setDuration(Toast.LENGTH_LONG);
        return mToast;
    }

    public static Toast ShowToastSuccess(Context context, String message) {
        Toast mToast = new Toast(context);
        final View toastLayout = ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.customtoast, null);
        TextView messageTextView = toastLayout.findViewById(R.id.toast_text);
        messageTextView.setText(message);
        ImageView toastImageView = toastLayout.findViewById(R.id.toast_image);
        toastImageView.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.success));
        mToastView(toastLayout, ContextCompat.getColor(context, R.color.green));
        mToast.setView(toastLayout);
        mToast.setDuration(Toast.LENGTH_LONG);
        return mToast;
    }

    public static Toast ShowToastInfo(Context context, String message) {
        Toast mToast = new Toast(context);
        final View toastLayout = ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.customtoast, null);
        TextView messageTextView = toastLayout.findViewById(R.id.toast_text);
        messageTextView.setText(message);
        ImageView toastImageView = toastLayout.findViewById(R.id.toast_image);
        toastImageView.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.info));
        mToastView(toastLayout, ContextCompat.getColor(context, R.color.blue));
        mToast.setView(toastLayout);
        mToast.setDuration(Toast.LENGTH_LONG);
        return mToast;
    }

    public static Toast ShowToastWarning(Context context, String message) {
        Toast mToast = new Toast(context);
        final View toastLayout = ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.customtoast, null);
        TextView messageTextView = toastLayout.findViewById(R.id.toast_text);
        messageTextView.setText(message);
        ImageView toastImageView = toastLayout.findViewById(R.id.toast_image);
        toastImageView.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.warning));
        mToastView(toastLayout, ContextCompat.getColor(context, R.color.yellow));
        mToast.setView(toastLayout);
        mToast.setDuration(Toast.LENGTH_LONG);
        return mToast;
    }


    public static Toast mCustomToast(Context context, String message, int textColor,
                                     boolean withIcon, Drawable drawable,
                                     boolean tintDrawable, int drawableTint,
                                     int backgroundColor,
                                     boolean isLong) throws IllegalArgumentException {
        Toast mToast = new Toast(context);
        final View toastLayout = ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.customtoast, null);
        TextView messageTextView = toastLayout.findViewById(R.id.toast_text);
        messageTextView.setText(message);
        messageTextView.setTextColor(textColor);
        ImageView toastImageView = toastLayout.findViewById(R.id.toast_image);
        if (withIcon) {
            if (drawable == null) {
                throw new IllegalArgumentException("With icon 'True',must contain a drawable");
            }
            toastImageView.setImageDrawable(drawable);
        } else {
            toastImageView.setVisibility(View.GONE);
        }
        if (tintDrawable) {
            DrawableCompat.setTint(toastImageView.getDrawable(), drawableTint);
        }
        GradientDrawable shape = new GradientDrawable();
        shape.setShape(GradientDrawable.RECTANGLE);
        shape.setCornerRadius(30);
        shape.setColor(backgroundColor);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            toastLayout.setBackground(shape);
        } else {
            toastLayout.setBackgroundDrawable(shape);
        }

        mToast.setView(toastLayout);
        mToast.setDuration((isLong) ? Toast.LENGTH_LONG : Toast.LENGTH_SHORT);
        return mToast;
    }

    private static void mToastView(View v, int backgroundColor) {
        GradientDrawable shape = new GradientDrawable();
        shape.setShape(GradientDrawable.RECTANGLE);
        shape.setCornerRadius(30);
        shape.setColor(backgroundColor);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            v.setBackground(shape);
        } else {
            v.setBackgroundDrawable(shape);
        }

    }

    public static Toast showToast(Context context, List<ToastParams> params, String message, boolean isLong) throws IllegalArgumentException {
        ToastParams mParams = params.get(0);
        Toast mToast = new Toast(context);
        final View toastLayout = ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.customtoast, null);
        TextView messageTextView = toastLayout.findViewById(R.id.toast_text);
        messageTextView.setText(message);
        messageTextView.setTextColor(mParams.textColor);
        ImageView toastImageView = toastLayout.findViewById(R.id.toast_image);
        if (mParams.withIcon) {
            if (mParams.iconDrawable == null) {
                throw new IllegalArgumentException("With icon 'True',must contain a drawable");
            }
            toastImageView.setImageDrawable(mParams.iconDrawable);
        } else {
            toastImageView.setVisibility(View.GONE);
        }
        if (mParams.tintDrawable) {
            DrawableCompat.setTint(toastImageView.getDrawable(), mParams.tintDrawableColor);
        }
        GradientDrawable shape = new GradientDrawable();
        shape.setShape(GradientDrawable.RECTANGLE);
        shape.setCornerRadius(30);
        shape.setColor(mParams.backGroundColor);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            toastLayout.setBackground(shape);
        } else {
            toastLayout.setBackgroundDrawable(shape);
        }

        mToast.setView(toastLayout);
        mToast.setDuration((isLong) ? Toast.LENGTH_LONG : Toast.LENGTH_SHORT);
        return mToast;
    }


}
