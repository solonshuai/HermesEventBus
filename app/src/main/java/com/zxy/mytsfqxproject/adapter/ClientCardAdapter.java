package com.zxy.mytsfqxproject.adapter;

import android.content.Context;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.zxy.mytsfqxproject.R;
import com.zxy.mytsfqxproject.inter.OnItemClickListener;
import com.zxy.mytsfqxproject.mvp.entity.ClientCardBean;
import com.zxy.mytsfqxproject.recyclerView.CommonRecycleAdapter;
import com.zxy.mytsfqxproject.recyclerView.CommonViewHolder;

import java.util.List;

public class ClientCardAdapter extends CommonRecycleAdapter<ClientCardBean.ResultBean> {
    private Context context;
    private OnItemClickListener listener;

    public ClientCardAdapter(Context context) {
        super(context, R.layout.adapter_client_car);
        this.context = context;
    }

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void setDataList(List<ClientCardBean.ResultBean> data) {
        super.setDataList(data);
    }

    @Override
    public void bindData(CommonViewHolder holder, final ClientCardBean.ResultBean item) {
        holder.setText(R.id.tv_card_type, item.getCard_title())
                .setText(R.id.tv_money, item.getMoney());
        ((CheckBox) holder.getView(R.id.iv_choose)).setChecked(item.isCheck());
        ((CheckBox) holder.getView(R.id.iv_choose)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                item.setCheck(isChecked);
                listener.onItemClick("", getmData());
            }
        });
    }
}
