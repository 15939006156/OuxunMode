package com.qingtian.mylibrary.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;
/**
 * Created：晴天 on 2016/11/3 0003 17:38
 * 描述：
 * qq:425116228
 */
public class MyListview extends ListView {

	public MyListview(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	/**
	 * 设置不滚动
	 */
	public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		//重新设置高度
		heightMeasureSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}

}
