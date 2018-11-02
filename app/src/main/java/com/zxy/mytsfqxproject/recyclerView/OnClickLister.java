package com.zxy.mytsfqxproject.recyclerView;

public interface OnClickLister<T> {
    void onItemAddClick( T item, int position);

    void onItemRemoveClick( T item, int position);
}
