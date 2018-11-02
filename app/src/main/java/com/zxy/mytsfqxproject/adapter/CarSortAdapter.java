package com.zxy.mytsfqxproject.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.zxy.mytsfqxproject.R;
import com.zxy.mytsfqxproject.mvp.entity.SortModel;
import com.zxy.mytsfqxproject.recyclerView.CommonRecycleAdapter;
import com.zxy.mytsfqxproject.recyclerView.CommonViewHolder;

import java.util.List;

public class CarSortAdapter extends CommonRecycleAdapter<SortModel> {
    private Context mContext;

    public CarSortAdapter(Context context) {
        super(context, R.layout.adapter_car_brand);
        this.mContext = context;
    }

    @Override
    public void setDataList(List<SortModel> data) {
        super.setDataList(data);
    }

    @Override
    public void addDataList(List<SortModel> dataList) {
        super.addDataList(dataList);
    }

    @Override
    public void bindData(CommonViewHolder holder, SortModel item) {
        holder.setText(R.id.tv_title, item.getName());
        Glide.with(mContext).load(item.getImgs())
                .into((ImageView) holder.getView(R.id.iv_car));
    }

    /**
     * 根据分类的首字母的Char ascii值获取其第一次出现该首字母的位置
     */
    public int getPositionForSection(int section) {
        for (int i = 0; i < getItemCount(); i++) {
            String sortStr = mData.get(i).getLetters();
            char firstChar = sortStr.toUpperCase().charAt(0);
            if (firstChar == section) {
                return i;
            }
        }
        return -1;
    }

}