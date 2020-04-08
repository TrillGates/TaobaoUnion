package com.sunofbeaches.taobaounion.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.sunofbeaches.taobaounion.R;
import com.sunofbeaches.taobaounion.base.BaseFragment;
import com.sunofbeaches.taobaounion.model.domain.Categories;
import com.sunofbeaches.taobaounion.presenter.IHomePresenter;
import com.sunofbeaches.taobaounion.ui.activity.IMainActivity;
import com.sunofbeaches.taobaounion.ui.activity.ScanQrCodeActivity;
import com.sunofbeaches.taobaounion.ui.adapter.HomePagerAdapter;
import com.sunofbeaches.taobaounion.utils.LogUtils;
import com.sunofbeaches.taobaounion.utils.PresenterManager;
import com.sunofbeaches.taobaounion.view.IHomeCallback;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;


public class HomeFragment extends BaseFragment implements IHomeCallback {

    @BindView(R.id.home_indicator)
    public TabLayout mTabLayout;

    private IHomePresenter mHomePresenter;

    @BindView(R.id.home_pager)
    public ViewPager homePager;

    @BindView(R.id.scan_icon)
    public View scanBtn;


    @BindView(R.id.home_search_input_box)
    public View mSearchInputBox;
    private HomePagerAdapter mHomePagerAdapter;

    @Override
    protected int getRootViewResId() {
        return R.layout.fragment_home;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,@Nullable ViewGroup container,@Nullable Bundle savedInstanceState) {
        LogUtils.d(this,"on create view...");
        return super.onCreateView(inflater,container,savedInstanceState);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        LogUtils.d(this,"on destroy view...");
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
    protected void initListener() {
        scanBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到扫码界面
                startActivity(new Intent(getContext(),ScanQrCodeActivity.class));
            }
        });
    }

    @Override
    protected void initPresenter() {
        //创建Presenter
        mHomePresenter = PresenterManager.getInstance().getHomePresenter();
        mHomePresenter.registerViewCallback(this);
        mSearchInputBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到搜索页面
                FragmentActivity activity = getActivity();
                if(activity instanceof IMainActivity) {
                    ((IMainActivity) activity).switch2Search();
                }
            }
        });
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
