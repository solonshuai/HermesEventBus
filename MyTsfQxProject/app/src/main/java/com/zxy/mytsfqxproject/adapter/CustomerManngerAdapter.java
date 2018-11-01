package com.zxy.mytsfqxproject.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.zxy.mytsfqxproject.R;
import com.zxy.mytsfqxproject.mvp.entity.ChooseCustomer;
import com.zxy.mytsfqxproject.recyclerView.CommonRecycleAdapter;
import com.zxy.mytsfqxproject.recyclerView.CommonViewHolder;

import java.util.List;

public class CustomerManngerAdapter extends CommonRecycleAdapter<ChooseCustomer.ResultBean.DataBean> {
    private Context mContext;

    public CustomerManngerAdapter(Context context) {
        super(context, R.layout.adapter_customer_manager);
        this.mContext = context;
    }

    @Override
    public void setDataList(List<ChooseCustomer.ResultBean.DataBean> data) {
        super.setDataList(data);
    }

    @Override
    public void addDataList(List<ChooseCustomer.ResultBean.DataBean> dataList) {
        super.addDataList(dataList);
    }

    @Override
    public void bindData(CommonViewHolder holder, ChooseCustomer.ResultBean.DataBean item) {
        holder.setText(R.id.tv_name, item.getUsername());
        holder.setText(R.id.tv_phone, item.getCar_number());
        if (item.getClient_grade() == 10) {
            holder.setText(R.id.tv_level, "A级");
        } else if (item.getClient_grade() == 20) {
            holder.setText(R.id.tv_level, "B级");
        } else if (item.getClient_grade() == 30) {
            holder.setText(R.id.tv_level, "C级");
        }

    }
}
