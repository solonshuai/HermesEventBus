package com.zxy.mytsfqxproject.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.zxy.mytsfqxproject.R;
import com.zxy.mytsfqxproject.Utils.Tools;
import com.zxy.mytsfqxproject.mvp.entity.RankFuddBean;
import com.zxy.mytsfqxproject.recyclerView.CommonRecycleAdapter;
import com.zxy.mytsfqxproject.recyclerView.CommonViewHolder;

import java.util.List;

public class RankAdapter extends CommonRecycleAdapter<RankFuddBean.ResultBean.DataBean> {
    private Context mContext;

    public RankAdapter(Context context) {
        super(context, R.layout.adapter_rank_fwdd);
        this.mContext = context;
    }

    @Override
    public void setDataList(List<RankFuddBean.ResultBean.DataBean> data) {
        super.setDataList(data);
    }

    @Override
    public void bindData(CommonViewHolder holder, RankFuddBean.ResultBean.DataBean item) {
        Glide.with(mContext).load(item.getCar_photo()).error(R.mipmap.image_placeholder).into((ImageView) holder.getView(R.id.imageView));
        holder.setText(R.id.tv_car_num, item.getCar_number())
                .setText(R.id.tv_username, item.getUsername())
                .setText(R.id.tv_money, "￥" + item.getOrder_amount());
        if (item.getGoods_name().size() > 0) {
            holder.setText(R.id.tv_fuwu, item.getGoods_name().get(0));
        } else if (item.getGoods_name().size() > 1) {
            holder.setText(R.id.tv_fuwu2, item.getGoods_name().get(1));
        } else if (item.getGoods_name().size() == 0) {
            holder.setViewVisibility(R.id.tv_fuwu, View.INVISIBLE)
                    .setViewVisibility(R.id.tv_fuwu2, View.INVISIBLE);
        }
        holder.setText(R.id.tv_beizhu, "嘱咐：" + item.getRemark());
        if (item.getClient_grade() == 10) {
            holder.setText(R.id.tv_level, "A级");
        } else if (item.getClient_grade() == 20) {
            holder.setText(R.id.tv_level, "B级");
        } else {
            holder.setText(R.id.tv_level, "C级");
        }
        holder.setText(R.id.tv_beizhu2, "预计交车时间：" + Tools.getTime(item.getExpect_complete_time(), "yyyy-MM-dd HH:mm:ss"))
                .setText(R.id.tv_jiedai, "接待：" + Tools.getTime(item.getCreate_time(), "yyyy-MM-dd HH:mm:ss"));
    }
}
