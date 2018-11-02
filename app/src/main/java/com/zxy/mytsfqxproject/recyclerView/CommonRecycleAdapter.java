package com.zxy.mytsfqxproject.recyclerView;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public abstract class CommonRecycleAdapter<T> extends RecyclerView.Adapter<CommonViewHolder> {
    protected LayoutInflater layoutInflater;
    protected OnRecyclerItemClickListener<T> mOnRecyclerItemClickListener;
    protected List<T> mData = new ArrayList<>();

    protected int layoutId;

    protected MultiTypeSupport<T> multiTypeSupport;

    public CommonRecycleAdapter(Context context, int layoutId) {
        this.layoutInflater = LayoutInflater.from(context);
        this.layoutId = layoutId;
    }

    public CommonRecycleAdapter(Context context, List<T> dataList, int layoutId) {
        this.layoutInflater = LayoutInflater.from(context);
        this.mData = dataList;
        this.layoutId = layoutId;
    }

    public List<T> getmData() {
        return mData;
    }

    public void setDataList(List<T> data) {
        this.mData = data == null ? new ArrayList<T>() : data;
        notifyDataSetChanged();
    }

    public void addDataList(List<T> dataList) {
        mData.addAll(dataList);
        notifyItemRangeInserted(mData.size() - dataList.size(), dataList.size());
        compatibilityDataSizeChanged(dataList.size());
    }

    private void compatibilityDataSizeChanged(int size) {
        final int dataSize = mData == null ? 0 : mData.size();
        if (dataSize == size) {
            notifyDataSetChanged();
        }
    }

    /**
     * 获取列表条目单击监听器
     *
     * @return
     */
    public OnRecyclerItemClickListener getOnRecyclerItemClickListener() {
        return mOnRecyclerItemClickListener;
    }

    /**
     * 设置列表条目单击监听器
     *
     * @param onRecyclerItemClickListener
     */
    public void setOnRecyclerItemClickListener(OnRecyclerItemClickListener onRecyclerItemClickListener) {
        mOnRecyclerItemClickListener = onRecyclerItemClickListener;
    }

    @Override
    public int getItemViewType(int position) {
        if (multiTypeSupport != null) {
            return multiTypeSupport.getLayoutId(mData.get(position), position);
        }
        return super.getItemViewType(position);
    }

    //    @Override
//    public long getItemId(int position) {
//        return super.getItemId(position);
//    }
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public CommonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (multiTypeSupport != null) {
            layoutId = viewType;
        }
        View itemView = layoutInflater.inflate(layoutId, parent, false);
        return new CommonViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CommonViewHolder holder, int position) {
        final int p = position;
        final T item = mData.get(p);
        bindData(holder, item);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnRecyclerItemClickListener != null) {
                    mOnRecyclerItemClickListener.onItemClick(v, item, p);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if (mData == null) {
            return 0;
        }
        return mData.size();
    }

    public abstract void bindData(CommonViewHolder holder, T item);
}
