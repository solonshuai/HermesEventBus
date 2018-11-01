package com.zxy.mytsfqxproject.View;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.CustomListener;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.zxy.mytsfqxproject.R;
import com.zxy.mytsfqxproject.inter.OnItemWheelView;

import java.util.List;

/**
 * 单选
 */
public class ShowWheelView {
    private static OptionsPickerView pvCustomOptions;

    public static void show(Context context, final List<String> list, final OnItemWheelView onItemWheelView) {
        pvCustomOptions = new OptionsPickerBuilder(context, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String tx = list.get(options1).trim();
                onItemWheelView.onItemClick(tx);
            }
        })
                .setLayoutRes(R.layout.dialog_wheelview, new CustomListener() {
                    @Override
                    public void customLayout(View v) {
                        final TextView ivCancel = v.findViewById(R.id.tv_cancel);
                        TextView tvSubmit = v.findViewById(R.id.tv_ok);
                        tvSubmit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pvCustomOptions.returnData();
                                pvCustomOptions.dismiss();
                            }
                        });
                        ivCancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pvCustomOptions.dismiss();
                            }
                        });
                    }
                })
                .isDialog(false)
                .build();
        pvCustomOptions.setPicker(list);
        pvCustomOptions.show();
    }
}
