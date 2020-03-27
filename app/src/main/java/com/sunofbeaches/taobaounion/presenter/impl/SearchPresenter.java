package com.sunofbeaches.taobaounion.presenter.impl;

import com.sunofbeaches.taobaounion.model.Api;
import com.sunofbeaches.taobaounion.model.domain.Histories;
import com.sunofbeaches.taobaounion.model.domain.SearchRecommend;
import com.sunofbeaches.taobaounion.model.domain.SearchResult;
import com.sunofbeaches.taobaounion.presenter.ISearchPresenter;
import com.sunofbeaches.taobaounion.utils.JsonCacheUtil;
import com.sunofbeaches.taobaounion.utils.LogUtils;
import com.sunofbeaches.taobaounion.utils.RetrofitManager;
import com.sunofbeaches.taobaounion.view.ISearchPageCallback;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SearchPresenter implements ISearchPresenter {

    private final Api mApi;
    private ISearchPageCallback mSearchViewCallback = null;
    private String mCurrentKeyword = null;
    private final JsonCacheUtil mJsonCacheUtil;

    public SearchPresenter() {
        RetrofitManager instance = RetrofitManager.getInstance();
        Retrofit retrofit = instance.getRetrofit();
        mApi = retrofit.create(Api.class);
        mJsonCacheUtil = JsonCacheUtil.getInstance();
    }

    public static final int DEFAULT_PAGE = 0;

    /**
     * 搜索的当前页面
     */
    private int mCurrentPage = DEFAULT_PAGE;

    @Override
    public void getHistories() {
        Histories histories = mJsonCacheUtil.getValue(KEY_HISTORIES,Histories.class);
        if(mSearchViewCallback != null) {
            mSearchViewCallback.onHistoriesLoaded(histories);
        }
    }

    @Override
    public void delHistories() {
        mJsonCacheUtil.delCache(KEY_HISTORIES);
        if(mSearchViewCallback != null) {
            mSearchViewCallback.onHistoriesDeleted();
        }
    }

    public static final String KEY_HISTORIES = "key_histories";

    public static final int DEFAULT_HISTORIES_SIZE = 10;
    private int mHistoriesMaxSize = DEFAULT_HISTORIES_SIZE;

    /**
     * 添加历史记录
     *
     * @param history
     */
    private void saveHistory(String history) {
        Histories histories = mJsonCacheUtil.getValue(KEY_HISTORIES,Histories.class);
        //如果说已经在了，就干掉，然后再添加
        List<String> historiesList = null;
        if(histories != null && histories.getHistories() != null) {
            historiesList = histories.getHistories();
            if(historiesList.contains(history)) {
                historiesList.remove(history);
            }
        }
        //去重完成
        //处理没有数据的情况
        if(historiesList == null) {
            historiesList = new ArrayList<>();
        }
        if(histories == null) {
            histories = new Histories();
        }
        histories.setHistories(historiesList);
        //对个数进行限制
        if(historiesList.size() > mHistoriesMaxSize) {
            historiesList = historiesList.subList(0,mHistoriesMaxSize);
        }
        //添加记录
        historiesList.add(history);
        //保存记录
        mJsonCacheUtil.saveCache(KEY_HISTORIES,histories);
    }


    @Override
    public void doSearch(String keyword) {
        if(mCurrentKeyword == null || !mCurrentKeyword.equals(keyword)) {
            this.saveHistory(keyword);
            this.mCurrentKeyword = keyword;
        }
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
        if(mCurrentKeyword == null) {
            if(mSearchViewCallback != null) {
                mSearchViewCallback.onEmpty();
            }
        } else {
            this.doSearch(mCurrentKeyword);
        }
    }

    @Override
    public void loaderMore() {
        mCurrentPage++;
        if(mCurrentKeyword == null) {
            if(mSearchViewCallback != null) {
                mSearchViewCallback.onMoreLoadedEmpty();
            }
        } else {
            doSearchMore();
        }
    }

    private void doSearchMore() {
        Call<SearchResult> task = mApi.doSearch(mCurrentPage,mCurrentKeyword);
        task.enqueue(new Callback<SearchResult>() {
            @Override
            public void onResponse(Call<SearchResult> call,Response<SearchResult> response) {
                int code = response.code();
                LogUtils.d(SearchPresenter.this,"do search result code -- > " + code);
                if(code == HttpURLConnection.HTTP_OK) {
                    handleMoreSearchResult(response.body());
                } else {
                    onLoaderMoreError();
                }
            }

            @Override
            public void onFailure(Call<SearchResult> call,Throwable t) {
                t.printStackTrace();
                onLoaderMoreError();
            }
        });
    }

    private void handleMoreSearchResult(SearchResult result) {
        if(mSearchViewCallback != null) {
            if(isResultEmpty(result)) {
                //数据为空
                mSearchViewCallback.onMoreLoadedEmpty();
            } else {
                mSearchViewCallback.onMoreLoaded(result);
            }
        }
    }

    private void onLoaderMoreError() {
        if(mSearchViewCallback != null) {
            mSearchViewCallback.onMoreLoadedError();
        }
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
