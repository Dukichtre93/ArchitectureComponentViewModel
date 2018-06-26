package com.thanhviet.userlistarchvm.adapter;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

public class BindingUtils {
    @BindingAdapter("imageId")
    public static void setImageId(ImageView imageView, int idImage) {
        if (idImage == 0) return;
        imageView.setImageResource(idImage);
    }
}
