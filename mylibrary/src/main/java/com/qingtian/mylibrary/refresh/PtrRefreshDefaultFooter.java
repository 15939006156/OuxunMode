package com.qingtian.mylibrary.refresh;
import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.qingtian.mylibrary.R;

import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrUIHandler;
import in.srain.cube.views.ptr.indicator.PtrIndicator;


public class PtrRefreshDefaultFooter extends FrameLayout implements PtrUIHandler {


    // 改时间
    private boolean isRunAnimation = false;
    private TextView text_rferesh_hint;

    private void initialize() {
        LayoutInflater.from(getContext()).inflate(R.layout.refresh_footer, this);
        setBackgroundColor(ContextCompat.getColor(getContext(), R.color.bg));
        text_rferesh_hint= (TextView)findViewById(R.id.pull_to_load_text);

    }

    public PtrRefreshDefaultFooter(Context context) {
        this(context, null, 0);
    }

    public PtrRefreshDefaultFooter(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PtrRefreshDefaultFooter(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize();
    }


    /**
     * 停止刷新动画。
     */
    private void stopAnimation() {
        if (isRunAnimation) {
            isRunAnimation = false;
        }
    }

    @Override
    public void onUIReset(PtrFrameLayout frame) {
        stopAnimation();
    }

    //准备刷新
    @Override
    public void onUIRefreshPrepare(PtrFrameLayout frame) {
        if(!frame.isPullToRefresh()){
            text_rferesh_hint.setText("释放刷新");
        }
    }


    //准备刷新
    @Override
    public void onUIRefreshBegin(PtrFrameLayout frame) {
        text_rferesh_hint.setText("正在刷新...");
        stopAnimation();
        stopAnimation();
    }

    @Override
    public void onUIRefreshComplete(PtrFrameLayout frame, boolean isHeader) {
        text_rferesh_hint.setText("刷新完成");
        stopAnimation();
    }


    //开始刷新
    @Override
    public void onUIPositionChange(PtrFrameLayout frame, boolean isUnderTouch, byte status, PtrIndicator ptrIndicator) {
    }

}
