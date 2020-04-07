package com.sunofbeaches.taobaounion.ui.custom;

import android.content.Context;
import android.graphics.ColorMatrix;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class GreyContainer extends FrameLayout {
    public GreyContainer(@NonNull Context context) {
        this(context,null);
    }

    public GreyContainer(@NonNull Context context,@Nullable AttributeSet attrs) {
        this(context,attrs,0);
    }

    public GreyContainer(@NonNull Context context,@Nullable AttributeSet attrs,int defStyleAttr) {
        super(context,attrs,defStyleAttr);
        ColorMatrix cm = new ColorMatrix();
        cm.setSaturation(0);
    }


}
