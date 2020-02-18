package com.sunofbeaches.taobaounion.model;

import com.sunofbeaches.taobaounion.model.domain.Categories;
import com.sunofbeaches.taobaounion.model.domain.HomePagerContent;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface Api {

    @GET("discovery/categories")
    Call<Categories> getCategories();

    @GET
    Call<HomePagerContent> getHomePageContent(@Url String url);
}
