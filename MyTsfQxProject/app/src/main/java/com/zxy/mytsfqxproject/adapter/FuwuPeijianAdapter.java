package com.zxy.mytsfqxproject.adapter;

import android.content.Context;
import android.view.View;
import android.widget.EditText;

import com.zxy.mytsfqxproject.R;
import com.zxy.mytsfqxproject.mvp.entity.PeiJianBean;
import com.zxy.mytsfqxproject.recyclerView.CommonRecycleAdapter;
import com.zxy.mytsfqxproject.recyclerView.CommonViewHolder;
import com.zxy.mytsfqxproject.recyclerView.OnClickLister;

import java.util.List;

public class FuwuPeijianAdapter extends CommonRecycleAdapter<PeiJianBean.ResultBean.DataBean> {
    private Context mContext;
    private OnClickLister<PeiJianBean.ResultBean.DataBean> lister;

    public FuwuPeijianAdapter(Context context) {
        super(context, R.layout.adapter_fuwupeijian);
        this.mContext = context;
    }

    @Override
    public void setDataList(List<PeiJianBean.ResultBean.DataBean> data) {
        super.setDataList(data);
    }

    public void setLister(OnClickLister<PeiJianBean.ResultBean.DataBean> OnClickLister) {
        this.lister = OnClickLister;
    }

    @Override
    public void addDataList(List<PeiJianBean.ResultBean.DataBean> dataList) {
        super.addDataList(dataList);
    }

    @Override
    public void bindData(final CommonViewHolder holder, final PeiJianBean.ResultBean.DataBean item) {
        holder.setText(R.id.tv_peiianTitle, item.getMaterial_name())
                .setText(R.id.tv_des, "编码:" + item.getMaterial_code())
                .setText(R.id.tv_money, "￥" + item.getSale_price());
        holder.getView(R.id.iv_jia).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.getView(R.id.iv_jian).setVisibility(View.VISIBLE);
                holder.getView(R.id.et_num).setVisibility(View.VISIBLE);
                int value = Integer.parseInt(String.valueOf(((EditText) holder.getView(R.id.et_num)).getText())) + 1;
                ((EditText) holder.getView(R.id.et_num)).setText(String.valueOf(value));
                item.setMaterial_num(value);
                double dd = Double.parseDouble(item.getSale_price()) * value;
                item.setMaterial_amount((int) dd);
                item.setMaterial_price((int) Double.parseDouble(item.getSale_price()));
                lister.onItemAddClick(item, holder.getPosition());
            }
        });
        holder.getView(R.id.iv_jian).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int value = Integer.parseInt(String.valueOf(((EditText) holder.getView(R.id.et_num)).getText()));
                if (value >= 1) {
                    value = value - 1;
                    ((EditText) holder.getView(R.id.et_num)).setText(String.valueOf(value));
                    item.setMaterial_num(value);
                    double dd = Double.parseDouble(item.getSale_price()) * value;
                    item.setMaterial_amount((int) dd);
                    item.setMaterial_price((int) Double.parseDouble(item.getSale_price()));
                    lister.onItemRemoveClick(item, holder.getPosition());
                }
            }
        });
    }
}
