package com.sunofbeaches.taobaounion.view;

import com.sunofbeaches.taobaounion.base.IBaseCallback;
import com.sunofbeaches.taobaounion.model.domain.HomePagerContent;

import java.util.List;

public interface ICategoryPagerCallback extends IBaseCallback {

    /**
     * 数据加载加回来
     *
     * @param contents
     */
    void onContentLoaded(List<HomePagerContent.DataBean> contents);

    int getCategoryId();

    /**
     * 加更多时网络错误
     */
    void onLoaderMoreError();

    /**
     * 没有更多内容
     */
    void onLoaderMoreEmpty();

    /**
     * 加到了更多内容
     *
     * @param contents
     */
    void onLoaderMoreLoaded(List<HomePagerContent.DataBean> contents);

    /**
     * 轮播图内容加载到了
     *
     * @param contents
     */
    void onLooperListLoaded(List<HomePagerContent.DataBean> contents);

}
