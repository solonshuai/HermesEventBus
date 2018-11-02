package com.zxy.mytsfqxproject.recyclerView;

import android.view.View;

/**
 * 描述:RecyclerView的条目单击监听器
 *
 * @version 1.0
 */
public interface OnRecyclerItemClickListener<T> {
    /**
     * 列表条目被单击时
     *
     * @param v        当前条目的视图
     * @param item     当前条目对应的数据
     * @param position 当前条目在指定区域里面的位置
     */
    void onItemClick(View v, T item, int position);
}
