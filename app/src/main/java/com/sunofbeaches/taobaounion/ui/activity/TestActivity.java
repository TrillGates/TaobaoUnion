package com.sunofbeaches.taobaounion.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;

import com.sunofbeaches.taobaounion.R;
import com.sunofbeaches.taobaounion.utils.LogUtils;
import com.sunofbeaches.taobaounion.utils.ToastUtil;

import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.ButterKnife;

public class TestActivity extends Activity {

    @BindView(R.id.test_navigation_bar)
    public RadioGroup navigationBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ButterKnife.bind(this);
        initListener();
    }

    public void showToast(View view) {
        //Toast.makeText(this,"测试...",Toast.LENGTH_SHORT).show();
        ToastUtil.showToast("测试....");
    }

    private void initListener() {
        navigationBar.setOnCheckedChangeListener((group,checkedId) -> {
            // LogUtils.d(TestActivity.class,"checkedId -- > " + checkedId);
            switch(checkedId) {
                case R.id.test_home:
                    LogUtils.d(TestActivity.class,"切换到首页");
                    break;
                case R.id.test_selected:
                    LogUtils.d(TestActivity.class,"切换到精选页面");
                    break;
                case R.id.test_search:
                    LogUtils.d(TestActivity.class,"切换到搜索页面");
                    break;
                case R.id.test_red_packet:
                    LogUtils.d(TestActivity.class,"切换到特惠");
                    break;
            }
        });
    }
}
