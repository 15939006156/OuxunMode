package com.qingtian.mylibrary.utils;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class MyNetUtils {


	private boolean k_isWifi, k_isMobile;// 是否为WiFi，是否为移动
	private ConnectivityManager connectivityManager_KBaseActivity;
	private NetworkInfo.State kState = null;

	public MyNetUtils(Context context) {
		connectivityManager_KBaseActivity = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
	}

	/**
	 * 检测网络是否可用
	 * 
	 * @return
	 */
	public static boolean isNetworkConnected(Context context) {
		ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo ni = cm.getActiveNetworkInfo();
		return ni != null && ni.isConnectedOrConnecting();
	}


	/**
	 * 获取当前网络类型
	 * 
	 * @return 0：没有网络 1：WIFI网络 2：WAP网络 3：NET网络
	 */

	public static final int NETTYPE_WIFI = 0x01;
	public static final int NETTYPE_CMWAP = 0x02;
	public static final int NETTYPE_CMNET = 0x03;

	public int getNetworkType(Context context) {
		int netType = 0;
		ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
		if (networkInfo == null) {
			return netType;
		}
		int nType = networkInfo.getType();
		if (nType == ConnectivityManager.TYPE_MOBILE) {
			String extraInfo = networkInfo.getExtraInfo();
			if (extraInfo!=null&&"".equals(extraInfo)) {
				if (extraInfo.toLowerCase().equals("cmnet")) {
					netType = NETTYPE_CMNET;
				} else {
					netType = NETTYPE_CMWAP;
				}
			}
		} else if (nType == ConnectivityManager.TYPE_WIFI) {
			netType = NETTYPE_WIFI;
		}
		return netType;
	}



	public boolean isInternet() {
		isWifi();
		if (!k_isWifi && !k_isMobile) {
			return false;
		}
		return true;
	}

	private boolean isWifi() {
		kState = connectivityManager_KBaseActivity.getNetworkInfo(
				ConnectivityManager.TYPE_WIFI).getState();
		if (kState == NetworkInfo.State.CONNECTED) {
			k_isWifi = true;
		} else {
			k_isWifi = false;
		}
		return k_isWifi;
	}



	/**
	 * 打开网络设置
	 * @param context
	 */
	public void openNetset(Context context){
		//判断手机系统的版  即API大于10 就是3.0或以上版
		Intent intent = null;
		if(android.os.Build.VERSION.SDK_INT>10){
			intent = new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS);
		}else{
			intent = new Intent();
			ComponentName component = new ComponentName("com.android.settings","com.android.settings.WirelessSettings");
			intent.setComponent(component);
			intent.setAction("android.intent.action.VIEW");
		}
		context.startActivity(intent);

	}
}
