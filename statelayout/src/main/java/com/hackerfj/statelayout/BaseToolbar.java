package com.hackerfj.statelayout;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

/**
 * 全局Toolbar
 */
public class BaseToolbar extends LinearLayout {

    public BaseToolbar(Context context,int view) {
        super(context);
        //添加View布局
        View toolbar = LayoutInflater.from(context).inflate(view, this);
        setOrientation(VERTICAL);
    }

}
