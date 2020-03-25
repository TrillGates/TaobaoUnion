package com.sunofbeaches.taobaounion.ui.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sunofbeaches.taobaounion.R;
import com.sunofbeaches.taobaounion.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

public class TextFlowLayout extends ViewGroup {

    public static final float DEFAULT_SPACE = 10;
    private float mItemHorizontalSpace = DEFAULT_SPACE;
    private float mItemVerticalSpace = DEFAULT_SPACE;


    public float getItemHorizontalSpace() {
        return mItemHorizontalSpace;
    }

    public void setItemHorizontalSpace(float itemHorizontalSpace) {
        mItemHorizontalSpace = itemHorizontalSpace;
    }

    public float getItemVerticalSpace() {
        return mItemVerticalSpace;
    }

    public void setItemVerticalSpace(float itemVerticalSpace) {
        mItemVerticalSpace = itemVerticalSpace;
    }

    private List<String> mTextList = new ArrayList<>();

    public TextFlowLayout(Context context) {
        this(context,null);
    }

    public TextFlowLayout(Context context,AttributeSet attrs) {
        this(context,attrs,0);
    }

    public TextFlowLayout(Context context,AttributeSet attrs,int defStyleAttr) {
        super(context,attrs,defStyleAttr);
        //去拿到相关属性
        TypedArray ta = context.obtainStyledAttributes(attrs,R.styleable.FlowTextStyle);
        mItemHorizontalSpace = ta.getDimension(R.styleable.FlowTextStyle_horizontalSpace,DEFAULT_SPACE);
        mItemVerticalSpace = ta.getDimension(R.styleable.FlowTextStyle_verticalSpace,DEFAULT_SPACE);
        ta.recycle();
        LogUtils.d(this,"mItemHorizontalSpace == > " + mItemHorizontalSpace);
        LogUtils.d(this,"mItemVerticalSpace == > " + mItemVerticalSpace);
    }

    public void setTextList(List<String> textList) {
        this.mTextList = textList;
        //遍历内容
        for(String text : mTextList) {
            //添加子view
            //LayoutInflater.from(getContext()).inflate(R.layout.flow_text_view,this,true);
            // 等价于
            TextView item = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.flow_text_view,this,false);
            item.setText(text);
            addView(item);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec,int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec,heightMeasureSpec);
        //测量
        LogUtils.d(this,"onMeasure -- > " + getChildCount());
    }


    @Override
    protected void onLayout(boolean changed,int l,int t,int r,int b) {
        //摆放孩子
        LogUtils.d(this,"onLayout -- > " + getChildCount());
    }
}
