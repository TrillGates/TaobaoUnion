package com.sunofbeaches.taobaounion.ui.custom;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;

import com.sunofbeaches.taobaounion.R;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;

public class LoadingView extends AppCompatImageView {
    private float mDegrees = 0;
    private boolean mNeedRotate = true;

    public LoadingView(Context context) {
        this(context,null);
    }

    public LoadingView(Context context,@Nullable AttributeSet attrs) {
        this(context,attrs,0);
    }

    public LoadingView(Context context,@Nullable AttributeSet attrs,int defStyleAttr) {
        super(context,attrs,defStyleAttr);
        setImageResource(R.mipmap.loading);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        mNeedRotate = true;
        //LogUtils.d(this,"onAttachedToWindow...");
        startRotate();
    }

    private void startRotate() {
        post(new Runnable() {
            @Override
            public void run() {
                mDegrees += 10;
                if(mDegrees >= 360) {
                    mDegrees = 0;
                }
                invalidate();
                // LogUtils.d(LoadingView.this,"loading....");
                //判断是否要继续旋转
                //如果不可见，或者已经DetachedFromWindow就不再转动了
                if(getVisibility() != VISIBLE && !mNeedRotate) {
                    removeCallbacks(this);
                } else {
                    postDelayed(this,10);
                }
            }
        });
    }


    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        //LogUtils.d(this,"onDetachedFromWindow...");
        stopRotate();
    }

    private void stopRotate() {
        mNeedRotate = false;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.rotate(mDegrees,getWidth() / 2,getHeight() / 2);
        super.onDraw(canvas);
    }
}
