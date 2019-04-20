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