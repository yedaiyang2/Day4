package com.example.administrator.demodemo.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.administrator.demodemo.R;
import com.example.administrator.demodemo.model.ListGoodsBean;
import com.example.administrator.demodemo.util.ApiService;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitActivity extends AppCompatActivity {
    @BindView(R.id.retro_rec)
    RecyclerView retroRec;
    @BindView(R.id.retro_smart)
    SmartRefreshLayout retroSmart;
    private int pscid = 1;
    private MyAdapter myAdapter;
    private List<ListGoodsBean.DataBean> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);
        ButterKnife.bind(this);

        initDatas();
        smart();
    }

    public void initDatas(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiService.Base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        IRetrofit iRetrofit = retrofit.create(IRetrofit.class);

        Call<ListGoodsBean> getlistgoods = iRetrofit.getlistgoods(pscid);

        getlistgoods.enqueue(new Callback<ListGoodsBean>() {

            @Override
            public void onResponse(Call<ListGoodsBean> call, Response<ListGoodsBean> response) {
                if (response.isSuccessful()){
                    ListGoodsBean listGoodsBean = response.body();
                    data = listGoodsBean.getData();
                    retroRec.setLayoutManager(new LinearLayoutManager(RetrofitActivity.this,LinearLayoutManager.VERTICAL,false));
                    myAdapter = new MyAdapter(RetrofitActivity.this, data);
                    retroRec.setAdapter(myAdapter);
                }
            }

            @Override
            public void onFailure(Call<ListGoodsBean> call, Throwable t) {

            }
        });
    }

    public void smart(){

        retroSmart.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                pscid = 1;
                data.clear();
                initDatas();
                retroSmart.finishRefresh(1000);

            }
        });

        retroSmart.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                pscid++;
                initDatas();
                retroSmart.finishLoadMore(1000);
            }
        });

    }
}
