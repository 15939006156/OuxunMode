package com.ouxun.ouxunmode.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ouxun.ouxunmode.R;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 改编者: lkp
 * 时间: 2017/7/28 0028 上午 9:49
 * 功能描述: fragment的封装基类
 * 备注:
 */
public abstract class BaseFragment extends Fragment {

    Unbinder unbinder;
    protected BaseActivity mContext;
    public View basefragmentview;
    protected String TAG ;
    private boolean isViewCreated;
    private boolean isUIVisible;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        basefragmentview = inflater.inflate(getContentViewId(), container, false);
        unbinder = ButterKnife.bind(this, basefragmentview);
        mContext= (BaseActivity)getActivity();
        TAG = mContext.getClass().getName();

        isViewCreated = true;
        lazyLoad();
        initView();
        initData();
        return basefragmentview;
    }

    private void lazyLoad() {
//这里进行双重标记判断,是因为setUserVisibleHint会多次回调,并且会在onCreateView执行前回调,必须确保onCreateView加载完毕且页面可见,才加载数据
        if (isViewCreated && isUIVisible) {
            loadData();
            //数据加载完毕,恢复标记,防止重复加载
            isViewCreated = false;
            isUIVisible = false;
        }

    }



    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        //isVisibleToUser这个boolean值表示:该Fragment的UI 用户是否可见
        if (isVisibleToUser) {
            isUIVisible = true;
            lazyLoad();
        } else {
            isUIVisible = false;
        }
    }


    // Intent的页面跳转
    public void gotoActivity(Class<? extends Activity> clazz, boolean finish) {
        Intent intent = new Intent(mContext, clazz);
        startActivity(intent);
        if (finish) {
            mContext.finish();
        }
        mContext.overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
    }

    public void gotoActivity(Class<? extends Activity> clazz, Bundle bundle, boolean finish) {
        Intent intent = new Intent(mContext, clazz);
        if (bundle != null) intent.putExtras(bundle);
        startActivity(intent);
        if (finish) {
            mContext.finish();
        }
        mContext.overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
    }


    public abstract int getContentViewId();
    protected abstract void initView();
    protected abstract void initData();

    /**
     *懒加载
     */
    public void loadData() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
