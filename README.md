# Android BaseToolbar+StatesLayout
### [中文版](https://github.com/luckyfj/BaseToolBar/blob/master/README_CN.md)
### 1.First picture，give a star

![markdown](https://github.com/luckyfj/BaseToolBar/blob/master/img/test.gif "markdown")

### 2.Function introduction

#### 2.1 BaseToolbar Function introduction
> 1.customize XML</br>
> 2.One line of code controls whether the title bar is added</br>
> 3.in BaseActivity Package basic methods such as title text, color, size, left button or text.</br>
> 4.Highly customizable package for direct use of commercial projects</br>

#### 2.2 StatesLayout Function introduction
> 1.Two basic layouts have been added by default、LoadingLayout And FailLayout，Need to add the page yourself、Get View directly to operate</br>
> 2.Get layout status</br>
> 3.One line of code sets the layout state</br>
> 4.If you feel that the basic layout is not enough, customize the layout XML display</br>

### 3.Instructions Function introduction
#### 1. Add
> 1.1 Add in global build.gradle
```html
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```
> 1.2 Add in the build.gradle under the app
```html
dependencies {
	         implementation 'com.github.luckyfj:BaseToolBar:1.0.0'
	}
```

#### 2.The BaseStateLayout method in the BaseActivity inheritance system can be used directly. The following is the TestBaseActivity I wrote for reference.

```html
package com.hackerfj.basetoolbar;

import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.hackerfj.statelayout.BaseStateLayout;

public abstract class BaseActivity extends BaseStateLayout {


    /**
     * @return Content layout
     */
    protected abstract int contentView();

    /**
     * Initialization method
     */
    protected abstract void initViews();

    /**
     * Set the content layout, because this is BaseActivity, then pass the contentView to the inherited page settings.
     * @return
     */
    @Override
    protected int setContentLayout() {
        return contentView();
    }


    /**
     * Set whether to add a Toolbar to set boolean, if set to false, setToolbar to null
     * @return
     */
    @Override
    protected boolean isAddToolBar() {
        return true;
    }

    /**
     * Set the title bar View
     * @return
     */
    @Override
    protected int setToolbar() {
        return R.layout.base_tool_bar;
    }

    /**
     * Set the loading view
     * @return
     */
    @Override
    protected int setLoadingView() {
        return R.layout.layout_loading;
    }

    /**
     * Set load failed View
     * @return
     */
    @Override
    protected int setFailView() {
        return R.layout.layout_fail;
    }

    /**
     * Set the initialization operation, because this is BaseActivity, then pass the initView to the inherited page settings.
     * @return
     */
    @Override
    public void initView() {
        initViews();
    }

    /**
     * Example: Set the title, you can use it to do more operations,
     * @param title
     */
    public void setToolbarTitle(String title){
        TextView tvTitle =  getBaseToolbar().findViewById(R.id.tv_bar_title);
        tvTitle.setText(title);
    }

    /**
     * Dynamically set the view I want to display
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
#### 3.Use the Activity sample code Kotlin

```html
package com.hackerfj.basetoolbar

import kotlinx.android.synthetic.main.base_tool_bar.*

class TestActivity : BaseActivity() {

    override fun contentView(): Int {
        return R.layout.activity_main
    }

    override fun initViews() {
        setToolbarTitle("Home")

        tv_bar_title.setOnClickListener {
            // Layout switched to loading
            setLayout(com.hackerfj.statelayout.LayoutState.STATE_LOADING)
        }
        tv_bar_left.setOnClickListener {
            // Layout switched to failure
            setLayout(com.hackerfj.statelayout.LayoutState.STATE_FAIL)
        }
        tv_bar_right.setOnClickListener {
            // Switch layout to content page
//            setLayout(LayoutState.STATE_SUCCESS)
            // Dynamically set the view I want to display
            addView(R.layout.layout_my_view)
        }
    }

}
```

#### 4.Use the Activity sample code Java

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
        setToolbarTitle("Home");

        findViewById(R.id.tv_bar_title).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Layout switched to loading
                setLayout(com.hackerfj.statelayout.LayoutState.STATE_LOADING);
            }
        });

        findViewById(R.id.tv_bar_left).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Layout switched to failure
                setLayout(com.hackerfj.statelayout.LayoutState.STATE_FAIL);
            }
        });
        findViewById(R.id.tv_bar_right).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Switch layout to content page
//            setLayout(LayoutState.STATE_SUCCESS)
                // Dynamically set the view I want to display
                addView(R.layout.layout_my_view);
            }
        });
    }
}

```