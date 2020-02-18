package com.sunofbeaches.taobaounion.presenter.impl;

import com.sunofbeaches.taobaounion.model.Api;
import com.sunofbeaches.taobaounion.model.domain.HomePagerContent;
import com.sunofbeaches.taobaounion.presenter.ICategoryPagerPresenter;
import com.sunofbeaches.taobaounion.utils.LogUtils;
import com.sunofbeaches.taobaounion.utils.RetrofitManager;
import com.sunofbeaches.taobaounion.utils.UrlUtils;
import com.sunofbeaches.taobaounion.view.ICategoryPagerCallback;

import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CategoryPagePresenterImpl implements ICategoryPagerPresenter {
    private Map<Integer,Integer> pagesInfo = new HashMap<>();
    public static final int DEFAULT_PAGE = 1;

    private CategoryPagePresenterImpl() {

    }

    private static ICategoryPagerPresenter sInstance = null;

    public static ICategoryPagerPresenter getInstance() {
        if(sInstance == null) {
            sInstance = new CategoryPagePresenterImpl();
        }
        return sInstance;
    }


    @Override
    public void getContentByCategoryId(int categoryId) {
        Retrofit retrofit = RetrofitManager.getInstance().getRetrofit();
        Api api = retrofit.create(Api.class);
        Integer targetPage = pagesInfo.get(categoryId);
        if(targetPage == null) {
            targetPage = DEFAULT_PAGE;
            pagesInfo.put(categoryId,targetPage);
        }
        String url = UrlUtils.createHomePageUrl(categoryId,targetPage);
        LogUtils.d(this,"url -- > " + url);
        Call<HomePagerContent> task = api.getHomePageContent(url);
        task.enqueue(new Callback<HomePagerContent>() {
            @Override
            public void onResponse(Call<HomePagerContent> call,Response<HomePagerContent> response) {
                int code = response.code();
                if(code == HttpURLConnection.HTTP_OK) {
                    HomePagerContent pageContent = response.body();
                    LogUtils.d(CategoryPagePresenterImpl.this,"page content -- > " + pageContent);
                } else {
                    //TODO:
                }
            }

            @Override
            public void onFailure(Call<HomePagerContent> call,Throwable t) {
                LogUtils.d(CategoryPagePresenterImpl.this,"onFailure -- > " + t.toString());
            }
        });
    }

    @Override
    public void loaderMore(int categoryId) {

    }

    @Override
    public void reload(int categoryId) {

    }

    @Override
    public void registerViewCallback(ICategoryPagerCallback callback) {

    }

    @Override
    public void unregisterViewCallback(ICategoryPagerCallback callback) {

    }
}
