package com.qingtian.mylibrary.popwindow;

import android.content.Context;
import android.view.View;
import android.widget.PopupWindow;

import com.qingtian.mylibrary.utils.MySizeUtils;

/**
 * 改编者: lkp
 * 时间: 2017/6/15 0015 上午 11:38
 * 功能描述: PopupWindow公共的类
 * 备注: 1.设置PopupWindow布局+2.设置子View的点击事件+3.设置宽度和高度如果不设置默认是wrap_content+4.设置背景灰色程度
 *      5.设置动画 +6.是否可点击Outside消失 +7.创建popwindow
 *      需要监听点击的子view的要实现接口 implements CommonPopupWindow.ViewInterface
 */
public class CommonPopupWindow extends PopupWindow {

    final PopupController controller;

    @Override
    public int getWidth() {
        return controller.mPopupView.getMeasuredWidth();
    }

    @Override
    public int getHeight() {
        return controller.mPopupView.getMeasuredHeight();
    }

    public interface ViewInterface {
        void getChildView(View view, int layoutResId);
    }

    public CommonPopupWindow(Context context) {
        controller = new PopupController(context, this);
    }

    @Override
    public void dismiss() {
        super.dismiss();
        controller.setBackGroundLevel(1.0f);
    }

    public static class Builder {
        private final PopupController.PopupParams params;
        private ViewInterface listener;

        public Builder(Context context) {
            params = new PopupController.PopupParams(context);
        }

        /**
         * @param layoutResId 设置PopupWindow 布局ID
         */
        public Builder setView(int layoutResId) {
            params.mView = null;
            params.layoutResId = layoutResId;
            return this;
        }

        /**
         * @param view 设置PopupWindow布局
         * @return Builder
         */
        public Builder setView(View view) {
            params.mView = view;
            params.layoutResId = 0;
            return this;
        }

        /**
         * 设置子View的点击事件
         *
         * @param listener ViewInterface
         * @return Builder
         */
        public Builder setViewOnclickListener(ViewInterface listener) {
            this.listener = listener;
            return this;
        }

        /**
         * 设置宽度和高度 如果不设置 默认是wrap_content
         *
         * @param width 宽
         * @return Builder
         */
        public Builder setWidthAndHeight(int width, int height) {
            params.mWidth = width;
            params.mHeight = height;
            return this;
        }

        /**
         * 设置背景灰色程度
         *
         * @param level 0.0f-1.0f
         * @return Builder
         */
        public Builder setBackGroundLevel(float level) {
            params.isShowBg = true;
            params.bg_level = level;
            return this;
        }

        /**
         * 是否可点击Outside消失
         * @param touchable 是否可点击
         * @return Builder
         */
        public Builder setOutsideTouchable(boolean touchable) {
            params.isTouchable = touchable;
            return this;
        }

        /**
         * 设置动画
         * @return Builder
         */
        public Builder setAnimationStyle(int animationStyle) {
            params.isShowAnim = true;
            params.animationStyle = animationStyle;
            return this;
        }

        /**
         * 创建popwindow
         * @return
         */
        public CommonPopupWindow create() {
            final CommonPopupWindow popupWindow = new CommonPopupWindow(params.mContext);
            params.apply(popupWindow.controller);
            if (listener != null && params.layoutResId != 0) {
                listener.getChildView(popupWindow.controller.mPopupView, params.layoutResId);
            }
            MySizeUtils.measureWidthAndHeight(popupWindow.controller.mPopupView);
            return popupWindow;
        }
    }
}
