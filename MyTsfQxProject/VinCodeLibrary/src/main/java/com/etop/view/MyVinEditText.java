package com.etop.view;

import android.content.Context;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.Selection;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MyVinEditText extends RelativeLayout {

    private EditText editText; //文本编辑框
    private Context context;

    private LinearLayout linearLayout; //文本密码的文本
    private TextView[] textViews; //文本数组

    public MyVinEditText(Context context) {
        this(context, null);
    }

    public MyVinEditText(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyVinEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
    }

    /**
     * @param bgdrawable    背景drawable
     * @param splilinewidth 分割线宽度
     * @param splilinecolor 分割线颜色
     * @param color      字体颜色
     * @param size       字体大小
     */
    public void initStyle(int bgdrawable, float splilinewidth, int splilinecolor, int color, int size) {
        initEdit(bgdrawable);
        initShowInput(bgdrawable, splilinewidth, splilinecolor, color, size);
    }

    /**
     * 初始化编辑框
     *
     * @param bgcolor
     */
    private void initEdit(int bgcolor) {
        editText = new EditText(context);
        editText.setBackgroundResource(bgcolor);
        editText.setCursorVisible(false);
        editText.setTextSize(0);
        editText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS | InputType.TYPE_NUMBER_VARIATION_NORMAL);
        editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(17)});
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Editable etext = editText.getText();
                Selection.setSelection(etext, etext.length());
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                initDatas(s);
            }
        });
        LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        lp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
        addView(editText, lp);

    }

    /**
     * @param bgcolor       背景drawable
     * @param slpilinewidth 分割线宽度
     * @param splilinecolor 分割线颜色
     * @param color      字体颜色
     * @param size       字体大小
     */
    public void initShowInput(int bgcolor, float slpilinewidth, int splilinecolor, int color, int size) {
        linearLayout = new LinearLayout(context);
        linearLayout.setBackgroundResource(bgcolor);
        LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        linearLayout.setLayoutParams(layoutParams);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        addView(linearLayout);

        textViews = new TextView[17];
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LayoutParams.MATCH_PARENT);
        params.weight = 1;
        params.gravity = Gravity.CENTER;

        LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(dip2px(context, slpilinewidth), LayoutParams.MATCH_PARENT);
        for (int i = 0; i < textViews.length; i++) {
            final int index = i;
            TextView textView = new TextView(context);
            textView.setGravity(Gravity.CENTER);
            textViews[i] = textView;
            textViews[i].setTextSize(size);
            textViews[i].setTextColor(context.getResources().getColor(color));
            linearLayout.addView(textView, params);


            if (i < textViews.length - 1) {
                View view = new View(context);
                view.setBackgroundColor(context.getResources().getColor(splilinecolor));
                linearLayout.addView(view, params2);
            }
        }
    }

    /**
     * 根据输入字符，显示字符个数
     *
     * @param s
     */
    public void initDatas(Editable s) {
        if (s.length() > 0) {
            int length = s.length();
            for (int i = 0; i < 17; i++) {
                if (i < length) {
                    for (int j = 0; j < length; j++) {
                        char ch = s.charAt(j);
                        textViews[j].setText(String.valueOf(ch));
                    }
                } else {
                    textViews[i].setText("");
                }
            }
        } else {
            for (int i = 0; i < 17; i++) {
                textViews[i].setText("");
            }
        }
    }

    public String getVinText() {
        if (editText != null)
            return editText.getText().toString().trim();
        return "";
    }

    public void setText(String matter) {
        editText.setText(matter);
    }

    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }
}
