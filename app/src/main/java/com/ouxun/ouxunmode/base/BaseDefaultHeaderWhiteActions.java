package com.ouxun.ouxunmode.base;

import android.app.Activity;
import com.ouxun.ouxunmode.R;

/**
 * 改编者: lkp
 * 时间: 2017/8/14 0014 下午 6:53
 * 功能描述: 默认点击事件
 * 备注:
 */
public class BaseDefaultHeaderWhiteActions implements BaseWhiteHeaderView.HeaderActions {
    private Activity activity;

    public BaseDefaultHeaderWhiteActions(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void onLeftClick() {
        if(activity!=null){
            activity.finish();
            activity.overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
        }
    }

    @Override
    public void onRightClick() {

    }
}
