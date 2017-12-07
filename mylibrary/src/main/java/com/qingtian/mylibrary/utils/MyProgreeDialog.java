package com.qingtian.mylibrary.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import com.qingtian.mylibrary.R;


public class MyProgreeDialog extends AlertDialog {

	private TextView dialog_text;
	private Context context;
	private Animation animation;

	public MyProgreeDialog(Context context) {
		super(context, R.style.my_dialog);
		this.context = context;
	}
	protected MyProgreeDialog(Context context, int theme) {
		super(context, theme);
		this.context = context;
	}

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_loading);
		initView();
	}

	private void initView() {
		//设置图片旋转的动画
		ImageView dialog_image = (ImageView) findViewById(R.id.loading_img);
		dialog_text = (TextView) findViewById(R.id.loading_text);
		animation = AnimationUtils.loadAnimation(context,
				R.anim.loading_animation);
		dialog_image.startAnimation(animation);
	}

	public void setContentText(String str) {
		dialog_text.setText(str);
	}

}
