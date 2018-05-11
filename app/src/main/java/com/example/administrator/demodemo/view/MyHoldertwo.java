package com.example.administrator.demodemo.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import butterknife.BindView;
import butterknife.ButterKnife;
import wby.laowang.threetest.R;

public class MyHoldertwo extends RecyclerView.ViewHolder {

    @BindView(R.id.two_text)
    TextView twoText;
    @BindView(R.id.two_img)
    SimpleDraweeView twoImg;

    public MyHoldertwo(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }
}
