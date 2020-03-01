package com.sunofbeaches.taobaounion.ui.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.sunofbeaches.taobaounion.utils.LogUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;

public class TbNestedScrollView extends NestedScrollView {
    public TbNestedScrollView(@NonNull Context context) {
        super(context);
    }

    public TbNestedScrollView(@NonNull Context context,@Nullable AttributeSet attrs) {
        super(context,attrs);
    }

    public TbNestedScrollView(@NonNull Context context,@Nullable AttributeSet attrs,int defStyleAttr) {
        super(context,attrs,defStyleAttr);
    }


    @Override
    public void onNestedScroll(@NonNull View target,int dxConsumed,int dyConsumed,
                               int dxUnconsumed,int dyUnconsumed,int type,@NonNull int[] consumed) {
        super.onNestedScroll(target,dxConsumed,dyConsumed,dxUnconsumed,dyUnconsumed,type,consumed);
        LogUtils.d(this,"onNestedScroll...");

    }
}
