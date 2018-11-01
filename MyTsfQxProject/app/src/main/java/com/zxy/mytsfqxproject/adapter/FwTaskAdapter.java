package com.zxy.mytsfqxproject.adapter;

import android.content.Context;
import android.view.View;

import com.zxy.mytsfqxproject.R;
import com.zxy.mytsfqxproject.mvp.entity.AddFuwuBean;
import com.zxy.mytsfqxproject.recyclerView.CommonRecycleAdapter;
import com.zxy.mytsfqxproject.recyclerView.CommonViewHolder;

import java.util.List;

/**
 * 派工
 */
public class FwTaskAdapter extends CommonRecycleAdapter<AddFuwuBean.ResultBean> {
    private Context mContext;

    public FwTaskAdapter(Context context) {
        super(context, R.layout.adapter_fw_task);
        this.mContext = context;
    }

    @Override
    public void setDataList(List<AddFuwuBean.ResultBean> data) {
        super.setDataList(data);
    }

    @Override
    public void bindData(CommonViewHolder holder, AddFuwuBean.ResultBean item) {
        holder.setText(R.id.tv_name, item.getGoods_name())
                .setText(R.id.tv_no, item.getGoods_name())
                .setText(R.id.tv_nums, item.getBuilder_user());
    }
}
