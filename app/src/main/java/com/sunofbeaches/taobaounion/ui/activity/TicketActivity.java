package com.sunofbeaches.taobaounion.ui.activity;

import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sunofbeaches.taobaounion.R;
import com.sunofbeaches.taobaounion.base.BaseActivity;
import com.sunofbeaches.taobaounion.model.domain.TicketResult;
import com.sunofbeaches.taobaounion.presenter.ITicketPresenter;
import com.sunofbeaches.taobaounion.utils.LogUtils;
import com.sunofbeaches.taobaounion.utils.PresenterManager;
import com.sunofbeaches.taobaounion.utils.UrlUtils;
import com.sunofbeaches.taobaounion.view.ITicketPagerCallback;

import butterknife.BindView;

public class TicketActivity extends BaseActivity implements ITicketPagerCallback {

    private ITicketPresenter mTicketPresenter;

    @BindView(R.id.ticket_cover)
    public ImageView mCover;

    @BindView(R.id.ticket_back_press)
    public View backPress;

    @BindView(R.id.ticket_code)
    public EditText mTicketCode;


    @BindView(R.id.ticket_copy_or_open_btn)
    public TextView mOpenOrCopyBtn;


    @Override
    protected void initPresenter() {
        mTicketPresenter = PresenterManager.getInstance().getTicketPresenter();
        if(mTicketPresenter != null) {
            mTicketPresenter.registerViewCallback(this);
        }
    }

    @Override
    protected void release() {
        if(mTicketPresenter != null) {
            mTicketPresenter.unregisterViewCallback(this);
        }
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initEvent() {
        backPress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_ticket;
    }

    @Override
    public void onTicketLoaded(String cover,TicketResult result) {
        if(mCover != null && !TextUtils.isEmpty(cover)) {
            ViewGroup.LayoutParams layoutParams = mCover.getLayoutParams();
            int tagetWith = layoutParams.width / 2;
            LogUtils.d(this,"cover width -- > " + tagetWith);
            String coverPath = UrlUtils.getCoverPath(cover);
            LogUtils.d(this,"coverPath === > " + coverPath);
            Glide.with(this).load(coverPath).into(mCover);
        }
        if(result != null && result.getData().getTbk_tpwd_create_response() != null) {
            mTicketCode.setText(result.getData().getTbk_tpwd_create_response().getData().getModel());
        }
    }

    @Override
    public void onError() {

    }

    @Override
    public void onLoading() {

    }

    @Override
    public void onEmpty() {

    }
}
