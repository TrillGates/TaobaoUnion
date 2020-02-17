package com.sunofbeaches.taobaounion.view;

import com.sunofbeaches.taobaounion.base.IBaseCallback;
import com.sunofbeaches.taobaounion.model.domain.Categories;

public interface IHomeCallback extends IBaseCallback {

    void onCategoriesLoaded(Categories categories);

}
