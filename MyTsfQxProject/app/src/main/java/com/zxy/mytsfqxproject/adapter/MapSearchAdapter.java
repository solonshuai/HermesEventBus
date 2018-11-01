package com.zxy.mytsfqxproject.adapter;

import android.content.Context;

import com.zxy.mytsfqxproject.R;
import com.zxy.mytsfqxproject.mvp.entity.LocationBean;
import com.zxy.mytsfqxproject.recyclerView.CommonRecycleAdapter;
import com.zxy.mytsfqxproject.recyclerView.CommonViewHolder;

public class MapSearchAdapter extends CommonRecycleAdapter<LocationBean> {
    private Context mContext;
    private boolean isOffer = false;

    public MapSearchAdapter(Context context) {
        super(context, R.layout.adapter_map_search);
        this.mContext = context;
    }

    @Override
    public void bindData(CommonViewHolder holder, LocationBean item) {
        holder.setText(R.id.tv_address, item.getAdd());
    }
}
