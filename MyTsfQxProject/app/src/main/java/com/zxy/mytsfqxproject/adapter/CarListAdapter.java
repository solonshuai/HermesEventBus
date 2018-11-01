package com.zxy.mytsfqxproject.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.zxy.mytsfqxproject.R;
import com.zxy.mytsfqxproject.mvp.entity.ChooseCustomer;
import com.zxy.mytsfqxproject.mvp.entity.clientDetail;
import com.zxy.mytsfqxproject.recyclerView.CommonRecycleAdapter;
import com.zxy.mytsfqxproject.recyclerView.CommonViewHolder;

import java.util.List;

public class CarListAdapter extends CommonRecycleAdapter<clientDetail.ResultBean.CarListBean> {
    private Context mContext;

    public CarListAdapter(Context context) {
        super(context, R.layout.adapter_car_list);
        this.mContext = context;
    }

    @Override
    public void setDataList(List<clientDetail.ResultBean.CarListBean> data) {
        super.setDataList(data);
    }

    @Override
    public void addDataList(List<clientDetail.ResultBean.CarListBean> dataList) {
        super.addDataList(dataList);
    }

    @Override
    public void bindData(CommonViewHolder holder, clientDetail.ResultBean.CarListBean item) {
        holder.setText(R.id.tv_car_title, item.getCar_number() + "");
        holder.setText(R.id.car_detail, "历史共进店" + item.getRepair_count() + "次");
        Glide.with(mContext).load(item.getBrand_logo())
                .into((ImageView) holder.getView(R.id.iv_car));
    }
}
