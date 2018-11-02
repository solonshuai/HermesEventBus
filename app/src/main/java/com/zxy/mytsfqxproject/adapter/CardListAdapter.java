package com.zxy.mytsfqxproject.adapter;

import android.content.Context;

import com.zxy.mytsfqxproject.R;
import com.zxy.mytsfqxproject.mvp.entity.clientDetail;
import com.zxy.mytsfqxproject.recyclerView.CommonRecycleAdapter;
import com.zxy.mytsfqxproject.recyclerView.CommonViewHolder;

import java.util.List;

public class CardListAdapter extends CommonRecycleAdapter<clientDetail.ResultBean.CardListBean> {
    private Context mContext;

    public CardListAdapter(Context context) {
        super(context, R.layout.adapter_car_list);
        this.mContext = context;
    }

    @Override
    public void setDataList(List<clientDetail.ResultBean.CardListBean> data) {
        super.setDataList(data);
    }

    @Override
    public void addDataList(List<clientDetail.ResultBean.CardListBean> dataList) {
        super.addDataList(dataList);
    }

    @Override
    public void bindData(CommonViewHolder holder, clientDetail.ResultBean.CardListBean item) {
        holder.setText(R.id.tv_car_title, item.getCard_title() + "");
        holder.setText(R.id.car_detail, "余额￥" + item.getMoney());
    }
}
