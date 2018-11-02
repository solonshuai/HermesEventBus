package com.zxy.mytsfqxproject.rongIM;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zxy.mytsfqxproject.R;

import java.util.Objects;

public class SubConversationListActivtiy extends FragmentActivity {
    ImageView iv_left;
    TextView title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.subconversationlist);
        iv_left = findViewById(R.id.iv_left);
        iv_left.setImageResource(R.mipmap.img_back);
        title = findViewById(R.id.tv_header_title);
        iv_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        title.setText(Objects.requireNonNull(getIntent().getData()).getQueryParameter("title"));
    }
}
