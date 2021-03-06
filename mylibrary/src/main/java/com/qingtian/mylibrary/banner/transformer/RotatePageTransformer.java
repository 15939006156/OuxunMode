package com.qingtian.mylibrary.banner.transformer;

import android.view.View;

import com.nineoldandroids.view.ViewHelper;

/**
 * 描述:
 */
public class RotatePageTransformer extends BGAPageTransformer {
    private float mMaxRotation = 15.0f;

    public RotatePageTransformer() {
    }

    public RotatePageTransformer(float maxRotation) {
        setMaxRotation(maxRotation);
    }

    @Override
    public void handleInvisiblePage(View view, float position) {
        ViewHelper.setPivotX(view, view.getMeasuredWidth() * 0.5f);
        ViewHelper.setPivotY(view, view.getMeasuredHeight());
        ViewHelper.setRotation(view, 0);
    }

    @Override
    public void handleLeftPage(View view, float position) {
        float rotation = (mMaxRotation * position);
        ViewHelper.setPivotX(view, view.getMeasuredWidth() * 0.5f);
        ViewHelper.setPivotY(view, view.getMeasuredHeight());
        ViewHelper.setRotation(view, rotation);
    }

    @Override
    public void handleRightPage(View view, float position) {
        handleLeftPage(view, position);
    }

    public void setMaxRotation(float maxRotation) {
        if (maxRotation >= 0.0f && maxRotation <= 40.0f) {
            mMaxRotation = maxRotation;
        }
    }
}