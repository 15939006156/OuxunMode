package com.qingtian.mylibrary.refresh;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.util.PtrCLog;

public class PtrRefreshFrameLayout extends PtrFrameLayout {

    private PtrRefreshDefaultHeader mPtrClassicHeader;
    private PtrRefreshDefaultFooter mPtrClassicFooter;
    private boolean disallowInterceptTouchEvent;

    public PtrRefreshFrameLayout(Context context) {
        super(context);
        initViews();
    }



    public PtrRefreshFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initViews();
    }


    public PtrRefreshFrameLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initViews();
    }



    public void initViews() {
        mPtrClassicHeader = new PtrRefreshDefaultHeader(getContext());
        setHeaderView(mPtrClassicHeader);
        addPtrUIHandler(mPtrClassicHeader);
        mPtrClassicFooter = new PtrRefreshDefaultFooter(getContext());
        setFooterView(mPtrClassicFooter);
        addPtrUIHandler(mPtrClassicFooter);
    }

    public PtrRefreshDefaultHeader getHeader() {
        return mPtrClassicHeader;
    }


    //解决滑动冲突问题
    @Override
    public void requestDisallowInterceptTouchEvent(boolean disallowIntercept) {
        disallowInterceptTouchEvent = disallowIntercept;
        super.requestDisallowInterceptTouchEvent(disallowIntercept);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent e) {
        switch (e.getAction()) {
            case MotionEvent.ACTION_UP:
                //解除刷新的屏蔽
                this.requestDisallowInterceptTouchEvent(false);
                break;
        }

        if (disallowInterceptTouchEvent) {
            //事件向下分发给子控件，子控件会屏蔽掉父控件的刷新
            return dispatchTouchEventSupper(e);
        }

        return super.dispatchTouchEvent(e);
    }

}
