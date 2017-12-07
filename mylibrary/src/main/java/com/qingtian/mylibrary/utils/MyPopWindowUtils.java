package com.qingtian.mylibrary.utils;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import com.qingtian.mylibrary.R;
import com.qingtian.mylibrary.popwindow.CommonPopupWindow;

/**
 * Created by Administrator on 2017/9/11 0011.
 * 编写人：li
 * 功能描述：
 */
public class MyPopWindowUtils {


    /**
     * 创建从底部弹出的popwindow
     * @param ctx
     * @param view
     */
    public static CommonPopupWindow  createpop(Activity ctx, View view){
         CommonPopupWindow popupWindow=null;
        if (popupWindow != null && popupWindow.isShowing()) return null;
        popupWindow = new CommonPopupWindow.Builder(ctx)
                .setView(R.layout.popup_listup)
                .setWidthAndHeight(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
                .setAnimationStyle(R.style.AnimUp)
                .setViewOnclickListener((CommonPopupWindow.ViewInterface)ctx)
                .setBackGroundLevel(0.5f)
                .setOutsideTouchable(false)
                .create();
        popupWindow.showAsDropDown(view);
        return popupWindow;
    }
}
