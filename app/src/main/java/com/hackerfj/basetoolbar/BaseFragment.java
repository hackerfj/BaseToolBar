package com.hackerfj.basetoolbar;

import com.hackerfj.statelayout.BaseStateFragment;


/**
 * @author jiefu
 */
public abstract class BaseFragment extends BaseStateFragment {

    /**
     * 内容布局
     */
    protected abstract int contentView();

    /**
     * 初始化方法
     */
    protected abstract void initViews();



    @Override
    protected int setContentLayout() {
        return contentView();
    }

    @Override
    protected boolean isAddToolBar() {
        return false;
    }

    @Override
    protected int setToolbar() {
        return R.layout.base_tool_bar;
    }

    @Override
    protected int setLoadingView() {
        return R.layout.layout_loading;
    }

    @Override
    protected int setFailView() {
        return R.layout.layout_fail;
    }

    @Override
    public void initView() {
        initViews();
    }
}
