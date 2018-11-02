package com.zxy.mytsfqxproject.adapter;

import android.content.Context;

import com.zxy.mytsfqxproject.R;
import com.zxy.mytsfqxproject.mvp.entity.PhoneList;
import com.zxy.mytsfqxproject.recyclerView.CommonRecycleAdapter;
import com.zxy.mytsfqxproject.recyclerView.CommonViewHolder;

import java.util.List;

public class PhoneListAdapter extends CommonRecycleAdapter<PhoneList> {
    private Context mContext;

    public PhoneListAdapter(Context context) {
        super(context, R.layout.adapter_item_popup);
        this.mContext = context;
    }

    @Override
    public void setDataList(List<PhoneList> data) {
        super.setDataList(data);
    }

    @Override
    public void addDataList(List<PhoneList> dataList) {
        super.addDataList(dataList);
    }

    @Override
    public void bindData(CommonViewHolder holder, PhoneList item) {
        holder.setText(R.id.tv_item, item.getName())
                .setText(R.id.tv_item2, item.getNumber());
    }
}
