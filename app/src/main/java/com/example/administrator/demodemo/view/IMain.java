package com.example.administrator.demodemo.view;

import com.example.administrator.demodemo.model.ListGoodsBean;

import java.util.List;



public interface IMain {

    void showGoods(List<ListGoodsBean.DataBean> data);

    int pscid();
}
