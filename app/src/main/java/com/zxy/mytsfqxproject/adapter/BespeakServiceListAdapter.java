package com.zxy.mytsfqxproject.adapter;

import android.content.Context;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.zxy.mytsfqxproject.R;
import com.zxy.mytsfqxproject.http.UrlConstant;
import com.zxy.mytsfqxproject.mvp.entity.BespeakServiceBean;
import com.zxy.mytsfqxproject.recyclerView.CommonRecycleAdapter;
import com.zxy.mytsfqxproject.recyclerView.CommonViewHolder;
import com.zxy.mytsfqxproject.recyclerView.OnClickLister;

import java.util.List;

public class BespeakServiceListAdapter extends CommonRecycleAdapter<BespeakServiceBean.ResultBean> {
    private Context mContext;
    private OnClickLister<BespeakServiceBean.ResultBean> lister;

    public BespeakServiceListAdapter(Context context) {
        super(context, R.layout.adapter_bespeak_list);
        this.mContext = context;
    }

    public void setLister(OnClickLister<BespeakServiceBean.ResultBean> lister) {
        this.lister = lister;
    }

    @Override
    public void setDataList(List<BespeakServiceBean.ResultBean> data) {
        super.setDataList(data);
    }

    @Override
    public void bindData(final CommonViewHolder holder, final BespeakServiceBean.ResultBean item) {
        if (UrlConstant.index == 0) {
            holder.getView(R.id.tv_fuwu_name).setBackgroundResource(R.mipmap.img_orange_bg);
            UrlConstant.index = UrlConstant.index + 1;
        } else if (UrlConstant.index == 1) {
            holder.getView(R.id.tv_fuwu_name).setBackgroundResource(R.mipmap.img_bule_bg);
            UrlConstant.index = UrlConstant.index + 1;
        } else if (UrlConstant.index == 2) {
            holder.getView(R.id.tv_fuwu_name).setBackgroundResource(R.mipmap.img_red_bg);
            UrlConstant.index = 0;
        }
        holder.setText(R.id.tv_fuwu_name, item.getTitle());
        ((CheckBox) holder.getView(R.id.cb_check)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    lister.onItemAddClick(item, holder.getPosition());
                } else {
                    lister.onItemRemoveClick(item, holder.getPosition());
                }
            }
        });
    }
}
