package com.hackerfj.statelayout;

public interface LayoutState {

    // 加载中
    int STATE_LOADING = 1;

    // 加载成功
    int STATE_SUCCESS = 2;

    // 加载失败
    int STATE_FAIL = 3;
}
