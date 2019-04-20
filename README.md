# Android全局状态栏+多状态布局
[========]
### 1.国际惯例先上图
![markdown](https://www.mdeditor.com/images/logos/markdown.png "markdown")

[========]
### 2.功能概述

#### 2.1 全局标题栏功能简介
> 1.自定义XML
> 2.一行代码控制标题栏是否添加
> 3.可在BaseActivity中封装基础方法，例如:标题文字、颜色、大小、左侧按钮或文字等。
> 4.高度自定义封装，可直接使用商业项目

#### 2.2 多状态布局
> 1.默认已添加两种基础布局、分别为LoadingLayout和FailLayout页面，但需要自己添加页面、可直接获取View进行其他操作
> 2.可获取布局状态
> 3.一行代码设置布局状态
> 4.如果你觉得基础layout不够用，可自定义添加布局XML显示

[========]
###3.使用方法
> 1.将自己的BaseActivity继承系统中BaseStateLayout方法可直接使用，下面是我写的TestBaseActivity，可参考

```html
package com.hackerfj.basetoolbar;

import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.hackerfj.statelayout.BaseStateLayout;

public abstract class BaseActivity extends BaseStateLayout {


    /**
     * @return 内容布局
     */
    protected abstract int contentView();

    /**
     * 初始化方法
     */
    protected abstract void initViews();

    /**
     * 设置内容布局，因为这是BaseActivity则将contentView传至继承页面设置
     * @return
     */
    @Override
    protected int setContentLayout() {
        return contentView();
    }


    /**
     * 设置是否添加Toolbar设置boolean，如果设置为false将setToolbar设置为null即可
     * @return
     */
    @Override
    protected boolean isAddToolBar() {
        return true;
    }

    /**
     * 设置标题栏View
     * @return
     */
    @Override
    protected int setToolbar() {
        return R.layout.base_tool_bar;
    }

    /**
     * 设置加载中View
     * @return
     */
    @Override
    protected int setLoadingView() {
        return R.layout.layout_loading;
    }

    /**
     * 设置加载失败View
     * @return
     */
    @Override
    protected int setFailView() {
        return R.layout.layout_fail;
    }

    /**
     * 设置初始化操作，因为这是BaseActivity则将initView传至继承页面设置
     * @return
     */
    @Override
    public void initView() {
        initViews();
    }

    /**
     * 实例：设置标题，你可以用来做更多的操作，举一反三
     * @param title
     */
    public void setToolbarTitle(String title){
        TextView tvTitle =  getBaseToolbar().findViewById(R.id.tv_bar_title);
        tvTitle.setText(title);
    }

    /**
     * 动态设置我想显示的View
     * @param view
     */
    public void addView(int view){
        View myView = View.inflate(this, view, null);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        myView.setLayoutParams(params);
        setMyView(myView);
    }
}

```
> 2.使用Activity示例代码Kotlin

```html
package com.hackerfj.basetoolbar

import kotlinx.android.synthetic.main.base_tool_bar.*

class TestActivity : BaseActivity() {

    override fun contentView(): Int {
        return R.layout.activity_main
    }

    override fun initViews() {
        setToolbarTitle("主页")

        tv_bar_title.setOnClickListener {
            // 布局切换至加载中
            setLayout(com.hackerfj.statelayout.LayoutState.STATE_LOADING)
        }
        tv_bar_left.setOnClickListener {
            // 布局切换至失败
            setLayout(com.hackerfj.statelayout.LayoutState.STATE_FAIL)
        }
        tv_bar_right.setOnClickListener {
            // 布局切换至内容页面
//            setLayout(LayoutState.STATE_SUCCESS)
            //动态设置我想显示的View
            addView(R.layout.layout_my_view)
        }
    }

}
```

> 3.使用Activity示例代码Java

```html
package com.hackerfj.basetoolbar;

import android.view.View;

public class TestActivity extends BaseActivity {
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

```