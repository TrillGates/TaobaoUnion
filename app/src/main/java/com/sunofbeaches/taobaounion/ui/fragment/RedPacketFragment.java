package com.sunofbeaches.taobaounion.ui.fragment;

import android.view.View;

import com.sunofbeaches.taobaounion.R;
import com.sunofbeaches.taobaounion.base.BaseFragment;

public class RedPacketFragment extends BaseFragment {
    @Override
    protected int getRootViewResId() {
        return R.layout.fragment_red_packet;
    }

    @Override
    protected void initView(View rootView) {
        setUpState(State.SUCCESS);
    }
}
