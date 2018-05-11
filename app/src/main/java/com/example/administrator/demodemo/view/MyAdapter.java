package com.example.administrator.demodemo.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.demodemo.R;
import com.example.administrator.demodemo.model.ListGoodsBean;

import java.util.List;



public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<ListGoodsBean.DataBean> data;
    //判断条目的类型
    private int ONE = 0;
    private int TWO = 1;
    private OnItemClick onItemClick;

    public MyAdapter(Context context, List<ListGoodsBean.DataBean> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (viewType == ONE) {
            View oview = LayoutInflater.from(context).inflate(R.layout.one_item, parent, false);
            MyHolderone myHolderone = new MyHolderone(oview);
            return myHolderone;
        } else if (viewType == TWO) {
            View tview = LayoutInflater.from(context).inflate(R.layout.two_item, parent, false);
            MyHoldertwo myHoldertwo = new MyHoldertwo(tview);
            return myHoldertwo;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {

        if (holder instanceof MyHolderone) {

            ((MyHolderone) holder).oneText.setText(data.get(position).getTitle());
            String[] split = data.get(position).getImages().split("\\|");
            ((MyHolderone) holder).oneImg.setImageURI(split[0]);

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClick.OnClick(position);
                }
            });
        } else if (holder instanceof MyHoldertwo) {

            ((MyHoldertwo) holder).twoText.setText(data.get(position).getTitle());
            String[] split = data.get(position).getImages().split("\\|");
            ((MyHoldertwo) holder).twoImg.setImageURI(split[0]);

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClick.OnClick(position);
                }
            });
        }
    }

    @Override
    public int getItemViewType(int position) {

        if (position % 2 == 0) {
            return ONE;
        } else {
            return TWO;
        }

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setOnClickListener(OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }
}
