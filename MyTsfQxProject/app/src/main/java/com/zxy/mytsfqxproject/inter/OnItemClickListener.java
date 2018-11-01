package com.zxy.mytsfqxproject.inter;

import java.util.List;

public interface OnItemClickListener<T> {
    void onItemClick(String str, List<T> mDatas);
}
