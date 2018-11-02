package com.zxy.mytsfqxproject.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zxy.mytsfqxproject.R;
import com.zxy.mytsfqxproject.Utils.Tools;
import com.zxy.mytsfqxproject.mvp.entity.GcglBean;
import com.zxy.mytsfqxproject.recyclerView.CommonRecycleAdapter;
import com.zxy.mytsfqxproject.recyclerView.CommonViewHolder;

import java.util.List;

public class ProcessAdapter extends CommonRecycleAdapter<GcglBean.ResultBean> {
    private Context mContext;


    public ProcessAdapter(Context context) {
        super(context, R.layout.adapter_process);
        this.mContext = context;
    }

    @Override
    public void setDataList(List<GcglBean.ResultBean> data) {
        super.setDataList(data);
    }

    @Override
    public void bindData(CommonViewHolder holder, GcglBean.ResultBean item) {
        Glide.with(mContext).load(item.getBrand_logo()).into((ImageView) holder.getView(R.id.iv_img));
        holder.setText(R.id.tv_car_name, item.getCar_number())
                .setText(R.id.tv_username, item.getUsername())
                .setText(R.id.tv_creat_time, "开始时间：" + Tools.getTime(item.getCreate_time(), "HH:mm"))
                .setText(R.id.tv_jieche_time, "预计交车时间：" + Tools.getTime(item.getCreate_time(), "yyyy-mm-dd HH:mm"))
                .setText(R.id.tv_state, item.getRepair_state());
        if (item.getGoods_name().size() > 0) {
            LinearLayout linear = holder.getView(R.id.layout_fuwu);
            for (int i = 0; i < item.getGoods_name().size(); i++) {
                if (i >= 5) return;
                TextView tv = new TextView(mContext);
                tv.setText(item.getGoods_name().get(i));
                tv.setBackgroundResource(R.drawable.shape_gary);
                tv.setPadding(5, 0, 5, 0);
                linear.addView(tv);
                TextView tv1 = new TextView(mContext);
                tv1.setText(" ");
                linear.addView(tv1);
            }

        }
    }
}
