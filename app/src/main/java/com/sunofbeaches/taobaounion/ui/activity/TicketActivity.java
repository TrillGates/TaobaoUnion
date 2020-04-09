package com.sunofbeaches.taobaounion.ui.activity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
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
import com.sunofbeaches.taobaounion.utils.ToastUtil;
import com.sunofbeaches.taobaounion.utils.UrlUtils;
import com.sunofbeaches.taobaounion.view.ITicketPagerCallback;

import butterknife.BindView;

public class TicketActivity extends BaseActivity implements ITicketPagerCallback {

    private ITicketPresenter mTicketPresenter;

    private boolean mHasTabaoApp = false;

    @BindView(R.id.ticket_cover)
    public ImageView mCover;

    @BindView(R.id.ticket_back_press)
    public View backPress;

    @BindView(R.id.ticket_code)
    public EditText mTicketCode;


    @BindView(R.id.ticket_copy_or_open_btn)
    public TextView mOpenOrCopyBtn;


    @BindView(R.id.ticket_cover_loading)
    public View loadingView;


    @BindView(R.id.ticket_load_retry)
    public View retryLoadText;


    @Override
    protected void initPresenter() {
        mTicketPresenter = PresenterManager.getInstance().getTicketPresenter();
        if(mTicketPresenter != null) {
            mTicketPresenter.registerViewCallback(this);
        }
        //判断是否有安装淘宝
        // act=android.intent.action.MAIN
        // cat=[android.intent.category.LAUNCHER]
        // flg=0x10200000
        // cmp=com.taobao.taobao/com.taobao.tao.welcome.Welcome
        //包名是这个：com.taobao.taobao
        // 检查是否有安装淘宝应用
        PackageManager pm = getPackageManager();
        try {
            PackageInfo packageInfo = pm.getPackageInfo("com.taobao.taobao",PackageManager.MATCH_UNINSTALLED_PACKAGES);
            mHasTabaoApp = packageInfo != null;
        } catch(PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            mHasTabaoApp = false;
        }
        LogUtils.d(this,"mHasTabaoApp -- > " + mHasTabaoApp);
        //根据这个值去修改UI
        mOpenOrCopyBtn.setText(mHasTabaoApp ? "打开淘宝领券" : "复制淘口令");
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
        mOpenOrCopyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //复制淘口令
                //拿到内容
                String ticketCode = mTicketCode.getText().toString().trim();
                LogUtils.d(TicketActivity.this,"ticketCode --- > " + ticketCode);
                ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                //复制到粘贴板
                ClipData clipData = ClipData.newPlainText("sob_taobao_ticket_code",ticketCode);
                cm.setPrimaryClip(clipData);
                //判断有没有淘宝
                if(mHasTabaoApp) {
                    //如果有就打开淘宝
                    Intent taobaoIntent = new Intent();
                    //taobaoIntent.setAction("android.intent.action.MAIN");
                    //taobaoIntent.addCategory("android.intent.category.LAUNCHER");
                    //com.taobao.taobao/com.taobao.tao.TBMainActivity
                    ComponentName componentName = new ComponentName("com.taobao.taobao","com.taobao.tao.TBMainActivity");
                    taobaoIntent.setComponent(componentName);
                    startActivity(taobaoIntent);
                } else {
                    //没有提示复制成功
                    ToastUtil.showToast("已经复制,粘贴分享,或打开淘宝");
                }
            }
        });
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_ticket;
    }

    @Override
    public void onTicketLoaded(String cover,TicketResult result) {
        //设置图片封面
        if(mCover != null && !TextUtils.isEmpty(cover)) {
            ViewGroup.LayoutParams layoutParams = mCover.getLayoutParams();
            int tagetWith = layoutParams.width / 2;
            LogUtils.d(this,"cover width -- > " + tagetWith);
            String coverPath = UrlUtils.getCoverPath(cover);
            LogUtils.d(this,"coverPath === > " + coverPath);
            Glide.with(this).load(coverPath).into(mCover);
        }

        if(TextUtils.isEmpty(cover)) {
            mCover.setImageResource(R.mipmap.no_image);
        }

        //设置一下code
        if(result != null && result.getData().getTbk_tpwd_create_response() != null) {
            mTicketCode.setText(result.getData().getTbk_tpwd_create_response().getData().getModel());
        }
        if(loadingView != null) {
            loadingView.setVisibility(View.GONE);
        }
    }

    @Override
    public void onError() {
        if(loadingView != null) {
            loadingView.setVisibility(View.GONE);
        }
        if(retryLoadText != null) {
            retryLoadText.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onLoading() {
        if(retryLoadText != null) {
            retryLoadText.setVisibility(View.GONE);
        }
        if(loadingView != null) {
            loadingView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onEmpty() {

    }
}
