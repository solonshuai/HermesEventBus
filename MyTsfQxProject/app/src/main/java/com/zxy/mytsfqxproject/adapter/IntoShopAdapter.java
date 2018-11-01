package com.zxy.mytsfqxproject.adapter;

import android.content.Context;

import com.zxy.mytsfqxproject.R;
import com.zxy.mytsfqxproject.Utils.Tools;
import com.zxy.mytsfqxproject.mvp.entity.IntoShopBean;
import com.zxy.mytsfqxproject.recyclerView.CommonRecycleAdapter;
import com.zxy.mytsfqxproject.recyclerView.CommonViewHolder;

import java.util.List;

public class IntoShopAdapter extends CommonRecycleAdapter<IntoShopBean.ResultBean.RepairListBean> {
    private Context mContext;

    public IntoShopAdapter(Context context) {
        super(context, R.layout.adapter_into_shop);
        this.mContext = context;
    }

    @Override
    public void setDataList(List<IntoShopBean.ResultBean.RepairListBean> data) {
        super.setDataList(data);
    }

    @Override
    public void bindData(CommonViewHolder holder, IntoShopBean.ResultBean.RepairListBean item) {
        holder.setText(R.id.tv_times, Tools.getTime(item.getCreate_time(), "yyyy-MM-dd"))
                .setText(R.id.tv_money, "￥" + item.getOrder_amount());
        String str = "";
        for (int i = 0; i < item.getGoods_list().size(); i++) {
            str = str + (i+1) + " " + item.getGoods_list().get(i) + "\n";
        }
        holder.setText(R.id.tv_items, str);
    }
}
