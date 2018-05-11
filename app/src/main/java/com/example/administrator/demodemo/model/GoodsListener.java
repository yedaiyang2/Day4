package com.example.administrator.demodemo.model;

public interface GoodsListener {

    void goodsSuccess(String json);
    void goodsError(String error);
}
