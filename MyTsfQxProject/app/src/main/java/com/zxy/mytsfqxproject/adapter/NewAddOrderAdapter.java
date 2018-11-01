package com.zxy.mytsfqxproject.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.zxy.mytsfqxproject.R;
import com.zxy.mytsfqxproject.mvp.entity.NewAddOrderBean;
import com.zxy.mytsfqxproject.recyclerView.CommonRecycleAdapter;
import com.zxy.mytsfqxproject.recyclerView.CommonViewHolder;

import java.util.List;

public class NewAddOrderAdapter extends CommonRecycleAdapter<NewAddOrderBean.ResultBean.DataBean> {
    private Context mContext;

    public NewAddOrderAdapter(Context context) {
        super(context, R.layout.adapter_new_order);
        this.mContext = context;
    }

    @Override
    public void setDataList(List<NewAddOrderBean.ResultBean.DataBean> data) {
        super.setDataList(data);
    }

    @Override
    public void bindData(CommonViewHolder holder, NewAddOrderBean.ResultBean.DataBean item) {
holder.setText(R.id.tv_car_num,item.getCar_number())
        .setText(R.id.tv_username,item.getUsername());
        Glide.with(mContext)
                .load(item.getBrand_logo())
                .into((ImageView) holder.getView(R.id.iv_car_icon));
    }
}
