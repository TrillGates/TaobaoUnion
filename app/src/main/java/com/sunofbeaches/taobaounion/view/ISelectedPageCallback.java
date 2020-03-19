package com.sunofbeaches.taobaounion.view;

import com.sunofbeaches.taobaounion.base.IBaseCallback;
import com.sunofbeaches.taobaounion.model.domain.SelectedContent;
import com.sunofbeaches.taobaounion.model.domain.SelectedPageCategory;

public interface ISelectedPageCallback extends IBaseCallback {

    /**
     * 分类内容结果
     *
     * @param categories 分类内容
     */
    void onCategoriesLoaded(SelectedPageCategory categories);


    /**
     * 内容
     *
     * @param content
     */
    void onContentLoaded(SelectedContent content);

}
