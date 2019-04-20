package com.hackerfj.basetoolbar;

import android.view.View;

public class TestTwoActivity extends BaseActivity {
    @Override
    protected int contentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViews() {
        setToolbarTitle("主页");

        findViewById(R.id.tv_bar_title).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 布局切换至加载中
                setLayout(com.hackerfj.statelayout.LayoutState.STATE_LOADING);
            }
        });

        findViewById(R.id.tv_bar_left).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 布局切换至失败
                setLayout(com.hackerfj.statelayout.LayoutState.STATE_FAIL);
            }
        });
        findViewById(R.id.tv_bar_right).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 布局切换至内容页面
//            setLayout(LayoutState.STATE_SUCCESS)
                //动态设置我想显示的View
                addView(R.layout.layout_my_view);
            }
        });
    }
}
