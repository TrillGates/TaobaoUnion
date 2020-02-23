package com.sunofbeaches.taobaounion.ui.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.sunofbeaches.taobaounion.R;
import com.sunofbeaches.taobaounion.base.BaseFragment;
import com.sunofbeaches.taobaounion.model.domain.Categories;
import com.sunofbeaches.taobaounion.presenter.IHomePresenter;
import com.sunofbeaches.taobaounion.presenter.impl.HomePresenterImpl;
import com.sunofbeaches.taobaounion.ui.adapter.HomePagerAdapter;
import com.sunofbeaches.taobaounion.utils.LogUtils;
import com.sunofbeaches.taobaounion.view.IHomeCallback;

import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;


public class HomeFragment extends BaseFragment implements IHomeCallback {

    @BindView(R.id.home_indicator)
    public TabLayout mTabLayout;

    private IHomePresenter mHomePresenter;

    @BindView(R.id.home_pager)
    public ViewPager homePager;
    private HomePagerAdapter mHomePagerAdapter;

    @Override
    protected int getRootViewResId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView(View rootView) {
        mTabLayout.setupWithViewPager(homePager);
        //给ViewPager设置适配器
        mHomePagerAdapter = new HomePagerAdapter(getChildFragmentManager());
        //设置适配器
        homePager.setAdapter(mHomePagerAdapter);
    }

    @Override
    protected void initPresenter() {
        //创建Presenter
        mHomePresenter = new HomePresenterImpl();
        mHomePresenter.registerViewCallback(this);
    }

    @Override
    protected View loadRootView(LayoutInflater inflater,ViewGroup container) {
        return inflater.inflate(R.layout.base_home_fragment_layout,container,false);
    }

    @Override
    protected void loadData() {
        //加载数据
        mHomePresenter.getCategories();
    }

    @Override
    public void onCategoriesLoaded(Categories categories) {
        setUpState(State.SUCCESS);
        LogUtils.d(this,"onCategoriesLoaded..");
        //加载的数据就会从这里回来
        if(mHomePagerAdapter != null) {
            //homePager.setOffscreenPageLimit(categories.getData().size());
            mHomePagerAdapter.setCategories(categories);
        }
    }

    @Override
    public void onError() {
        setUpState(State.ERROR);
    }

    @Override
    public void onLoading() {
        setUpState(State.LOADING);
    }

    @Override
    public void onEmpty() {
        setUpState(State.EMPTY);
    }

    @Override
    protected void release() {
        //取消回调注册
        if(mHomePresenter != null) {
            mHomePresenter.unregisterViewCallback(this);
        }
    }

    @Override
    protected void onRetryClick() {
        //网络错误,点击了重试
        //重新加载分类内容
        if(mHomePresenter != null) {
            mHomePresenter.getCategories();
        }
    }
}
