package com.sunofbeaches.taobaounion.presenter.impl;

import com.sunofbeaches.taobaounion.model.Api;
import com.sunofbeaches.taobaounion.model.domain.SelectedContent;
import com.sunofbeaches.taobaounion.model.domain.SelectedPageCategory;
import com.sunofbeaches.taobaounion.presenter.ISelectedPagePresenter;
import com.sunofbeaches.taobaounion.utils.LogUtils;
import com.sunofbeaches.taobaounion.utils.RetrofitManager;
import com.sunofbeaches.taobaounion.view.ISelectedPageCallback;

import java.net.HttpURLConnection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SelectedPagePresenterImpl implements ISelectedPagePresenter {

    private final Api mApi;
    private SelectedPageCategory.DataBean mCurrentCategoryItem = null;

    public SelectedPagePresenterImpl() {
        Retrofit retrofit = RetrofitManager.getInstance().getRetrofit();
        mApi = retrofit.create(Api.class);
    }

    private ISelectedPageCallback mViewCallback = null;

    @Override
    public void getCategories() {
        if(mViewCallback != null) {
            mViewCallback.onLoading();
        }
        //拿retrofit
        Call<SelectedPageCategory> task = mApi.getSelectedPageCategories();
        task.enqueue(new Callback<SelectedPageCategory>() {
            @Override
            public void onResponse(Call<SelectedPageCategory> call,Response<SelectedPageCategory> response) {
                int resultCode = response.code();
                LogUtils.d(SelectedPagePresenterImpl.this,"result code -- > " + resultCode);
                if(resultCode == HttpURLConnection.HTTP_OK) {
                    SelectedPageCategory result = response.body();
                    //通知UI更新
                    if(mViewCallback != null) {
                        mViewCallback.onCategoriesLoaded(result);
                    }
                } else {
                    onLoadedError();
                }
            }

            @Override
            public void onFailure(Call<SelectedPageCategory> call,Throwable t) {
                onLoadedError();
            }
        });
    }

    private void onLoadedError() {
        if(mViewCallback != null) {
            mViewCallback.onError();
        }
    }

    @Override
    public void getContentByCategory(SelectedPageCategory.DataBean item) {
        this.mCurrentCategoryItem = item;
        Call<SelectedContent> task = mApi.getSelectedPageContent(item.getFavorites_id());
        task.enqueue(new Callback<SelectedContent>() {
            @Override
            public void onResponse(Call<SelectedContent> call,Response<SelectedContent> response) {
                int resultCode = response.code();
                LogUtils.d(SelectedPagePresenterImpl.this,"getContentByCategory result code -- > " + resultCode);
                if(resultCode == HttpURLConnection.HTTP_OK) {
                    SelectedContent result = response.body();
                    if(mViewCallback != null) {
                        mViewCallback.onContentLoaed(result);
                    }
                } else {
                    onLoadedError();
                }
            }

            @Override
            public void onFailure(Call<SelectedContent> call,Throwable t) {
                onLoadedError();
            }
        });
    }

    @Override
    public void reloadContent() {
        if(mCurrentCategoryItem != null) {
            this.getContentByCategory(mCurrentCategoryItem);
        }
    }

    @Override
    public void registerViewCallback(ISelectedPageCallback callback) {
        this.mViewCallback = callback;
    }

    @Override
    public void unregisterViewCallback(ISelectedPageCallback callback) {
        this.mViewCallback = null;
    }
}
