package com.ouxun.ouxunmode.base;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.ouxun.ouxunmode.R;

/**
 * 改编者: lkp
 * 时间: 2017/8/14 0014 下午 5:31
 * 功能描述: 这是自定的头布局
 * 备注:
 */
public class BaseWhiteHeaderView extends RelativeLayout {
    public TextView titleText,leftText,leftText2,rightText1,rightText2;
    private LinearLayout leftlinLayout,rightlinLayout;
    private HeaderActions headerActions;
    private OnClickListener onClickListener;
    private RelativeLayout rel_header;
    public EditText edit_head_title;
    public View text_head_view;

    public BaseWhiteHeaderView(Context context) {
        super(context);
        initHeaderView();
    }

    public BaseWhiteHeaderView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initHeaderView();
    }

    public BaseWhiteHeaderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initHeaderView();
    }

    private void initHeaderView() {
        LayoutInflater.from(getContext()).inflate(R.layout.base_header, this, true);
        findViews();
        onClickListener = new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (headerActions != null) {
                    switch (view.getId()) {
                        case R.id.lin_head_left:
                            headerActions.onLeftClick();
                            break;
                        case R.id.lin_head_right:
                            headerActions.onRightClick();
                            break;
                    }

                }
            }
        };
        leftlinLayout.setOnClickListener(onClickListener);
        rightlinLayout.setOnClickListener(onClickListener);
        setTitleColor(R.color.white);
        leftText.setBackgroundResource(R.drawable.back_left);
        leftText2.setTextColor(getResources().getColor(R.color.main_text333));
        titleText.setTextColor(getResources().getColor(R.color.main_text333));
        rightText1.setTextColor(getResources().getColor(R.color.main_text333));

    }

    public void setTitleText(String title) {
        titleText.setText(title);
    }

    public void setTitleColor(int color) {
        rel_header.setBackgroundResource(color);
    }
    public void setHeaderActions(HeaderActions headerActions) {
        this.headerActions = headerActions;
    }

    private void findViews(){
        rel_header=(RelativeLayout)findViewById(R.id.rel_header);
        titleText=(TextView)findViewById(R.id.text_head_title);
        leftText=(TextView)findViewById(R.id.text_head_leftback);
        leftText2=(TextView) findViewById(R.id.text_head_left2);
        rightText1=(TextView)findViewById(R.id.text_head_right1);
        rightText2=(TextView) findViewById(R.id.text_head_right2);
        leftlinLayout=(LinearLayout) findViewById(R.id.lin_head_left);
        rightlinLayout=(LinearLayout) findViewById(R.id.lin_head_right);
        edit_head_title=(EditText)findViewById(R.id.edit_head_title);
        text_head_view = findViewById(R.id.text_head_view);
    }

    public interface HeaderActions{
        void onLeftClick();
        void onRightClick();
    }
}
