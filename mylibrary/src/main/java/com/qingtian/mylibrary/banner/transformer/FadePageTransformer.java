package com.qingtian.mylibrary.banner.transformer;

import android.view.View;

import com.nineoldandroids.view.ViewHelper;

/**
 * 描述:
 */
public class FadePageTransformer extends BGAPageTransformer {

    @Override
    public void handleInvisiblePage(View view, float position) {
    }

    @Override
    public void handleLeftPage(View view, float position) {
        ViewHelper.setTranslationX(view, -view.getWidth() * position);
        ViewHelper.setAlpha(view, 1 + position);
    }

    @Override
    public void handleRightPage(View view, float position) {
        ViewHelper.setTranslationX(view, -view.getWidth() * position);
        ViewHelper.setAlpha(view, 1 - position);
    }

}