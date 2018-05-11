package com.example.administrator.demodemo.presenter;

import com.example.administrator.demodemo.model.GoodsListener;
import com.example.administrator.demodemo.model.IModel;
import com.example.administrator.demodemo.model.ListGoodsBean;
import com.example.administrator.demodemo.util.ApiUtil;
import com.example.administrator.demodemo.view.IMain;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class GoodsPresenter implements IPresenter {
    @Override
    public void showtoView(IModel iModel, final IMain iMain) {

        Map<String, String> gmap = new HashMap<>();
        gmap.put("pscid",String.valueOf(iMain.pscid()));

        iModel.getGoods(ApiUtil.goods_url, gmap, new GoodsListener() {
            @Override
            public void goodsSuccess(String json) {

                ListGoodsBean listGoodsBean = new Gson().fromJson(json, ListGoodsBean.class);
                List<ListGoodsBean.DataBean> data = listGoodsBean.getData();

                iMain.showGoods(data);
            }

            @Override
            public void goodsError(String error) {

            }
        });
    }
}
