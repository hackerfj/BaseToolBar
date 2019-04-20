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
