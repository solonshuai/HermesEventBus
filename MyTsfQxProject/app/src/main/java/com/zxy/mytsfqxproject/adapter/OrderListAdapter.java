package com.zxy.mytsfqxproject.adapter;

import android.content.Context;
import android.view.View;

import com.google.gson.JsonObject;
import com.zxy.mytsfqxproject.R;
import com.zxy.mytsfqxproject.Utils.Tools;
import com.zxy.mytsfqxproject.http.RetrofitManager;
import com.zxy.mytsfqxproject.inter.OnItemWheelView;
import com.zxy.mytsfqxproject.mvp.entity.OrdeListBean;
import com.zxy.mytsfqxproject.recyclerView.CommonRecycleAdapter;
import com.zxy.mytsfqxproject.recyclerView.CommonViewHolder;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderListAdapter extends CommonRecycleAdapter<OrdeListBean.ResultBean.DataBean> {
    private Context mContext;
    private OnItemWheelView listener;

    public OrderListAdapter(Context context) {
        super(context, R.layout.adapter_order_list);
        this.mContext = context;
    }

    public void setListener(OnItemWheelView listener) {
        this.listener = listener;
    }

    @Override
    public void setDataList(List<OrdeListBean.ResultBean.DataBean> data) {
        super.setDataList(data);
    }

    @Override
    public void bindData(CommonViewHolder holder, final OrdeListBean.ResultBean.DataBean item) {
        holder.setText(R.id.tv_state, item.getState_txt())
                .setText(R.id.tv_car_num, item.getCar_number())
                .setText(R.id.tv_name, item.getUsername())
                .setText(R.id.tv_level, item.getClient_grade())
                .setText(R.id.tv_fuwu, item.getServe_project())
                .setText(R.id.tv_time, Tools.getTime(item.getCreate_time(), "HH:mm"));
        holder.getView(R.id.tv_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RetrofitManager.INSTANCE.getService().cancelBespeak(item.getBespeak_id()).enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                        JSONObject jobj = null;
                        try {
                            jobj = new JSONObject(response.body().toString());
                            if (jobj.optInt("code") == 200) {
                                listener.onItemClick("");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<JsonObject> call, Throwable t) {

                    }
                });
            }
        });
    }
}
