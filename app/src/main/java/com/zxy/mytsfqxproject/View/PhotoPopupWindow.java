package com.zxy.mytsfqxproject.View;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.zxy.mytsfqxproject.R;
import com.zxy.mytsfqxproject.inter.OnChooseCameraListener;

/**
 * 拍照和相册的弹框
 */
public class PhotoPopupWindow extends PopupWindow {
    private final OnChooseCameraListener listener;

    public PhotoPopupWindow(Context mContext, View parent, OnChooseCameraListener onChooseCameraListener) {
        this.listener = onChooseCameraListener;
        View view = View.inflate(mContext, R.layout.item_photo_popup, null);
        LinearLayout ll_popup = view.findViewById(R.id.ll_popup);
        ll_popup.startAnimation(AnimationUtils.loadAnimation(mContext, R.anim.ease_push_bottom_in));
        setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        setBackgroundDrawable(new BitmapDrawable());
        setFocusable(true);
        setOutsideTouchable(true);
        setContentView(view);
        showAtLocation(parent, Gravity.BOTTOM, 0, 0);
        update();
        Button bt1 = view.findViewById(R.id.item_popupwindows_camera);
        Button bt2 = view.findViewById(R.id.item_popupwindows_Photo);
        Button bt3 = view.findViewById(R.id.item_popupwindows_cancel);
        bt1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {//拍照
                listener.fromCamera();
                dismiss();
            }
        });
        bt2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {//相册
                listener.fromAlbum();
                dismiss();
            }
        });
        bt3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dismiss();
            }
        });
    }
}
