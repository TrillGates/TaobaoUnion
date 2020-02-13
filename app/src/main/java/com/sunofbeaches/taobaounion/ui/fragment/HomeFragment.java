package com.sunofbeaches.taobaounion.ui.fragment;

import com.sunofbeaches.taobaounion.R;
import com.sunofbeaches.taobaounion.base.BaseFragment;
import com.sunofbeaches.taobaounion.model.domain.Categories;
import com.sunofbeaches.taobaounion.presenter.IHomePresenter;
import com.sunofbeaches.taobaounion.presenter.impl.HomePresenterImpl;
import com.sunofbeaches.taobaounion.view.IHomeCallback;


public class HomeFragment extends BaseFragment implements IHomeCallback {

    private IHomePresenter mHomePresenter;

    @Override
    protected int getRootViewResId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initPresenter() {
        //创建Presenter
        mHomePresenter = new HomePresenterImpl();
        mHomePresenter.registerCallback(this);
    }

    @Override
    protected void loadData() {
        //加载数据
        mHomePresenter.getCategories();
    }

    @Override
    public void onCategoriesLoaded(Categories categories) {
        //加载的数据就会从这里回来
    }

    @Override
    protected void release() {
        //取消回调注册
        if(mHomePresenter != null) {
            mHomePresenter.unregisterCallback(this);
        }
    }
}
