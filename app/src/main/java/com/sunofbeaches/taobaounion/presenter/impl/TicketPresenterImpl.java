package com.sunofbeaches.taobaounion.presenter.impl;

import com.sunofbeaches.taobaounion.model.Api;
import com.sunofbeaches.taobaounion.model.domain.TicketParams;
import com.sunofbeaches.taobaounion.model.domain.TicketResult;
import com.sunofbeaches.taobaounion.presenter.ITicketPresenter;
import com.sunofbeaches.taobaounion.utils.LogUtils;
import com.sunofbeaches.taobaounion.utils.RetrofitManager;
import com.sunofbeaches.taobaounion.utils.UrlUtils;
import com.sunofbeaches.taobaounion.view.ITicketPagerCallback;

import java.net.HttpURLConnection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class TicketPresenterImpl implements ITicketPresenter {

    @Override
    public void getTicket(String title,String url,String cover) {
        LogUtils.d(this,"title -- > " + title);
        LogUtils.d(this,"url -- > " + url);
        LogUtils.d(this,"cover -- > " + cover);
        String targetUrl = UrlUtils.getTicketUrl(url);
        //去获取淘口令
        Retrofit retrofit = RetrofitManager.getInstance().getRetrofit();
        Api api = retrofit.create(Api.class);
        TicketParams ticketParams = new TicketParams(targetUrl,title);
        Call<TicketResult> task = api.getTicket(ticketParams);
        task.enqueue(new Callback<TicketResult>() {
            @Override
            public void onResponse(Call<TicketResult> call,Response<TicketResult> response) {
                int code = response.code();
                LogUtils.d(TicketPresenterImpl.this,"result code == > " + code);
                if(code == HttpURLConnection.HTTP_OK) {
                    //请求成功
                    TicketResult ticketResult = response.body();
                    LogUtils.d(TicketPresenterImpl.this,"result " + ticketResult);
                } else {
                    //请求失败
                }
            }

            @Override
            public void onFailure(Call<TicketResult> call,Throwable t) {

            }
        });
    }
    @Override
    public void unregisterViewCallback(ITicketPagerCallback callback) {

    }

   @Override
    public void registerViewCallback(ITicketPagerCallback callback) {

    }
}
