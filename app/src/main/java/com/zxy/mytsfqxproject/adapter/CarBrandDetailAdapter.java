package com.zxy.mytsfqxproject.adapter;

import android.content.Context;

import com.zxy.mytsfqxproject.R;
import com.zxy.mytsfqxproject.mvp.entity.CarBean;
import com.zxy.mytsfqxproject.recyclerView.CommonRecycleAdapter;
import com.zxy.mytsfqxproject.recyclerView.CommonViewHolder;

import java.util.List;

public class CarBrandDetailAdapter extends CommonRecycleAdapter<CarBean.ResultBean> {
    private Context mContext;

    public CarBrandDetailAdapter(Context context) {
        super(context, R.layout.adapter_item_popup);
        this.mContext = context;
    }

    @Override
    public void setDataList(List<CarBean.ResultBean> data) {
        super.setDataList(data);
    }

    @Override
    public void addDataList(List<CarBean.ResultBean> dataList) {
        super.addDataList(dataList);
    }

    @Override
    public void bindData(CommonViewHolder holder, CarBean.ResultBean item) {
        holder.setText(R.id.tv_item, item.getBrand_name());
    }

}