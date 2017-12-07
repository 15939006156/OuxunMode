package com.ouxun.ouxunmode.base;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import com.ouxun.ouxunmode.R;
import com.qingtian.mylibrary.utils.MyActivityStackUtils;
import java.lang.ref.WeakReference;
import java.util.List;

/**
 * 描述:这是activity的基类
 */
public abstract class BaseActivity extends AppCompatActivity {

    protected String TAG;
    protected BaseActivity mContext;
    FrameLayout headerLayout;
    FrameLayout containerLayout;
    FrameLayout loadingView;
    FrameLayout errorView;
    private ProgressDialog mProgressDialog;
    private MyHandler mHandler = new MyHandler(this);
    protected BaseErrorView.OnRetryListener onRetryListener;
    protected View mContentView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.base_activity);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//强制竖屏
        MyActivityStackUtils.getInstance().push(this);
        TAG = "<<<<<<<<<" + this.getClass().getSimpleName();
        mContext = this;
        headerLayout = (FrameLayout) findViewById(R.id.fl_base_header);
        containerLayout = (FrameLayout) findViewById(R.id.fl_base_container);
        loadingView = (FrameLayout) findViewById(R.id.fl_base_loading);
        errorView = (FrameLayout) findViewById(R.id.fl_base_error);
        initHeaderLayout();
        initLoadingView();
        initErrorView();
    }


    //多EditText同步输入验证
    protected void JudgeTextChanged(List<EditText> list, Button button) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getText().toString().length() == 0) {
                button.setBackgroundResource(R.drawable.drawable_themes_gray);
                button.setClickable(false);
                return;
            }
            button.setBackgroundResource(R.drawable.drawable_themes);
            button.setClickable(true);
        }
    }


    @Override
    public void setContentView(@LayoutRes final int layoutResID) {
        mContentView = LayoutInflater.from(mContext).inflate(layoutResID, containerLayout, true);
    }


    /**
     * 添加头布局
     *
     * @param headerView
     */
    public void setHeaderLayout(View headerView) {
        headerLayout.addView(headerView);
    }


    public void initLoadingView() {
        setLoadingView(R.layout.base_loading);
    }

    public void setLoadingView(@LayoutRes int layoutResID) {
        LayoutInflater.from(this).inflate(layoutResID, loadingView, true);
    }


    public void initErrorView() {
        BaseErrorView errorViews = new BaseErrorView(this);
        initRetryListener();
        errorViews.setOnRetryListener(onRetryListener);
        this.errorView.addView(errorViews);
    }

    /**
     * 通过状态显示加载页面，内容页面，错误页面
     */
    public void showViewByState(boolean showLoading, boolean showContainer, boolean showErrorView) {
        showView(loadingView, showLoading);
        showView(containerLayout, showContainer);
        showView(errorView, showErrorView);
    }

    private void showView(View view, boolean show) {
        if (show) {
            view.setVisibility(View.VISIBLE);
        } else {
            view.setVisibility(View.GONE);
        }
    }

    /**
     * 编写自己的头布局文件下面的必须设置
     * BaseHeaderView mHeader = new BaseHeaderView(this);
     * mHeader.setTitleText("");
     * mHeader.setLeftText("");
     * mHeader.setHeaderActions(new BaseDefaultHeaderActions(this));
     * setHeaderLayout(mHeader);
     */
    public abstract void initHeaderLayout();

    /**
     * 点击刷新网络的监听
     * 在这里重写onRetryListener
     */
    public void initRetryListener() {

    }


    // Intent的页面跳转
    public void gotoActivity(Class<? extends Activity> clazz, boolean finish) {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
        if (finish) {
            finish();
        }
        overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
    }

    public void gotoActivity(Class<? extends Activity> clazz, Bundle bundle, boolean finish) {
        Intent intent = new Intent(this, clazz);
        if (bundle != null) intent.putExtras(bundle);
        startActivity(intent);
        if (finish) {
            finish();
        }
        overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
    }

    public void BacktoActivity(Class<? extends Activity> clazz, Bundle bundle,  boolean finish) {
        Intent intent = new Intent(this, clazz);
        if (bundle != null) intent.putExtras(bundle);
        startActivity(intent);
        if (finish) {
            finish();
        }
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }

    //监听后退按钮实现 动画效果
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
            overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    public static class MyHandler extends Handler {
        private WeakReference<BaseActivity> weakReference;

        public MyHandler(BaseActivity value) {
            weakReference = new WeakReference<BaseActivity>(value);
        }

        public void clear() {
            weakReference.clear();
        }

        @Override
        public void handleMessage(Message msg) {
            BaseActivity value = weakReference.get();
            if (value != null) {
                value.mProgressDialog.dismiss();
            }
            super.handleMessage(msg);
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler.clear();
        MyActivityStackUtils.getInstance().remove(this);
    }



}