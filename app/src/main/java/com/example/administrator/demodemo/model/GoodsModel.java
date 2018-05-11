package com.example.administrator.demodemo.model;

import com.example.administrator.demodemo.util.HttpUtils;

import java.util.Map;




public class GoodsModel implements IModel {
    @Override
    public void getGoods(String url, Map<String, String> map, final GoodsListener goodsListener) {

        HttpUtils httpUtils = HttpUtils.getHttpUtils();
        httpUtils.okPost(url,map);
        httpUtils.setOkLoadListener(new HttpUtils.OkLoadListener() {
            @Override
            public void okLoadSuccess(String json) {
                goodsListener.goodsSuccess(json);
            }

            @Override
            public void okLoadError(String error) {
                goodsListener.goodsError(error);
            }
        });
    }
}
