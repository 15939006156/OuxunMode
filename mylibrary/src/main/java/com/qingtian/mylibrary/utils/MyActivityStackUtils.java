package com.qingtian.mylibrary.utils;

import android.app.Activity;

import java.util.Stack;

/**
 * Created：晴天 on 2016/11/3 0003 17:38
 * 描述：MyActivityStackUtils
 * qq:425116228
 */
public class MyActivityStackUtils {

	private static MyActivityStackUtils instance;
	private Stack<Activity> stack;// activity�?

	private MyActivityStackUtils() {
	}

	public static MyActivityStackUtils getInstance() {
		if (instance == null) {
			instance = new MyActivityStackUtils();
		}
		return instance;
	}

	/**
	 * 
	 * 描述�?把一个activity压入栈中
	 * 在BaseActivity中调用 onCreate 添加进去
	 * @param actvity
	 */
	public void push(Activity actvity) {
		if (stack == null) {
			stack = new Stack<Activity>();
		}
		stack.add(actvity);
	}
	
	/**
	 * 
	 * 描述移除�?��activity
	 * 在BaseActivity中调用 onDestroy 添加进去
	 * @param activity
	 */
	public void remove(Activity activity) {
		if (stack != null && stack.size() > 0) {
			if (activity != null) {
				activity.finish();
				stack.remove(activity);
				activity = null;
			}
		}
	}

	/**
	 * 
	 * 描述�?获取栈顶的activity，先进后出原�?
	 * @return
	 */
	public Activity getLastActivity() {
		return stack.lastElement();
	}

	/**
	 * 
	 * 描述删除所有的activity
	 */
	public void finishAllActivity() {
		if (stack != null) {
			while (stack.size() > 0) {
				Activity activity = getLastActivity();
				if (activity == null)
					break;
				remove(activity);
			}
		}
	}

	/**
	 * 
	 * 描述：退出工程
	 */
	public void exitApp(){
		finishAllActivity();
	}

}
