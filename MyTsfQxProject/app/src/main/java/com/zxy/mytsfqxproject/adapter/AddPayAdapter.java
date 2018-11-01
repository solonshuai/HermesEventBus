package com.zxy.mytsfqxproject.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.zxy.mytsfqxproject.R;
import com.zxy.mytsfqxproject.View.ShowWheelView;
import com.zxy.mytsfqxproject.inter.OnItemWheelView;
import com.zxy.mytsfqxproject.mvp.entity.AddPaymentView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddPayAdapter extends RecyclerView.Adapter<AddPayAdapter.ViewHolder> {
    private Context context;
    private ArrayList<String> showTitle = new ArrayList<>();
    private List<AddPaymentView.ResultBean> mDatas = new ArrayList<>();
    private Map<String, Object> setShowValue = new HashMap<>();

    public AddPayAdapter(Context context) {
        this.context = context;
    }
    public void setShowTitle(ArrayList<String> showTitle) {
        this.showTitle = showTitle;
    }

    public void setShowValue(HashMap<String, Object> setShowValue) {
        this.setShowValue = setShowValue;
    }

    public void setDataList(List<AddPaymentView.ResultBean> data) {
        this.mDatas = data;
        notifyDataSetChanged();
    }

    public void addDataList(AddPaymentView.ResultBean dataList) {
        this.mDatas.add(dataList);
        notifyItemRangeInserted(mDatas.size() - 1, 1);
        compatibilityDataSizeChanged();
    }

    private void compatibilityDataSizeChanged() {
        final int dataSize = mDatas == null ? 0 : mDatas.size();
        if (dataSize == 1) {
            notifyDataSetChanged();
        }
    }

    public List<AddPaymentView.ResultBean> getDatas() {
        return this.mDatas;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_add_pay, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        final AddPaymentView.ResultBean data = mDatas.get(position);
        holder.tv_choose_type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowWheelView.show(context, showTitle, new OnItemWheelView() {
                    @Override
                    public void onItemClick(String str) {
                        data.setPay_title(str);
                        data.setPay_id((int) setShowValue.get(str));
                        notifyItemChanged(position, data);
                    }
                });
            }
        });
        holder.iv_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatas.remove(position);
                notifyDataSetChanged();
            }
        });
        holder.et_choose_money.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (TextUtils.isEmpty(holder.et_choose_money.getText().toString())) return;
                    data.setAmount(holder.et_choose_money.getText().toString());
                }
            }
        });
        if (data.getPay_title() != null && !data.getPay_title().equals("")) {
            holder.tv_choose_type.setText(data.getPay_title());
            holder.et_choose_money.setText(data.getAmount());
        }
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_choose_type;
        private EditText et_choose_money;
        private ImageView iv_del;

        ViewHolder(View itemView) {
            super(itemView);
            tv_choose_type = itemView.findViewById(R.id.tv_choose_type);
            et_choose_money = itemView.findViewById(R.id.et_choose_money);
            iv_del = itemView.findViewById(R.id.iv_del);
        }
    }

}
