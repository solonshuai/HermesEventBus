package com.zxy.mytsfqxproject.View;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.zxy.mytsfqxproject.R;

/**
 * 该自定义Dialog应用在：弹出框居中显示图片，点击其他区域自动关闭Dialog
 */
public class CustomPopDialog2 extends Dialog {
    public CustomPopDialog2(Context context) {
        super(context);
    }

    public CustomPopDialog2(Context context, int theme) {
        super(context, theme);
    }

    public static class Builder {
        private Context context;
        private Bitmap image = null;
        private String imags = null;

        public Builder(Context context) {
            this.context = context;
        }

        public Bitmap getImage() {
            return image;
        }

        public void setImage(Bitmap image) {
            this.image = image;
        }

        public String getImags() {
            return imags;
        }

        public void setImags(String imags) {
            this.imags = imags;
        }

        public CustomPopDialog2 create() {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final CustomPopDialog2 dialog = new CustomPopDialog2(context, R.style.MyDialog);
            View layout = inflater.inflate(R.layout.pop_window_img, null);
            dialog.addContentView(layout, new ViewGroup.LayoutParams(
                    android.view.ViewGroup.LayoutParams.WRAP_CONTENT
                    , android.view.ViewGroup.LayoutParams.WRAP_CONTENT));
            dialog.setContentView(layout);
            ImageView img = layout.findViewById(R.id.image);
            if (image != null) {
                img.setImageBitmap(getImage());
            } else if (imags != null) {
                Glide.with(context).load(imags).into(img);
            }
            dialog.setCanceledOnTouchOutside(true);
            return dialog;
        }
    }
}
