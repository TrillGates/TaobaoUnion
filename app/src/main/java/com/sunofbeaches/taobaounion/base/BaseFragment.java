package com.sunofbeaches.taobaounion.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public abstract class BaseFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,@Nullable ViewGroup
            container,@Nullable Bundle savedInstanceState) {
        View rootView = loadRootView(inflater,container,savedInstanceState);
        initPresenter();
        loadData();
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        release();
    }

    protected void release() {
        //释放资源
    }

    protected void initPresenter() {
        //创建Presenter
    }

    protected void loadData() {
        //加载数据
    }

    protected View loadRootView(LayoutInflater inflater,ViewGroup container,
                                Bundle savedInstanceState) {
        int resId = getRootViewResId();
        return inflater.inflate(resId,container,false);
    }

    protected abstract int getRootViewResId();
}
