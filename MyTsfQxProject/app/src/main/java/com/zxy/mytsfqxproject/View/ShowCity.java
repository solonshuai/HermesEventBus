package com.zxy.mytsfqxproject.View;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.zxy.mytsfqxproject.inter.OnItemWheelView;
import com.zxy.mytsfqxproject.mvp.entity.JsonBean;

import java.util.ArrayList;


public class ShowCity {

    public static void show(Context context, final ArrayList<JsonBean> province, final ArrayList<ArrayList<String>> city, final ArrayList<ArrayList<ArrayList<String>>> area, final OnItemWheelView onItemWheelView) {

        OptionsPickerView pvOptions = new OptionsPickerBuilder(context, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String tx = province.get(options1).getPickerViewText() +
                        city.get(options1).get(options2) +
                        area.get(options1).get(options2).get(options3);
                onItemWheelView.onItemClick(tx);
            }
        })
                .setTitleText("城市选择")
                .setDividerColor(Color.BLACK)
                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                .setContentTextSize(20)
                .build();
        pvOptions.setPicker(province, city, area);//三级选择器
        pvOptions.show();
    }
}
