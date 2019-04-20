package com.hackerfj.statelayout;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public abstract class BaseStateLayout extends AppCompatActivity {

    /**
     * 成功View
     */
    private View success;

    /**
     * 失败View
     */
    private View fail;

    /**
     * 加载中View
     */
    private View loading;

    /**
     * 状态码
     */
    private int state;

    /**
     * 标题栏
     */
    private BaseToolbar toolbar;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLayout();
    }

    protected abstract int setContentLayout();

    protected abstract boolean isAddToolBar();

    protected abstract int setToolbar();

    protected abstract int setLoadingView();

    protected abstract int setFailView();

    /**
     * 初始化加载布局
     */
    private void setLayout() {
        if (isAddToolBar()) {
            toolbar = new BaseToolbar(this, setToolbar());
            toolbar.addView(successView());
            toolbar.addView(loadingView());
            toolbar.addView(failView());
            setContentView(toolbar);
        } else {
            setContentView(setContentLayout());
        }
        initView();
    }

    /**
     * 设置布局状态
     * @param state 状态
     */
    public void setLayout(int state) {
        if (getLayoutState() == state) {
            return;
        }
        switch (state) {
            case LayoutState.STATE_LOADING:
                success.setVisibility(View.GONE);
                loading.setVisibility(View.VISIBLE);
                fail.setVisibility(View.GONE);
                state = LayoutState.STATE_LOADING;
                break;
            case LayoutState.STATE_SUCCESS:
                success.setVisibility(View.VISIBLE);
                loading.setVisibility(View.GONE);
                fail.setVisibility(View.GONE);
                state = LayoutState.STATE_SUCCESS;
                break;
            case LayoutState.STATE_FAIL:
                success.setVisibility(View.GONE);
                loading.setVisibility(View.GONE);
                fail.setVisibility(View.VISIBLE);
                state = LayoutState.STATE_FAIL;
                break;
        }
    }

    /**
     * 动态设置我想显示的View
     * @param view
     */
    public void setMyView(View view){
        getBaseToolbar().addView(view);
        success.setVisibility(View.GONE);
        loading.setVisibility(View.GONE);
        fail.setVisibility(View.GONE);
        view.setVisibility(View.VISIBLE);
    }

    /**
     *
     * @return 获取当前布局状态
     */
    public int getLayoutState() {
        return state;
    }

    /**
     *
     * @return 返回BaseToolbar方便直接在BaseActivity中封装基本方法，例如统一封装设置主题文字内容、大小、颜色等。
     */
    public BaseToolbar getBaseToolbar() {
        return toolbar;
    }

    /**
     *
     * @return 初始化加载中页面
     */
    public View loadingView() {
        loading = View.inflate(this, setLoadingView(), null);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        loading.setLayoutParams(params);
        return loading;
    }

    /**
     *
     * @return 初始化成功页面
     */
    public View successView() {
        success = View.inflate(this, setContentLayout(), null);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        success.setLayoutParams(params);
        return success;
    }


    /**
     *
     * @return 初始化失败页面
     */
    public View failView() {
        fail = View.inflate(this, setFailView(), null);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        fail.setLayoutParams(params);
        return fail;
    }

    /**
     * 初始化方法
     */
    public abstract void initView();

}
