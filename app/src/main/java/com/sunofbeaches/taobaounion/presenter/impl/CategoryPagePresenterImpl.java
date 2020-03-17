package com.sunofbeaches.taobaounion.presenter.impl;

import com.sunofbeaches.taobaounion.model.Api;
import com.sunofbeaches.taobaounion.model.domain.HomePagerContent;
import com.sunofbeaches.taobaounion.presenter.ICategoryPagerPresenter;
import com.sunofbeaches.taobaounion.utils.LogUtils;
import com.sunofbeaches.taobaounion.utils.RetrofitManager;
import com.sunofbeaches.taobaounion.utils.UrlUtils;
import com.sunofbeaches.taobaounion.view.ICategoryPagerCallback;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CategoryPagePresenterImpl implements ICategoryPagerPresenter {
    private Map<Integer,Integer> pagesInfo = new HashMap<>();
    public static final int DEFAULT_PAGE = 1;
    private Integer mCurrentPage;
    
    @Override
    public void getContentByCategoryId(int categoryId) {
        for(ICategoryPagerCallback callback : callbacks) {
            if(callback.getCategoryId() == categoryId) {
                callback.onLoading();
            }
        }
        Integer targetPage = pagesInfo.get(categoryId);
        if(targetPage == null) {
            targetPage = DEFAULT_PAGE;
            pagesInfo.put(categoryId,targetPage);
        }
        Call<HomePagerContent> task = createTask(categoryId,targetPage);
        task.enqueue(new Callback<HomePagerContent>() {
            @Override
            public void onResponse(Call<HomePagerContent> call,Response<HomePagerContent> response) {
                int code = response.code();
                if(code == HttpURLConnection.HTTP_OK) {
                    HomePagerContent pageContent = response.body();
                    //LogUtils.d(CategoryPagePresenterImpl.this,"page content -- > " + pageContent);
                    //把数据给到UI更新
                    handleHomePageContentResult(pageContent,categoryId);
                } else {
                    handleNetworkError(categoryId);
                }
            }

            @Override
            public void onFailure(Call<HomePagerContent> call,Throwable t) {
                LogUtils.d(CategoryPagePresenterImpl.this,"onFailure -- > " + t.toString());
                handleNetworkError(categoryId);
            }
        });
    }

    private Call<HomePagerContent> createTask(int categoryId,Integer targetPage) {
        String url = UrlUtils.createHomePageUrl(categoryId,targetPage);
        LogUtils.d(this,"url -- > " + url);
        Retrofit retrofit = RetrofitManager.getInstance().getRetrofit();
        Api api = retrofit.create(Api.class);
        return api.getHomePageContent(url);
    }

    private void handleNetworkError(int categoryId) {
        for(ICategoryPagerCallback callback : callbacks) {
            if(callback.getCategoryId() == categoryId) {
                callback.onError();
            }
        }
    }

    private void handleHomePageContentResult(HomePagerContent pageContent,int categoryId) {
        //通知UI层更新数据
        List<HomePagerContent.DataBean> data = pageContent.getData();
        for(ICategoryPagerCallback callback : callbacks) {
            if(callback.getCategoryId() == categoryId) {
                if(pageContent == null || pageContent.getData().size() == 0) {
                    callback.onEmpty();
                } else {
                    List<HomePagerContent.DataBean> looperData = data.subList(data.size() - 5,data.size());
                    callback.onLooperListLoaded(looperData);
                    callback.onContentLoaded(data);
                }
            }
        }
    }

    @Override
    public void loaderMore(int categoryId) {
        //加载更多的数据
        //1、拿到当前页面
        mCurrentPage = pagesInfo.get(categoryId);
        if(mCurrentPage == null) {
            mCurrentPage = 1;
        }
        //2、页码++
        mCurrentPage++;
        //3、加载数据
        Call<HomePagerContent> task = createTask(categoryId,mCurrentPage);
        //4、处理数据结果
        task.enqueue(new Callback<HomePagerContent>() {
            @Override
            public void onResponse(Call<HomePagerContent> call,Response<HomePagerContent> response) {
                //结果
                int code = response.code();
                LogUtils.d(CategoryPagePresenterImpl.this,"result code -- > " + code);
                if(code == HttpURLConnection.HTTP_OK) {
                    HomePagerContent result = response.body();
                    // LogUtils.d(CategoryPagePresenterImpl.this,"result -- > " + result.toString());
                    handleLoaderResult(result,categoryId);
                } else {
                    handleLoaderMoreError(categoryId);
                }
            }

            @Override
            public void onFailure(Call<HomePagerContent> call,Throwable t) {
                //请求失败
                LogUtils.d(CategoryPagePresenterImpl.this,t.toString());
                handleLoaderMoreError(categoryId);
            }
        });
    }

    private void handleLoaderResult(HomePagerContent result,int categoryId) {
        for(ICategoryPagerCallback callback : callbacks) {
            if(callback.getCategoryId() == categoryId) {
                if(result == null || result.getData().size() == 0) {
                    callback.onLoaderMoreEmpty();
                } else {
                    callback.onLoaderMoreLoaded(result.getData());
                }
            }
        }
    }

    private void handleLoaderMoreError(int categoryId) {
        mCurrentPage--;
        pagesInfo.put(categoryId,mCurrentPage);
        for(ICategoryPagerCallback callback : callbacks) {
            if(callback.getCategoryId() == categoryId) {
                callback.onLoaderMoreError();
            }
        }
    }

    @Override
    public void reload(int categoryId) {

    }

    private List<ICategoryPagerCallback> callbacks = new ArrayList<>();

    @Override
    public void registerViewCallback(ICategoryPagerCallback callback) {
        if(!callbacks.contains(callback)) {
            callbacks.add(callback);
        }
    }

    @Override
    public void unregisterViewCallback(ICategoryPagerCallback callback) {
        callbacks.remove(callback);
    }
}
