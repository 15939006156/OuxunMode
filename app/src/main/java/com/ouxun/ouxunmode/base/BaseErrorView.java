package com.ouxun.ouxunmode.base;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.ouxun.ouxunmode.R;

/**
 * 用做加载错误显示，按需显示
 * Created by Lance on 2016/8/14.
 */
public class BaseErrorView extends RelativeLayout {

    private TextView retryText;

    private OnRetryListener onRetryListener;

    public BaseErrorView(Context context) {
        super(context);
        initErrorView();
    }

    public BaseErrorView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initErrorView();
    }

    public BaseErrorView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initErrorView();
    }

    public void setOnRetryListener(OnRetryListener onRetryListener) {
        this.onRetryListener = onRetryListener;
    }

    public void initErrorView(){
        LayoutInflater.from(getContext()).inflate(R.layout.base_error,this,true);
        retryText= (TextView) findViewById(R.id.tv_error_retry);
        retryText.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onRetryListener!=null){
                    onRetryListener.onRetry();
                }
            }
        });
    }

    public interface OnRetryListener{
        void onRetry();
    }
}
