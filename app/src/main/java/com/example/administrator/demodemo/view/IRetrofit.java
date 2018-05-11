package com.example.administrator.demodemo.view;

import com.example.administrator.demodemo.model.ListGoodsBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface IRetrofit {

    @GET("product/getProducts")
    Call<ListGoodsBean> getlistgoods(@Query("pscid") int pscid);
}
