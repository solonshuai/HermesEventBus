package com.zxy.mytsfqxproject.recyclerView;

import android.content.Context;
import android.content.res.ColorStateList;
import android.net.Uri;
import android.support.annotation.ColorInt;
import android.support.annotation.IdRes;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class CommonViewHolder extends RecyclerView.ViewHolder {
    private SparseArray<View> viewSparseArray;

    public CommonViewHolder(View itemView) {
        super(itemView);
        viewSparseArray = new SparseArray<>();
    }

    /**
     * 根据 ID 来获取 View
     *
     * @param viewId viewID
     * @param <T>    泛型
     * @return 将结果强转为 View 或 View 的子类型
     */
    public <T extends View> T getView(int viewId) {
        // 先从缓存中找，找打的话则直接返回
        // 如果找不到则 findViewById ，再把结果存入缓存中
        View view = viewSparseArray.get(viewId);
        if (view == null) {
            view = itemView.findViewById(viewId);
            viewSparseArray.put(viewId, view);
        }
        return (T) view;
    }

    public CommonViewHolder setText(int viewId, CharSequence text) {
        TextView tv = getView(viewId);
        tv.setText(text);
        return this;
    }


    public CommonViewHolder setViewVisibility(int viewId, int visibility) {
        getView(viewId).setVisibility(visibility);
        return this;
    }


    public CommonViewHolder setImageResource(int viewId, int resourceId) {
        ImageView imageView = getView(viewId);
        imageView.setImageResource(resourceId);
        return this;
    }

    public CommonViewHolder setBackgroundResource(int viewId, int resourceId) {
        getView(viewId).setBackgroundResource(resourceId);
        return this;
    }

    public CommonViewHolder setTextColor(@IdRes int viewId, @ColorInt int textColor) {
        TextView view = getView(viewId);
        view.setTextColor(textColor);
        return this;
    }

    public CommonViewHolder setTextColor(@IdRes int viewId, ColorStateList textColor) {
        TextView view = getView(viewId);
        view.setTextColor(textColor);
        return this;
    }
}
