package com.sunofbeaches.taobaounion.presenter;

import com.sunofbeaches.taobaounion.base.IBasePresenter;
import com.sunofbeaches.taobaounion.model.domain.SelectedPageCategory;
import com.sunofbeaches.taobaounion.view.ISelectedPageCallback;

public interface ISelectedPagePresenter extends IBasePresenter<ISelectedPageCallback> {

    /**
     * 获取分类
     */
    void getCategories();

    /**
     * 根据分类获取内容
     *
     * @param item
     */
    void getContentByCategory(SelectedPageCategory.DataBean item);

    /**
     * 重新加载内容
     */
    void reloadContent();

}
