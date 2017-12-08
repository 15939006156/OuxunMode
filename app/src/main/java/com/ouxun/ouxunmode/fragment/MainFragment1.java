package com.ouxun.ouxunmode.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ouxun.ouxunmode.R;
import com.ouxun.ouxunmode.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2017/12/1 0001.
 * 编写人：li
 * 功能描述：
 */
public class MainFragment1 extends BaseFragment {
    @BindView(R.id.test_hello)
    TextView testHello;
    Unbinder unbinder;

    @Override
    public int getContentViewId() {
        return R.layout.fragment_main1;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
