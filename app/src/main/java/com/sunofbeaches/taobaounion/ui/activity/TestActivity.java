package com.sunofbeaches.taobaounion.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;

import com.sunofbeaches.taobaounion.R;
import com.sunofbeaches.taobaounion.ui.custom.TextFlowLayout;
import com.sunofbeaches.taobaounion.utils.LogUtils;
import com.sunofbeaches.taobaounion.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.ButterKnife;

public class TestActivity extends Activity {

    @BindView(R.id.test_navigation_bar)
    public RadioGroup navigationBar;

    @BindView(R.id.test_flow_text)
    public TextFlowLayout flowText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ButterKnife.bind(this);
        initListener();
        List<String> testList = new ArrayList<>();
        testList.add("电脑");
        testList.add("电脑显示器");
        testList.add("Nuxt.js");
        testList.add("Vue.js课程");
        testList.add("机械键盘");
        testList.add("滑板鞋");
        testList.add("运动鞋");
        testList.add("肥宅快乐水");
        testList.add("阳光沙滩");
        testList.add("android编程");
        testList.add("机械键盘");
        testList.add("滑板鞋");
        testList.add("运动鞋");
        testList.add("肥宅快乐水");
        testList.add("阳光沙滩");
        testList.add("android编程");
        testList.add("机械键盘");
        testList.add("滑板鞋");
        testList.add("肥宅快乐水");
        testList.add("阳光沙滩");
        testList.add("android编程");
        testList.add("机械键盘");
        testList.add("滑板鞋");
        testList.add("运动鞋");
        testList.add("肥宅快乐水");
        testList.add("阳光沙滩");
        testList.add("android编程");
        testList.add("JavaWeb后台");
        flowText.setTextList(testList);
        flowText.setOnFlowTextItemClickListener(new TextFlowLayout.OnFlowTextItemClickListener() {
            @Override
            public void onFlowItemClick(String text) {
                LogUtils.d(TestActivity.this," click text -== > " + text);
            }
        });
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
