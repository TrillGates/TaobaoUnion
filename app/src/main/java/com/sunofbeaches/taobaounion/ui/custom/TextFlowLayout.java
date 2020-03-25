package com.sunofbeaches.taobaounion.ui.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
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
    private int mSelfWidth;


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

    //这个是描述单行
    private List<View> line = null;
    //这个是描述所有的行
    private List<List<View>> lines = new ArrayList<>();

    @Override
    protected void onMeasure(int widthMeasureSpec,int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec,heightMeasureSpec);
        mSelfWidth = MeasureSpec.getSize(widthMeasureSpec) - getPaddingLeft() - getPaddingRight();
        LogUtils.d(this,"mSelfWidth == > " + mSelfWidth);
        //测量
        LogUtils.d(this,"onMeasure -- > " + getChildCount());
        //测量孩子
        int childCount = getChildCount();
        for(int i = 0; i < childCount; i++) {
            View itemView = getChildAt(i);
            //测量前
            LogUtils.d(this,"before height -- > " + itemView.getMeasuredHeight());
            measureChild(itemView,widthMeasureSpec,heightMeasureSpec);
            //测量后
            LogUtils.d(this,"after height -- > " + itemView.getMeasuredHeight());
            if(line == null) {
                //说明当前行为空，可以添加进来
                createNewLine(itemView);
            } else {
                //判断是否可以再继续添加
                if(canBeAdd(itemView,line)) {
                    //可以添加，添加去
                    line.add(itemView);
                } else {
                    //新创建一行
                    createNewLine(itemView);
                }
            }
        }
        int selfHeight = (int) (lines.size() * getChildAt(0).getMeasuredHeight() + mItemVerticalSpace * (lines.size() + 1) + 0.5f);
        //测量自己
        setMeasuredDimension(mSelfWidth,selfHeight);
    }

    private void createNewLine(View itemView) {
        line = new ArrayList<>();
        line.add(itemView);
        lines.add(line);
    }

    /**
     * 判断当前行是否可以再继续添加新数据
     *
     * @param itemView
     * @param line
     */
    private boolean canBeAdd(View itemView,List<View> line) {
        //所有已经添加的子view宽度相加+(line.size()+1)*mItemHorizontalSpace + itemView.getMeasuredWidth()
        //条件：如果小于/等于当前控件的宽度，则可以添加，否则不能添加
        int totalWith = itemView.getMeasuredWidth();
        for(View view : line) {
            //叠加所有已经添加控件的宽度
            totalWith += view.getMeasuredWidth();
        }
        //水平间距的宽度
        totalWith += mItemHorizontalSpace * (line.size() + 1);
        LogUtils.d(this,"totalWith -- > " + totalWith);
        LogUtils.d(this,"mSelfWidth -- > " + mSelfWidth);
        //如果小于/等于当前控件的宽度，则可以添加，否则不能添加
        return totalWith <= mSelfWidth;
    }


    @Override
    protected void onLayout(boolean changed,int l,int t,int r,int b) {
        //摆放孩子
        LogUtils.d(this,"onLayout -- > " + getChildCount());
    }
}
