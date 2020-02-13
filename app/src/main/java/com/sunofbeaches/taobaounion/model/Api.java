package com.sunofbeaches.taobaounion.model;

import com.sunofbeaches.taobaounion.model.domain.Categories;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {

    @GET("discovery/categories")
    Call<Categories> getCategories();
}
