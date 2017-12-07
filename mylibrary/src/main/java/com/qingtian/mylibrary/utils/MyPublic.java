package com.qingtian.mylibrary.utils;

import android.content.Context;
import android.util.DisplayMetrics;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created：晴天 on 2016/11/4 0004 16:32
 * 描述： 这是一个项目常用设置的工具类
 * qq:425116228
 */
public class MyPublic {

	private Context context;
	
	public MyPublic(){
		
	}
	public MyPublic(Context context){
		this.context = context;
	}


	private void getPixels(){
		DisplayMetrics dm2 = context.getResources().getDisplayMetrics();
	}


	/**
	 * 正则匹配 是否是数字
	 *
	 * @param str
	 * @return
	 */
	public static boolean isNumber(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher matcher = pattern.matcher(str);
		if (!matcher.matches()) {
			return false;
		}
		return true;
	}

	/**
	 * 判断是否为主线程
	 */

	public static boolean isMainThread(Context ctx){
		String threadName = Thread.currentThread().getName();
		if ("main".equals(threadName)) {
			return true;
		} else {
			return false;
		}


	}

}
