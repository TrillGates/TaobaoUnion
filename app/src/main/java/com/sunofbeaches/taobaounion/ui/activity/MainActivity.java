package com.sunofbeaches.taobaounion.ui.activity;

import android.os.Bundle;
import android.util.Log;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.sunofbeaches.taobaounion.R;
import com.sunofbeaches.taobaounion.ui.fragment.HomeFragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private BottomNavigationView mNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initListener();
    }

    private void initListener() {
        mNavigationView.setOnNavigationItemSelectedListener(item -> {
            // Log.d(TAG,"title -- > " + item.getTitle() + " id -- > " + item.getItemId());
            if(item.getItemId() == R.id.home) {
                Log.d(TAG,"切换到首页");
                //TODO
            } else if(item.getItemId() == R.id.selected) {
                Log.d(TAG,"切换到精选");
            } else if(item.getItemId() == R.id.red_packet) {
                Log.d(TAG,"切换到特惠");
            } else if(item.getItemId() == R.id.search) {
                Log.d(TAG,"切换到搜索");
            }
            return true;
        });
    }

    private void initView() {
        mNavigationView = this.findViewById(R.id.main_navigation_bar);

        HomeFragment homeFragment = new HomeFragment();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.add(R.id.main_page_container,homeFragment);
        transaction.commit();
    }
}
