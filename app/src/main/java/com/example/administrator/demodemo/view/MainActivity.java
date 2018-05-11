package com.example.administrator.demodemo.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.administrator.demodemo.R;
import com.example.administrator.demodemo.model.GoodsModel;
import com.example.administrator.demodemo.model.ListGoodsBean;
import com.example.administrator.demodemo.presenter.GoodsPresenter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements IMain {

    @BindView(R.id.main_rec)
    RecyclerView mainRec;
    @BindView(R.id.main_smart)
    SmartRefreshLayout mainSmart;

    private int pscid = 1;
    private GoodsPresenter goodsPresenter;
    private List<ListGoodsBean.DataBean> datap;
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initDatas();
        smart();
    }

    public void initDatas(){
        goodsPresenter = new GoodsPresenter();
        goodsPresenter.showtoView(new GoodsModel(),this);
    }

    public void smart(){

        mainSmart.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                pscid = 1;
                datap.clear();
                initDatas();
            }
        });

        mainSmart.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                pscid++;
                initDatas();
            }
        });

    }

    @Override
    public void showGoods(List<ListGoodsBean.DataBean> data) {
        this.datap = data;

        if (myAdapter == null){
            myAdapter = new MyAdapter(MainActivity.this,data);
            mainRec.setAdapter(myAdapter);
        }else {
            myAdapter.notifyDataSetChanged();
        }

        mainSmart.finishRefresh(2000);
        mainSmart.finishLoadMore(2000);

        mainRec.setLayoutManager(new LinearLayoutManager(MainActivity.this,LinearLayoutManager.VERTICAL,false));
        myAdapter = new MyAdapter(MainActivity.this,data);
        mainRec.setAdapter(myAdapter);

        myAdapter.setOnClickListener(new OnItemClick() {
            @Override
            public void OnClick(int position) {
                Intent intent = new Intent(MainActivity.this,RetrofitActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public int pscid() {
        return pscid;
    }
}
