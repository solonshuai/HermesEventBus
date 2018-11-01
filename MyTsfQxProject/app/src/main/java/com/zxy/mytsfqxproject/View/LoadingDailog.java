package com.zxy.mytsfqxproject.View;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.zxy.mytsfqxproject.R;

public class LoadingDailog {
    private static Dialog pd;

    public static void show(Context context) {
        show(context, true);
    }

    public static void show(Context context, boolean cancelable) {
        try {
            dismiss();
            //拿到dialog
            pd = getLoadingDialog(context);
            //设置点击外围是否消失
            pd.setCancelable(cancelable);
            pd.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //这里的加载效果自己随意定义

    public static Dialog getLoadingDialog(Context context) {
        Dialog loading_dialog = new Dialog(context, R.style.MyDialog);
        View view = LayoutInflater.from(context).inflate(R.layout.layout_loading, null);
        loading_dialog.setContentView(view);
        loading_dialog.getWindow().setBackgroundDrawableResource(R.color.color_white);
        loading_dialog.setCancelable(false);
        return loading_dialog;
    }


    //在这里直接做了判断，大胆的放心的使用，保证不会出现空指针
    public static void dismiss() {
        try {
            if (pd != null && pd.isShowing()) {
                pd.dismiss();
            }
            pd = null;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            pd = null;
        }
    }


    public static boolean isShowing() {
        try {
            if (pd != null) {
                return pd.isShowing();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 设置dialog点击外部是否可以消失
     */
    public static void setCanceledOnTouchOutside(boolean b) {
        try {
            if (pd != null && pd.isShowing()) {
                pd.setCanceledOnTouchOutside(b);
            }
        } catch (Exception e) {
            // TODO: handle exception
        }

    }
}
