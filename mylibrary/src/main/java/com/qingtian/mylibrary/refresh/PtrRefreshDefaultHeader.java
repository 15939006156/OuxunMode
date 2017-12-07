package com.qingtian.mylibrary.refresh;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.qingtian.mylibrary.R;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrUIHandler;
import in.srain.cube.views.ptr.indicator.PtrIndicator;

public class PtrRefreshDefaultHeader extends FrameLayout implements PtrUIHandler {


    ImageView mIvIcon;
    // 改时间
    private AnimationDrawable mAnimationDrawable;
    private boolean isRunAnimation = false;
    private int limitX;
    private TextView text_rferesh_hint;


    private void initialize() {
        LayoutInflater.from(getContext()).inflate(R.layout.refresh_parallax, this);
        setBackgroundColor(ContextCompat.getColor(getContext(), R.color.bg));

        mIvIcon = (ImageView) findViewById(R.id.iv_refresh_icon);
        mAnimationDrawable = (AnimationDrawable) mIvIcon.getDrawable();
        text_rferesh_hint= (TextView)findViewById(R.id.text_rferesh_hint);

    }

    public PtrRefreshDefaultHeader(Context context) {
        this(context, null, 0);
    }

    public PtrRefreshDefaultHeader(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PtrRefreshDefaultHeader(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize();
    }

    /**
     * 开始刷新动画。
     */
    private void startAnimation() {
        if (!isRunAnimation) {
            isRunAnimation = true;
            mIvIcon.setImageDrawable(mAnimationDrawable);
            mAnimationDrawable.start();
        }
    }

    /**
     * 停止刷新动画。
     */
    private void stopAnimation() {
        if (isRunAnimation) {
            isRunAnimation = false;
            mAnimationDrawable.stop();
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
        startAnimation();
    }

    @Override
    public void onUIRefreshComplete(PtrFrameLayout frame, boolean isHeader) {
        text_rferesh_hint.setText("刷新完成");
        stopAnimation();
    }



    //开始刷新
    @Override
    public void onUIPositionChange(PtrFrameLayout frame, boolean isUnderTouch, byte status, PtrIndicator ptrIndicator) {
        final int offsetToRefresh = frame.getOffsetToRefresh();
        final int currentPos = ptrIndicator.getCurrentPosY();

        if (currentPos <= offsetToRefresh && !isRunAnimation){
            if (limitX == 0) calcLimitX();

            double percent = (double) currentPos / offsetToRefresh;
            int targetX = (int) (limitX * percent);
            mIvIcon.setTranslationX(targetX);

            int newPercent = (int) (percent * 100);

            if (newPercent % 10 == 0) {
                double i = newPercent / 10;
                if (i % 2 == 0) {
                    mIvIcon.setImageResource(R.drawable.wb_loading_frame1);
                } else {
                    mIvIcon.setImageResource(R.drawable.wb_loading_frame1);
                }
            } else if (newPercent % 5 == 0) {
                mIvIcon.setImageResource(R.drawable.wb_loading_frame2);
            }
        }
    }

    /**
     * 计算动画的位置
     */
    private void calcLimitX() {
//      limitX = DisplayUtils.screenWidth / 2;
        int mIconIvWidth = mIvIcon.getMeasuredWidth();
        limitX -= (mIconIvWidth / 2);
    }
}
