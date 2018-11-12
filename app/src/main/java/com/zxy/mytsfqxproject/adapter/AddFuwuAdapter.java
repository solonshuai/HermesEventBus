package com.zxy.mytsfqxproject.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.zxy.mytsfqxproject.R;
import com.zxy.mytsfqxproject.mvp.entity.AddFuwuBean;
import com.zxy.mytsfqxproject.recyclerView.CommonRecycleAdapter;
import com.zxy.mytsfqxproject.recyclerView.CommonViewHolder;
import com.zxy.mytsfqxproject.recyclerView.OnClickLister;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 检车订单 添加服务
 */
public class AddFuwuAdapter extends CommonRecycleAdapter<AddFuwuBean.ResultBean> {
    private Context mContext;
    private boolean isOffer = false;
    private OnClickLister onClickLister;

    public AddFuwuAdapter(Context context) {
        super(context, R.layout.adapter_addfuwu);
        this.mContext = context;
    }

    public void setOnClickLister(OnClickLister onClickLister) {
        this.onClickLister = onClickLister;
    }

    public void setOffer(boolean isOffer) {
        this.isOffer = isOffer;
    }

    @Override
    public void setDataList(List<AddFuwuBean.ResultBean> data) {
        Collections.sort(data, new Comparator<AddFuwuBean.ResultBean>() {
            @Override
            public int compare(AddFuwuBean.ResultBean o1, AddFuwuBean.ResultBean o2) {
                /**
                 * 返回负数表示：p1 小于p2，
                 * 返回0 表示：p1和p2相等，
                 * 返回正数表示：p1大于p2
                 */
                if (o1.getGoods_type() > o2.getGoods_type()) {
                    return 1;
                }
                if (o1.getGoods_type() == o2.getGoods_type()) {
                    return 0;
                }
                return -1;
            }
        });
        super.setDataList(data);
    }

    @Override
    public void bindData(final CommonViewHolder holder, final AddFuwuBean.ResultBean item) {
        holder.setText(R.id.tv_name, item.getGoods_name())
                .setText(R.id.tv_nos, item.getGoods_code())
                .setText(R.id.tv_nums, item.getGoods_num() + "")
                .setText(R.id.tv_price, "￥" + item.getGoods_saleprice())
                .setText(R.id.tv_received_num, "已领 : x" + item.getReceived_num());

        if (item.getGoods_type() == 10) {
            holder.setText(R.id.tv_type, "服务");
            holder.setViewVisibility(R.id.iv_edit, View.INVISIBLE);
            double value = item.getGoods_num() * Double.parseDouble(item.getGoods_saleprice());
            holder.setText(R.id.tv_totals, "￥" + value);
        } else {
            holder.setText(R.id.tv_type, "配件");
            holder.setViewVisibility(R.id.iv_edit, View.VISIBLE);
            if (item.getGoods_saleprice() == null) {
                holder.setText(R.id.tv_totals, "￥");
            } else {
                holder.setText(R.id.tv_totals, "￥" + item.getGoods_saleprice());
            }
        }
        if (isOffer) {
            holder.setViewVisibility(R.id.iv_edit, View.INVISIBLE)
                    .setViewVisibility(R.id.iv_trash, View.INVISIBLE);
        }
        holder.getView(R.id.iv_jia).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView tx = holder.getView(R.id.tv_nums);
                int num = Integer.valueOf(tx.getText().toString()) + 1;
                holder.setText(R.id.tv_nums, num + "");
                item.setGoods_num(num);
            }
        });
        holder.getView(R.id.iv_jian).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView tx = holder.getView(R.id.tv_nums);
                int num = Integer.valueOf(tx.getText().toString()) - 1;
                if (num > -1) {
                    holder.setText(R.id.tv_nums, num + "");
                    item.setGoods_num(num);
                }
            }
        });
        holder.getView(R.id.iv_edit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (item.isEdit()) {
                    holder.setViewVisibility(R.id.et_amount, View.INVISIBLE)
                            .setViewVisibility(R.id.iv_jian, View.INVISIBLE)
                            .setViewVisibility(R.id.iv_jia, View.INVISIBLE)
                            .setViewVisibility(R.id.tv_x, View.VISIBLE)
                            .setViewVisibility(R.id.tv_totals, View.VISIBLE);
                    holder.setText(R.id.tv_nums, item.getGoods_num() + "");
                    EditText et = holder.getView(R.id.et_amount);
                    if (!TextUtils.isEmpty(et.getText())) {
                        if (Integer.valueOf(et.getText().toString()) > 0) {
                            item.setGoods_amount(et.getText().toString());
                            holder.setText(R.id.tv_totals, "￥" + et.getText().toString());
                        }
                    }
                    item.setEdit(false);
                    onClickLister.onItemAddClick(item, holder.getPosition());
                } else {
                    holder.setViewVisibility(R.id.et_amount, View.VISIBLE)
                            .setViewVisibility(R.id.iv_jian, View.VISIBLE)
                            .setViewVisibility(R.id.iv_jia, View.VISIBLE)
                            .setViewVisibility(R.id.tv_x, View.INVISIBLE)
                            .setViewVisibility(R.id.tv_totals, View.GONE);
                    holder.setText(R.id.tv_nums, item.getGoods_num() + "");
                    item.setEdit(true);

                }
            }
        });
        holder.getView(R.id.iv_trash).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickLister.onItemRemoveClick(item, holder.getPosition());
            }
        });
    }
}
