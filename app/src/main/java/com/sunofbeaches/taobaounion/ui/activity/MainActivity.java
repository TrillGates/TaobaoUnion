package com.sunofbeaches.taobaounion.ui.activity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.sunofbeaches.taobaounion.R;
import com.sunofbeaches.taobaounion.base.BaseActivity;
import com.sunofbeaches.taobaounion.base.BaseFragment;
import com.sunofbeaches.taobaounion.ui.fragment.HomeFragment;
import com.sunofbeaches.taobaounion.ui.fragment.OnSellFragment;
import com.sunofbeaches.taobaounion.ui.fragment.SearchFragment;
import com.sunofbeaches.taobaounion.ui.fragment.SelectedFragment;
import com.sunofbeaches.taobaounion.utils.LogUtils;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import butterknife.BindView;

public class MainActivity extends BaseActivity implements IMainActivity {

    @BindView(R.id.main_navigation_bar)
    public BottomNavigationView mNavigationView;
    private HomeFragment mHomeFragment;
    private OnSellFragment mRedPacketFragment;
    private SelectedFragment mSelectedFragment;
    private SearchFragment mSearchFragment;
    private FragmentManager mFm;

    @Override
    protected void initPresenter() {

    }

    /**
     * 跳转到搜索界面
     */
    public void switch2Search() {
        // switchFragment(mSearchFragment);
        //切换导航栏的选中项
        mNavigationView.setSelectedItemId(R.id.search);
    }


    @Override
    protected void initEvent() {
        initListener();
    }

    @Override
    protected void initView() {
        initFragments();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }


    private void initFragments() {
        mHomeFragment = new HomeFragment();
        mRedPacketFragment = new OnSellFragment();
        mSelectedFragment = new SelectedFragment();
        mSearchFragment = new SearchFragment();
        mFm = getSupportFragmentManager();
        switchFragment(mHomeFragment);
    }

    private void initListener() {
        mNavigationView.setOnNavigationItemSelectedListener(item -> {
            if(item.getItemId() == R.id.home) {
                LogUtils.d(this,"切换到首页");
                switchFragment(mHomeFragment);
            } else if(item.getItemId() == R.id.selected) {
                LogUtils.i(this,"切换到精选");
                switchFragment(mSelectedFragment);
            } else if(item.getItemId() == R.id.red_packet) {
                LogUtils.w(this,"切换到特惠");
                switchFragment(mRedPacketFragment);
            } else if(item.getItemId() == R.id.search) {
                LogUtils.e(this,"切换到搜索");
                switchFragment(mSearchFragment);
            }
            return true;
        });
    }

    /**
     * 上一次显示的fragment
     */
    private BaseFragment lastOneFragment = null;

    private void switchFragment(BaseFragment targetFragment) {
        //如果上一个fragment跟当前要切换的fragment是同一个，那么不需要切换
        if(lastOneFragment == targetFragment) {
            return;
        }
        //修改成add和hide的方式来控制Fragment的切换
        FragmentTransaction fragmentTransaction = mFm.beginTransaction();
        if(!targetFragment.isAdded()) {
            fragmentTransaction.add(R.id.main_page_container,targetFragment);
        } else {
            fragmentTransaction.show(targetFragment);
        }
        if(lastOneFragment != null) {
            fragmentTransaction.hide(lastOneFragment);
        }
        lastOneFragment = targetFragment;
        //fragmentTransaction.replace(R.id.main_page_container,targetFragment);
        fragmentTransaction.commit();
    }

}
