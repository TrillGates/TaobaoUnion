package com.sunofbeaches.taobaounion.utils;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import com.sunofbeaches.taobaounion.model.domain.IGetTicketBaseInfo;
import com.sunofbeaches.taobaounion.presenter.ITicketPresenter;
import com.sunofbeaches.taobaounion.ui.activity.TicketActivity;

public class TicketUtil {

    public static void toTicketPage(Context context,IGetTicketBaseInfo baseInfo) {
        //特惠列表内容被点击
        //处理数据
        String title = baseInfo.getTitle();
        //详情的地址
        String url = baseInfo.getUrl();
        if(TextUtils.isEmpty(url)) {
            url = baseInfo.getUrl();
        }
        String cover = baseInfo.getCover();
        //拿到tiketPresenter去加载数据
        ITicketPresenter ticketPresenter = PresenterManager.getInstance().getTicketPresenter();
        ticketPresenter.getTicket(title,url,cover);
        context.startActivity(new Intent(context,TicketActivity.class));
    }
}
