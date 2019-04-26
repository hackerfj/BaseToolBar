package com.hackerfj.statelayout;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public abstract class BaseStateFragment extends Fragment {
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


    protected abstract int setContentLayout();

    protected abstract boolean isAddToolBar();

    protected abstract int setToolbar();

    protected abstract int setLoadingView();

    protected abstract int setFailView();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        initView();
        return setLayout(container);
    }

    /**
     * 初始化加载布局
     */
    private View setLayout(ViewGroup container) {
        if (isAddToolBar()) {
            toolbar = new BaseToolbar(getContext(), setToolbar());
            toolbar.addView(successView());
            toolbar.addView(loadingView());
            toolbar.addView(failView());
            return toolbar;
        } else {
            return View.inflate(getContext(), setContentLayout(), container);
        }
    }

    /**
     * 设置布局状态
     *
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
            default:
                break;
        }
    }

    /**
     * 动态设置我想显示的View
     *
     * @param view
     */
    public void setMyView(View view) {
        getBaseToolbar().addView(view);
        success.setVisibility(View.GONE);
        loading.setVisibility(View.GONE);
        fail.setVisibility(View.GONE);
        view.setVisibility(View.VISIBLE);
    }

    /**
     * @return 获取当前布局状态
     */
    public int getLayoutState() {
        return state;
    }

    /**
     * @return 返回BaseToolbar方便直接在BaseActivity中封装基本方法，例如统一封装设置主题文字内容、大小、颜色等。
     */
    public BaseToolbar getBaseToolbar() {
        return toolbar;
    }

    /**
     * @return 初始化加载中页面
     */
    public View loadingView() {
        loading = View.inflate(getContext(), setLoadingView(), null);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        loading.setLayoutParams(params);
        return loading;
    }

    /**
     * @return 初始化成功页面
     */
    public View successView() {
        success = View.inflate(getContext(), setContentLayout(), null);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        success.setLayoutParams(params);
        return success;
    }


    /**
     * @return 初始化失败页面
     */
    public View failView() {
        fail = View.inflate(getContext(), setFailView(), null);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        fail.setLayoutParams(params);
        return fail;
    }

    /**
     * 初始化方法
     */
    public abstract void initView();
}
