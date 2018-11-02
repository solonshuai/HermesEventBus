package com.zxy.mytsfqxproject.adapter;

import android.content.Context;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.zxy.mytsfqxproject.R;
import com.zxy.mytsfqxproject.mvp.entity.FuwuBean;
import com.zxy.mytsfqxproject.recyclerView.CommonRecycleAdapter;
import com.zxy.mytsfqxproject.recyclerView.CommonViewHolder;
import com.zxy.mytsfqxproject.recyclerView.OnClickLister;

import java.util.List;

public class FuWuAdapter extends CommonRecycleAdapter<FuwuBean.ResultBean.DataBean> {
    private Context mContext;
    private OnClickLister<FuwuBean.ResultBean.DataBean> lister;

    public FuWuAdapter(Context context) {
        super(context, R.layout.adapter_fuwu);
        this.mContext = context;
    }

    public void setLister(OnClickLister<FuwuBean.ResultBean.DataBean> OnClickLister) {
        this.lister = OnClickLister;
    }

    @Override
    public void setDataList(List<FuwuBean.ResultBean.DataBean> data) {
        super.setDataList(data);
    }

    @Override
    public void addDataList(List<FuwuBean.ResultBean.DataBean> dataList) {
        super.addDataList(dataList);
    }

    @Override
    public void bindData(final CommonViewHolder holder, final FuwuBean.ResultBean.DataBean item) {
        holder.setText(R.id.tv_fuwuname, item.getProject_title());
        holder.setText(R.id.tv_money, "￥" + item.getProject_price());
        holder.setText(R.id.tv_fuwudsc, "编码:" + item.getProject_code());
        ((CheckBox) holder.getView(R.id.ck_istrue)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    item.setProject_num(1);
                    item.setProject_amount((int) Double.parseDouble(item.getProject_price()));
                    lister.onItemAddClick(item, holder.getPosition());
                } else {
                    lister.onItemRemoveClick(item, holder.getPosition());
                }
            }
        });
    }
}
