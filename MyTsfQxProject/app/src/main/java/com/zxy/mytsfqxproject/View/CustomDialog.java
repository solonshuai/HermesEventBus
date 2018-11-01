package com.zxy.mytsfqxproject.View;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Point;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zxy.mytsfqxproject.R;


/**
 * 仿苹果的弹框
 */

public class CustomDialog extends Dialog {
    private static CustomDialog dialog = null;

    public CustomDialog(Activity context) {
        super(context);
    }

    public CustomDialog(Activity context, int theme) {
        super(context, theme);
    }


    public static class Builder {
        boolean ishidd = false;
        boolean isOutDiss = false;
        boolean isBack = true;
        int dialogwidth;
        private Activity context;
        private View title;
        private int isDefaultTitel = -1;
        private CharSequence message;
        private String positiveButtonText;
        private String negativeButtonText;
        private View contentView, divideLine;
        public LinearLayout bt_botm_view;
        private OnClickListener positiveButtonClickListener;
        private OnClickListener negativeButtonClickListener;

        public Builder(Activity context) {
            WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            Display display = wm.getDefaultDisplay();
            Point size = new Point();
            display.getSize(size);
            this.dialogwidth = (int) (size.x * 0.9);
            this.context = context;
        }

        public Builder setMessage(CharSequence message) {
            this.message = message;
            return this;
        }

        //是否要隐藏确定和取消键
        public void setishidd(boolean ishidd) {
            this.ishidd = ishidd;
        }

        //点击屏幕是否隐藏
        public void setDialogOut(boolean isout) {
            this.isOutDiss = isout;
        }

        //点击手机返回键
        public void setCancelable(boolean isBack) {
            this.isBack = isBack;
        }

        /**
         * Set the Dialog message from resource
         *
         * @param
         * @return
         */
        public Builder setMessage(int message) {
            this.message = context.getText(message);
            return this;
        }

        /**
         * Set the Dialog title from String
         *
         * @param title
         * @return
         */
        public void setTitle(View title) {
            this.title = title;
        }

        public void setTitle(String title) {
            TextView textView = new TextView(context);
            textView.setTextSize(16);
            textView.setGravity(Gravity.CENTER);
            textView.setTextColor(Color.parseColor("#000000"));
            textView.setText(title);
            this.title = textView;
        }

        public Builder setTitle(int title) {
            this.isDefaultTitel = title;
            return this;
        }

        public Builder setContentView(View v) {
            this.contentView = v;
            return this;
        }

        /**
         * Set the positive button resource and it's listener
         *
         * @param positiveButtonText
         * @return
         */
        public Builder setPositiveButton(int positiveButtonText,
                                         OnClickListener listener) {
            this.positiveButtonText = (String) context
                    .getText(positiveButtonText);
            this.positiveButtonClickListener = listener;
            return this;
        }

        public Builder setPositiveButton(String positiveButtonText,
                                         final OnClickListener listener) {
            this.positiveButtonText = positiveButtonText;

            this.positiveButtonClickListener = new OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (listener != null)
                        listener.onClick(dialog, which);
                    dialog.dismiss();
                }
            };

            return this;
        }

        public Builder setNegativeButton(int negativeButtonText,
                                         final OnClickListener listener) {
            this.negativeButtonText = (String) context
                    .getText(negativeButtonText);

            this.negativeButtonClickListener = new OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (listener != null)
                        listener.onClick(dialog, which);
                    dialog.dismiss();
                }
            };

            return this;
        }

        public Builder setNegativeButton(String negativeButtonText,
                                         final OnClickListener listener) {
            this.negativeButtonText = negativeButtonText;
            this.negativeButtonClickListener = new OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (listener != null)
                        listener.onClick(dialog, which);
                    dialog.dismiss();
                }
            };

            return this;
        }

        public void show() {
            if (dialog != null && dialog.isShowing()) {
                dialog.dismiss();
            }
            create().show();
        }

        public void DialogDismiss() {
            dialog.dismiss();
        }

        CustomDialog create() {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            dialog = new CustomDialog(context, R.style.MyDialog);
            assert inflater != null;
            View layout = inflater.inflate(R.layout.dialog_layout, null);
            bt_botm_view = layout.findViewById(R.id.bt_botm_view);
            divideLine = layout.findViewById(R.id.divideLine);
            layout.setMinimumWidth(dialogwidth);
            dialog.addContentView(layout, new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            LinearLayout Titeltc = layout.findViewById(R.id.title);
            if (isDefaultTitel == 1) {
                title = new ImageView(context);
            } else if (isDefaultTitel == 0) {
                title = new ImageView(context);
            } else if (title == null && isDefaultTitel == -1) {
                Titeltc.setVisibility(View.GONE);
            }
            if (title != null) {
                Titeltc.addView(title);
            }
            if (positiveButtonText != null) {
                ((TextView) layout.findViewById(R.id.positiveButton))
                        .setText(positiveButtonText);
                if (positiveButtonClickListener != null) {
                    (layout.findViewById(R.id.positiveButton))
                            .setOnClickListener(new View.OnClickListener() {
                                public void onClick(View v) {
                                    positiveButtonClickListener.onClick(dialog,
                                            DialogInterface.BUTTON_POSITIVE);
                                }
                            });
                }
            } else {
                layout.findViewById(R.id.positiveButton).setVisibility(
                        View.GONE);
                divideLine.setVisibility(View.GONE);
            }
            if (negativeButtonText != null) {
                ((TextView) layout.findViewById(R.id.negativeButton))
                        .setText(negativeButtonText);
                if (negativeButtonClickListener != null) {
                    (layout.findViewById(R.id.negativeButton))
                            .setOnClickListener(new View.OnClickListener() {
                                public void onClick(View v) {
                                    negativeButtonClickListener.onClick(dialog,
                                            DialogInterface.BUTTON_NEGATIVE);
                                }
                            });
                }
            } else {
                layout.findViewById(R.id.negativeButton).setVisibility(
                        View.GONE);
                divideLine.setVisibility(View.GONE);
            }
            if (message != null) {
                ((TextView) layout.findViewById(R.id.message)).setText(message);
            }
            if (contentView != null) {
                ((LinearLayout) layout.findViewById(R.id.content)).removeAllViews();
                ((LinearLayout) layout.findViewById(R.id.content))
                        .addView(contentView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.MATCH_PARENT));
            }
            if (ishidd) {
                bt_botm_view.setVisibility(View.GONE);
            }
            dialog.setContentView(layout);
            dialog.setCanceledOnTouchOutside(isOutDiss);
            dialog.setCancelable(isBack);
            return dialog;
        }
    }
}
