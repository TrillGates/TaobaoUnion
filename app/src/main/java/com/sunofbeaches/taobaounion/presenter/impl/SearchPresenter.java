package com.sunofbeaches.taobaounion.presenter.impl;

import com.sunofbeaches.taobaounion.model.Api;
import com.sunofbeaches.taobaounion.model.domain.SearchRecommend;
import com.sunofbeaches.taobaounion.model.domain.SearchResult;
import com.sunofbeaches.taobaounion.presenter.ISearchPresenter;
import com.sunofbeaches.taobaounion.utils.LogUtils;
import com.sunofbeaches.taobaounion.utils.RetrofitManager;
import com.sunofbeaches.taobaounion.view.ISearchPageCallback;

import java.net.HttpURLConnection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SearchPresenter implements ISearchPresenter {

    private final Api mApi;
    private ISearchPageCallback mSearchViewCallback = null;

    public SearchPresenter() {
        RetrofitManager instance = RetrofitManager.getInstance();
        Retrofit retrofit = instance.getRetrofit();
        mApi = retrofit.create(Api.class);
    }

    public static final int DEFAULT_PAGE = 0;

    /**
     * 搜索的当前页面
     */
    private int mCurrentPage = DEFAULT_PAGE;

    @Override
    public void getHistories() {

    }

    @Override
    public void delHistories() {

    }

    @Override
    public void doSearch(String keyword) {
        //更新UI状态
        if(mSearchViewCallback != null) {
            mSearchViewCallback.onLoading();
        }
        Call<SearchResult> task = mApi.doSearch(mCurrentPage,keyword);
        task.enqueue(new Callback<SearchResult>() {
            @Override
            public void onResponse(Call<SearchResult> call,Response<SearchResult> response) {
                int code = response.code();
                LogUtils.d(SearchPresenter.this,"do search result code -- > " + code);
                if(code == HttpURLConnection.HTTP_OK) {
                    handleSearchResult(response.body());
                } else {
                    onError();
                }
            }

            @Override
            public void onFailure(Call<SearchResult> call,Throwable t) {
                t.printStackTrace();
                onError();
            }
        });
    }

    private void onError() {
        if(mSearchViewCallback != null) {
            mSearchViewCallback.onError();
        }
    }

    private void handleSearchResult(SearchResult result) {
        if(mSearchViewCallback != null) {
            if(isResultEmpty(result)) {
                //数据为空
                mSearchViewCallback.onEmpty();
            } else {
                mSearchViewCallback.onSearchSuccess(result);
            }
        }
    }

    private boolean isResultEmpty(SearchResult result) {
        try {
            return result == null ||
                    result.getData().getTbk_dg_material_optional_response().getResult_list().getMap_data().size() == 0;
        } catch(Exception e) {
            return false;
        }
    }

    @Override
    public void research() {

    }

    @Override
    public void loaderMore() {

    }

    @Override
    public void getRecommendWords() {
        Call<SearchRecommend> task = mApi.getRecommendWords();
        task.enqueue(new Callback<SearchRecommend>() {
            @Override
            public void onResponse(Call<SearchRecommend> call,Response<SearchRecommend> response) {
                int code = response.code();
                LogUtils.d(SearchPresenter.this,"getRecommendWords result code -- > " + code);
                if(code == HttpURLConnection.HTTP_OK) {
                    //处理结果
                    if(mSearchViewCallback != null) {
                        mSearchViewCallback.onRecommendWordsLoaded(response.body().getData());
                    }
                }
            }

            @Override
            public void onFailure(Call<SearchRecommend> call,Throwable t) {
                t.printStackTrace();
            }
        });
    }

    @Override
    public void registerViewCallback(ISearchPageCallback callback) {
        this.mSearchViewCallback = callback;
    }

    @Override
    public void unregisterViewCallback(ISearchPageCallback callback) {
        this.mSearchViewCallback = null;
    }
}
