package com.sunofbeaches.taobaounion.view;

import com.sunofbeaches.taobaounion.base.IBaseCallback;
import com.sunofbeaches.taobaounion.model.domain.OnSellContent;

public interface IOnSellPageCallback extends IBaseCallback {

    /**
     * 特惠内容加载完成
     *
     * @param result
     */
    void onContentLoadedSuccess(OnSellContent result);

    /**
     * 加载更多的结果
     *
     * @param moreResult
     */
    void onMoreLoaded(OnSellContent moreResult);


    /**
     * 加载更多失败
     */
    void onMoreLoadedError();

    /**
     * 没有更多内容
     */
    void onMoreLoadedEmpty();

}
